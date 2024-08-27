package com.example.CRUD_Student.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageResponse<T>{
    private int pageNo;
    private int pageSize;
    private int totalPage;
    private int totalElement;
    private T items;
}
