package ro.unibuc.fmi.activityrecommender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChangePasswordModel {

    private String oldPassword;
    private String newPassword;

}
