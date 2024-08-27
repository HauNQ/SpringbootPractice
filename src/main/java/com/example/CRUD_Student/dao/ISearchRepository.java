package com.example.CRUD_Student.dao;

import com.example.CRUD_Student.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ISearchRepository {
    PageResponse<?> getSearchedAddress();
    PageResponse<?> searchByCriteria(Pageable pageable, String sorts, String[] address,String... search);
    PageResponse<?> getsearchedStudentWithPage(int pageNo, int pageNum, String search, String sorts);
}
