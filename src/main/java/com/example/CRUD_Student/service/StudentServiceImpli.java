package com.example.CRUD_Student.service;

import com.example.CRUD_Student.dao.ISearchRepository;
import com.example.CRUD_Student.dao.SearchRepository;
import com.example.CRUD_Student.dao.StudentRepository;
import com.example.CRUD_Student.dto.request.StudentRequest;
import com.example.CRUD_Student.dto.response.PageResponse;
import com.example.CRUD_Student.entity.Address;
import com.example.CRUD_Student.entity.Student;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpli implements StudentService {
    private final StudentRepository studentRepository;
    private final ISearchRepository searchRepository;

    @Override
    public Student getStudentByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    @Override
    public Student getStudentByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Student> findByFirstNameContaining(String firstName) {
        return this.studentRepository.findByFirstNameLike(firstName);
    }

    @Override
    public PageResponse<?> getStudentWithPage(int pageNo, int pageSize, String... sorts) {
        List<Sort.Order> orders = new ArrayList<>();

        if(sorts != null){
            for (String sort : sorts){
                Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
                Matcher matcher = pattern.matcher(sort);
                if(matcher.find()){
                    if(matcher.group(3).equalsIgnoreCase("asc")){
                        orders.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                    }else if(matcher.group(3).equalsIgnoreCase("desc")){
                        orders.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                    }else throw new  InvalidParameterException("Request parameter is invalid. Should be(ASD/DESC)");
                }
            }
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize,Sort.by(orders));
        Page<Student> studentPage = this.studentRepository.findAll(pageable);

        List<Student> students = studentPage.stream().toList();
        students.forEach(st ->{
            log.info(st.getFirstName());
            st.getAddresses().forEach(a -> log.info(a.getProvince()));
        });

        List<StudentRequest> response = studentPage.stream().map(st -> StudentRequest.builder()
                        .id(st.getId())
                        .firstName(st.getFirstName())
                        .lastName(st.getLastName())
                        .email(st.getEmail())
                        .build())
                .toList();

        return PageResponse.builder().pageNo(pageNo).pageSize(pageSize).totalPage(studentPage.getTotalPages()).items(response).build();
    }

    @Override
    public List<Student> getListStudentTest(){
       return this.studentRepository.findAll();
    }

    @Override
    public Long addStudent(StudentRequest st) {
        Address address = new Address(st.getAddressRequest().getCity(), st.getAddressRequest().getProvince());
        Set<Address> addresses = new HashSet<>();
        addresses.add(address);

       Student student = Student.builder()
               .firstName(st.getFirstName())
               .lastName(st.getLastName())
               .email(st.getEmail())
               .addresses(addresses)
               .build();

       for(Address add : addresses){
           add.setStudent(student);
       }
       return  studentRepository.save(student).getId();
    }

    @Override
    public PageResponse<?> getsearchedStudentWithPage(int pageNo, int pageNum, String search, String sorts) {
         return searchRepository.getsearchedStudentWithPage( pageNo,  pageNum,  search,  sorts);
    }

    @Override
    public PageResponse<?> searchByCriteria(Pageable pageable, String sorts,String[] address, String... search) {
        return searchRepository.searchByCriteria(pageable, sorts,address, search);
    }


    @Override
    public PageResponse<?> searchBySpecification(Pageable pageable, String search, String sort,String city) {
        Specification<Student> spec = filterStudentSpecification(pageable, search, sort, city);

        List<Student> list = this.studentRepository.findAll(spec);
        return PageResponse.builder()
                .pageNo(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalPage(0)
                .items(list)
                .build();
    }

    private Specification<Student> filterStudentSpecification(Pageable pageable, String search, String sort, String city) {
       return (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
           List<Predicate> orPredicates = new ArrayList<>();
           orPredicates.add(criteriaBuilder.like(root.get("firstName"),String.format("%%%s%%",search)));
           orPredicates.add(criteriaBuilder.like(root.get("lastName"),String.format("%%%s%%",search)));
           orPredicates.add(criteriaBuilder.like(root.get("email"),String.format("%%%s%%",search)));
           Predicate searchPre = criteriaBuilder.or(orPredicates.toArray(new Predicate[0]));

           if(StringUtils.hasLength(city)){
               Join<Address,Student> addressStudentJoin = root.join("addresses", JoinType.INNER);
               Predicate addPredicate = criteriaBuilder.equal(addressStudentJoin.get("city"), city);
               return criteriaBuilder.and(searchPre, addPredicate);
           }

           query.orderBy(criteriaBuilder.desc(root.get("id")));



           return criteriaBuilder.and(searchPre);
       };
    }


}
