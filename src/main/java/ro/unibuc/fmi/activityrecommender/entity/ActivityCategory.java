package ro.unibuc.fmi.activityrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "activity_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCategory {

    @Id
    @GeneratedValue(generator = "ACTIVITY_CAT_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ACTIVITY_CAT_GEN", sequenceName = "activity_cat_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

}
