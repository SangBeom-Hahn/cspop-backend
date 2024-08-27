package com.kyonggi.cspop.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor
public class PageInfo {
    private int currentPage;
    private int lastPage;
    private int countPerPage;
    private long totalSize;

    private PageInfo(final int currentPage, final int lastPage, final int countPerPage, final long totalSize) {
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.countPerPage = countPerPage;
        this.totalSize = totalSize;
    }

    public static PageInfo of(final int currentPage, final int lastPage, final int countPerPage, final long totalSize) {
        return ofNextPage(currentPage, lastPage, countPerPage, totalSize);
    }

    public static PageInfo ofNextPage(int currentPage, final int lastPage, final int countPerPage, final long totalSize) {
        return new PageInfo(currentPage + 1, lastPage, countPerPage, totalSize);
    }

    public static PageInfo from(final Page<?> data) {
        final int pageNumber = data.getPageable().getPageNumber();
        final int totalPages = data.getTotalPages();
        final int pageSize = data.getPageable().getPageSize();
        final long totalElements = data.getTotalElements();
        return ofNextPage(pageNumber, totalPages, pageSize, totalElements);
    }
}
