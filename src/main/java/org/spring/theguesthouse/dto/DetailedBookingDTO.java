
package org.spring.theguesthouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailedBookingDTO {
    private Long id;
    private Date start_date;
    private Date end_date;
    private CustomerDto customer;
    private List<RoomDto> rooms;
}
