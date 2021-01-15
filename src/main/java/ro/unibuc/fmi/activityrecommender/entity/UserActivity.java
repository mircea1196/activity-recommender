package ro.unibuc.fmi.activityrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_activity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {

    @Id
    @GeneratedValue(generator = "USER_ACTIVITY_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_ACTIVITY_GEN", sequenceName = "user_activity_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "activity_id")
    private Long activityId;

}
