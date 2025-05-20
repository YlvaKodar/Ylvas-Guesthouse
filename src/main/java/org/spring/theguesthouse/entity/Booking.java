package org.spring.theguesthouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    private Date startingDate;
    private Date endingDate;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToMany
    @JoinTable()
    private List<Room> rooms;

    public void addRoom(Room room) {
        rooms.add(room);
    }
}
