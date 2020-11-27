package ro.unibuc.fmi.activityrecommender.model;

import lombok.Data;

@Data
public class ActivityModel {

    private Long id;
    private String name;
    private Long categoryId;
    private String description;

}
