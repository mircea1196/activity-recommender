package ro.unibuc.fmi.activityrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "activity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(generator = "ACTIVITY_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ACTIVITY_SEQ", sequenceName = "activity_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "description")
    private String description;

}
