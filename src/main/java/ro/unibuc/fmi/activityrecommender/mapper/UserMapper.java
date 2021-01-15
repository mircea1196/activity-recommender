package ro.unibuc.fmi.activityrecommender.mapper;

import ro.unibuc.fmi.activityrecommender.entity.Users;
import ro.unibuc.fmi.activityrecommender.model.UserModel;

import java.time.LocalDateTime;

public class UserMapper {

    private UserMapper() {}

    public static Users toEntity(final UserModel userModel) {
        return Users.builder()
                .username(userModel.getUsername())
                .password(userModel.getPassword())
                .email(userModel.getEmail())
                .phoneNumber(userModel.getPhoneNumber())
                .createdOn(LocalDateTime.now())
                .enabled(true)
                .name(userModel.getName())
                .location(userModel.getLocation())
                .description(userModel.getDescription())
                .showPhone(userModel.getShowPhone())
                .rating(userModel.getRating())
                .build();
    }

    public static UserModel toModel(final Users user) {
        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .profileImageUrl(user.getProfileImageUrl())
                .createdAt(user.getCreatedOn())
                .name(user.getName())
                .location(user.getLocation())
                .description(user.getDescription())
                .showPhone(user.getShowPhone())
                .rating(user.getRating())
                .enabled(user.getEnabled())
                .roles(user.getRoles())
                .build();
    }

}
