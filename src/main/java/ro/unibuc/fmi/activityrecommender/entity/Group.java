package ro.unibuc.fmi.activityrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "group_d")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(generator = "GROUP_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "GROUP_SEQ_GEN", sequenceName = "group_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "admin")
    private String admin;

}
