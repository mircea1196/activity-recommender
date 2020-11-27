package ro.unibuc.fmi.activityrecommender.model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
public class IntervalModel {

    private Long id;
    private Long intervalId;
    private ZonedDateTime startInterval;
    private ZonedDateTime endInterval;

}
