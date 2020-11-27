package ro.unibuc.fmi.activityrecommender.model;

import lombok.Data;

@Data
public class UserActivityModel {

    private Long id;
    private Long userId;
    private Long activityId;

}
