package com.example.CRUD_Student.dao;

import com.example.CRUD_Student.dao.Criteria.SearchCriteria;
import com.example.CRUD_Student.dao.Criteria.SearchCriteriaJoinQueryConsumer;
import com.example.CRUD_Student.dao.Criteria.SearchCriteriaQueryConsumer;
import com.example.CRUD_Student.dto.response.PageResponse;
import com.example.CRUD_Student.entity.Address;
import com.example.CRUD_Student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.hibernate.type.ListType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SearchRepository implements ISearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageResponse<?> getSearchedAddress() {
        StringBuilder sql = new StringBuilder("SELECT a FROM Address a WHERE a.student.firstName = (:firstName)");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("firstName", "Thị F");
        List result = query.getResultList();
        return PageResponse.builder().items(result).build();
    }

    @Override
    public PageResponse<?> getsearchedStudentWithPage(int pageNo, int pageSize, String search, String sort) {

              /*  StringBuilder query = new StringBuilder("Select new com.example.CRUD_Student.dto.request.StudentRequest(" +
                "s.id, s.email, s.firstName, s.lastName, new com.example.CRUD_Student.dto.request.AddressRequest( " +
                "a.id, a.city, a.province)) " +
                "from Student s " +
                "left join s.addresses a " +
                "where 1=1");*/


        // get List of Student
        StringBuilder query = new StringBuilder("Select s from Student s" +
                " where 1=1");

        // search
        if (StringUtils.hasLength(search)) {
            query.append(" and lower(s.firstName) LIKE lower(:firstName)");
            query.append(" or lower(s.lastName) LIKE lower(:lastName)");
            query.append(" or lower(s.email) LIKE lower(:email)");
        }

        //sort
        if (StringUtils.hasLength(sort)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sort);
            if (matcher.find()) {
                query.append(String.format(" Order By %s %s", matcher.group(1), matcher.group(3)));
            } else throw new RuntimeException("Invalid parameters");
        }

        Query selectedQuery = entityManager.createQuery(query.toString());
        selectedQuery.setFirstResult(pageNo * pageSize);
        selectedQuery.setMaxResults(pageSize);

        //search
        if (StringUtils.hasLength(search)) {
            selectedQuery.setParameter("firstName", String.format("%%%s%%", search));
            selectedQuery.setParameter("lastName", String.format("%%%s%%", search));
            selectedQuery.setParameter("email", String.format("%%%s%%", search));
        }

        List result = selectedQuery.getResultList();

        // Count total Student
        StringBuilder countQuery = new StringBuilder("Select count(*) from Student s where 1=1");
        // search
        if (StringUtils.hasLength(search)) {
            countQuery.append(" and lower(s.firstName) LIKE lower(:firstName)");
            countQuery.append(" or lower(s.lastName) LIKE lower(:lastName)");
            countQuery.append(" or lower(s.email) LIKE lower(:email)");
        }

        Query count = entityManager.createQuery(countQuery.toString());
        //search
        if (StringUtils.hasLength(search)) {
            count.setParameter("firstName", String.format("%%%s%%", search));
            count.setParameter("lastName", String.format("%%%s%%", search));
            count.setParameter("email", String.format("%%%s%%", search));
        }

        Long totalStudents = (Long) count.getSingleResult();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage((int) Math.ceil((double) totalStudents.intValue() / pageSize))
                .items(result)
                .build();
    }

    @Override
    public PageResponse<?> searchByCriteria(Pageable pageable, String sorts, String[] address,String... search) {
        List<SearchCriteria> searchQuery = getCriteriaList(search);
        List<SearchCriteria> addressQuery = getCriteriaList(address);

        List<Student> students = GetStudents(pageable, sorts, addressQuery,searchQuery);
        Long totalElement = getTotalElement(addressQuery,searchQuery);
        int totalPage =(int) Math.ceil((double) totalElement / pageable.getPageSize());

        return PageResponse.builder()
                .pageNo(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalPage(totalPage)
                .totalElement(totalElement.intValue())
                .items(students)
                .build();
    }

    private Long getTotalElement(List<SearchCriteria> addressQuery, List<SearchCriteria> studentQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Student> root = query.from(Student.class);

        // Build student predicates
        Predicate studentPre = criteriaBuilder.conjunction();
        if (!studentQuery.isEmpty()) {
            SearchCriteriaQueryConsumer studentConsumer = SearchCriteriaQueryConsumer.builder()
                    .builder(criteriaBuilder)
                    .root(root)
                    .predicate(studentPre)
                    .build();
            studentQuery.forEach(studentConsumer);
            studentPre = studentConsumer.getPredicate();
        }

        // Build address predicates
        Predicate addPre = criteriaBuilder.conjunction();
        if (!addressQuery.isEmpty()) {
            SearchCriteriaJoinQueryConsumer addressConsumer = SearchCriteriaJoinQueryConsumer.builder()
                    .builder(criteriaBuilder)
                    .root(root.join("addresses"))
                    .predicate(addPre)
                    .build();
            addressQuery.forEach(addressConsumer);
            addPre = addressConsumer.getPredicate();
        }


        query.where(studentPre,addPre);
        query.select(criteriaBuilder.count(root));

        return entityManager.createQuery(query).getSingleResult();
    }

    private List<SearchCriteria> getCriteriaList(String[] list) {
        List<SearchCriteria> rs = new ArrayList<>();

        if (list != null && list.length != 0) {
            Pattern pattern = Pattern.compile("(\\w+?)([:><])(.*)");
            Arrays.asList(list).forEach(s -> {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find())
                    rs.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            });
        }

        return rs;
    }

    private List<Student> GetStudents(Pageable pageable, String sortBy, List<SearchCriteria> addressQuery,List<SearchCriteria> studentQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        // Build student predicates
        Predicate studentPre = criteriaBuilder.conjunction();
        if (!studentQuery.isEmpty()) {
            SearchCriteriaQueryConsumer studentConsumer = SearchCriteriaQueryConsumer.builder()
                    .builder(criteriaBuilder)
                    .root(root)
                    .predicate(studentPre)
                    .build();
            studentQuery.forEach(studentConsumer);
            studentPre = studentConsumer.getPredicate();
        }

        // Build address predicates
        Predicate addPre = criteriaBuilder.conjunction();
        if (!addressQuery.isEmpty()) {
            SearchCriteriaJoinQueryConsumer addressConsumer = SearchCriteriaJoinQueryConsumer.builder()
                    .builder(criteriaBuilder)
                    .root(root.join("addresses"))
                    .predicate(addPre)
                    .build();
            addressQuery.forEach(addressConsumer);
            addPre = addressConsumer.getPredicate();
        }


        query.where(studentPre,addPre);

        if (sortBy != null) {
            Pattern pattern = Pattern.compile("(\\w+?)([:])(asc|desc|ASC|DESC)");
            Matcher matcher = pattern.matcher(sortBy);
            if(matcher.find()){
                if(matcher.group(3).equalsIgnoreCase("desc")){
                    query.orderBy(criteriaBuilder.desc(root.get(matcher.group(1))));
                }else {
                    query.orderBy(criteriaBuilder.asc(root.get(matcher.group(1))));
                }
            }
        }

        return entityManager.createQuery(query)
                .setFirstResult(pageable.getPageNumber()* pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }
}
