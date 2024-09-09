package com.codeWithProject.HotelSever.services.admin.rooms;

import com.codeWithProject.HotelSever.dto.RoomDto;
import com.codeWithProject.HotelSever.dto.RoomsResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface RoomsService {
    boolean postRoom(RoomDto roomDto);

    RoomsResponseDto getAllRooms(int pageNumber);

    RoomDto getRoomById (Long id);

    boolean updateRoom(Long id, RoomDto roomDto);

    void deleteRoom(Long id);
}
