package com.enigma.pocket.util;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageWrapper<T> {
    private Integer code;
    private Integer pages;
    private Integer totalPages;
    private Integer size;
    private Long count;
    private List<T> content;

    public PageWrapper(Integer code, Page<T> content) {
        this.code = code;
        this.pages = content.getNumber();
        this.totalPages = content.getTotalPages();
        this.size = content.getSize();
        this.count = content.getTotalElements();
        this.content = content.getContent();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
