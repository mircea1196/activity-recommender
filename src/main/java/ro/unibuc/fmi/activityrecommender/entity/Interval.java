package ro.unibuc.fmi.activityrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "interval")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interval {

    @Id
    @GeneratedValue(generator = "INTERVAL_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "INTERVAL_SEQ_GEN", sequenceName = "interval_seq", allocationSize = 1)
    private Long id;

    @Column(name = "interval_id", nullable = false)
    private Long intervalId;

    @Column(name = "start_interval")
    private ZonedDateTime startInterval;

    @Column(name = "end_interval")
    private ZonedDateTime endInterval;

}
