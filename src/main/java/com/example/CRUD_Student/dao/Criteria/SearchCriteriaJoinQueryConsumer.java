package com.example.CRUD_Student.dao.Criteria;

import com.example.CRUD_Student.entity.Address;
import com.example.CRUD_Student.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

@Getter
@Setter
@Builder
public class SearchCriteriaJoinQueryConsumer implements Consumer<SearchCriteria> {
    private CriteriaBuilder builder;
    private Predicate predicate;
    private Join<Student, Address> root;

    @Override
    public void accept(SearchCriteria searchCriteria) {
        if(searchCriteria.getOperation().equalsIgnoreCase(">")){
            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get(searchCriteria.getKey())
                                                                            ,searchCriteria.getValue().toString()));
        } else if(searchCriteria.getOperation().equalsIgnoreCase("<")){
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(root.get(searchCriteria.getKey())
                    ,searchCriteria.getValue().toString()));
        }else {
           if(root.get(searchCriteria.getKey()).getJavaType() == String.class){
               predicate = builder.and(predicate, builder.like(root.get(searchCriteria.getKey())
                       , String.format("%%%s%%",searchCriteria.getValue().toString())));
           }else {
               predicate = builder.and(predicate, builder.equal(root.get(searchCriteria.getKey())
                       ,searchCriteria.getValue().toString()));
           }
        }
    }
}
