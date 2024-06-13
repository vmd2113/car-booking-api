package com.duongw.carbookingapi.DTO;

import java.util.List;

public class BookingDetailResponse {

    private List<BookingDetailDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPage;
    private boolean last;
    private String sortField;
    private String orderBy;
    private String reversedOrderBy;


    public List<BookingDetailDTO> getContent() {
        return content;
    }

    public void setContent(List<BookingDetailDTO> content) {
        this.content = content;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getReversedOrderBy() {
        return reversedOrderBy;
    }

    public void setReversedOrderBy(String reversedOrderBy) {
        this.reversedOrderBy = reversedOrderBy;
    }


    @Override
    public String toString() {
        return "BookingDetailResponse{" +
                "content=" + content +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalElement=" + totalElement +
                ", totalPage=" + totalPage +
                ", last=" + last +
                ", sortField='" + sortField + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", reversedOrderBy='" + reversedOrderBy + '\'' +
                '}';
    }
}
