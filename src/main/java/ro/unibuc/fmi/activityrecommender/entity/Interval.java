package ro.unibuc.fmi.activityrecommender.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "interval")
@Data
@Builder
public class Interval {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "interval_id", nullable = false)
    private Long intervalId;

    @Column(name = "start_interval")
    private ZonedDateTime startInterval;

    @Column(name = "end_interval")
    private ZonedDateTime endInterval;

}
