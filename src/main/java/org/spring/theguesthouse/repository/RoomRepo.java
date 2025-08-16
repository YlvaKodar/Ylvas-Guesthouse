package org.spring.theguesthouse.repository;

import org.spring.theguesthouse.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {}