
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
    private Date startingDate;
    private Date endingDate;
    private Long customerId;
    private List<Long> roomIds;
}
