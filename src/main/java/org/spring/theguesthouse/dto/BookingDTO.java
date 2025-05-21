package org.spring.theguesthouse.dto;

import lombok.Data;
import java.util.Date;

@Data
public class BookingDTO {
    private Long id;
    private Date start_date;
    private Date end_date;
}
