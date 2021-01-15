package ro.unibuc.fmi.activityrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(generator = "USER_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private LocalDateTime createdOn;
    private LocalDateTime lastLogin;
    private Boolean showPhone;
    private Boolean enabled;
    private String profileImageUrl;
    private String name;
    private String location;
    private String description;
    private Double rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


}
