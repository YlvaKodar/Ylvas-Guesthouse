package org.spring.theguesthouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.theguesthouse.dto.RoomDto;
import org.spring.theguesthouse.entity.Room;
import org.spring.theguesthouse.service.RoomService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    @Override
    public RoomDto roomToDto(Room r) {
        return RoomDto.builder().roomNumber(r.getR).build();
    }

    @Override
    public Room roomDtoToRoom(RoomDto r) {
        return null;
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return List.of();
    }
}
