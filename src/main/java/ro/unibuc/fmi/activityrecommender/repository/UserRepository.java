package ro.unibuc.fmi.activityrecommender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.activityrecommender.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
