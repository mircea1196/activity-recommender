package ro.unibuc.fmi.activityrecommender.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@Data
@Builder
public class Authority {

    @Id
    private String name;

}
