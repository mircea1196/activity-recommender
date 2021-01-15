package ro.unibuc.fmi.activityrecommender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommonIntervalModel {

    LocalDateTime startDate;
    LocalDateTime endDate;

}
