package ro.unibuc.fmi.activityrecommender.model;

import lombok.Data;

@Data
public class UserModel {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated = false;
    private String langKey;
    private String imageUrl;
    private String activationKey;

}
