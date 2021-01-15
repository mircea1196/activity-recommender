package ro.unibuc.fmi.activityrecommender.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.unibuc.fmi.activityrecommender.entity.Role;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    private String name;
    private String location;
    private String description;
    private Boolean showPhone;
    private Double rating;
    private Boolean enabled;
    private Set<Role> roles;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt;

}
