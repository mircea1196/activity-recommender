package ro.unibuc.fmi.activityrecommender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.unibuc.fmi.activityrecommender.entity.Role;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAdminModel {

    private String username;
    private Set<Role> roles;

    public void addRole(final Role role) {
        roles.add(role);
    }

}
