package com.codeWithProject.HotelSever.services.customer.room;

import com.codeWithProject.HotelSever.dto.RoomsResponseDto;

public interface RoomService {

    RoomsResponseDto getAvailableRooms(int pageNumber);
}
