package com.daowen.webcontrol;

public class PagerMetal {

    private int pageIndex;
    private int pageSize;
    private int totalRecords;
    private int totalPages;

    public PagerMetal() {
        this.pageIndex = 1;
        this.pageSize = 10;
    }

    public PagerMetal(int pageIndex, int pageSize, int totalRecords) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.totalPages = (totalRecords + pageSize - 1) / pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        this.totalPages = (totalRecords + pageSize - 1) / pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean hasNext() {
        return pageIndex < totalPages;
    }

    public boolean hasPrevious() {
        return pageIndex > 1;
    }
}