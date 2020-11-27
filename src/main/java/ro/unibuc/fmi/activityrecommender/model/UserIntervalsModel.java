package ro.unibuc.fmi.activityrecommender.model;

import lombok.Data;

@Data
public class UserIntervalsModel {

    private Long id;
    private Long userId;
    private Long groupId;
    private Long intervalId;

}
