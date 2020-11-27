package ro.unibuc.fmi.activityrecommender.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "activity")
@Data
@Builder
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "description")
    private String description;

}
