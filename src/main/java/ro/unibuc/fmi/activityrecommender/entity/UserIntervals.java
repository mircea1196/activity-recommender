package ro.unibuc.fmi.activityrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_intervals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserIntervals {

    @Id
    @GeneratedValue(generator = "USER_INTERVAL_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_INTERVAL_GEN", sequenceName = "user_interval_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "interval_id", nullable = false)
    private Long intervalId;

}
