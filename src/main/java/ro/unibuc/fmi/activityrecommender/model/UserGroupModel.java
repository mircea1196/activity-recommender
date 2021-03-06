package ro.unibuc.fmi.activityrecommender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGroupModel {

    private String username;
    private Long groupId;

}
