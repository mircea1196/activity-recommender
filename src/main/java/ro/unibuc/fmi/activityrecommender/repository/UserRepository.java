package ro.unibuc.fmi.activityrecommender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.activityrecommender.entity.Role;
import ro.unibuc.fmi.activityrecommender.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(final String username);
    Optional<Users> findByEmail(final String email);
    Optional<Users> getByPhoneNumber(String phone);
    Users getById(final Long id);
    List<Users> getAllByRoles(final Role role);

    Boolean existsByUsername(final String username);
    Boolean existsByEmail(final String email);
    Boolean existsByPhoneNumber(final String phoneNumber);
}
