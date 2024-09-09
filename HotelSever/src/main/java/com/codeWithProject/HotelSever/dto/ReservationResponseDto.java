package com.codeWithProject.HotelSever.dto;

import java.util.List;

public class ReservationResponseDto {


    private Integer totalPage;
    private Integer pageNumber;
    private List<ReservationDto> reservationDtoList;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<ReservationDto> getReservationDtoList() {
        return reservationDtoList;
    }

    public void setReservationDtoList(List<ReservationDto> reservationDtoList) {
        this.reservationDtoList = reservationDtoList;
    }
}
