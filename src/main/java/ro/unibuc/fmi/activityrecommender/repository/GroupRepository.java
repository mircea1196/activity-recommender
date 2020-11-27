package ro.unibuc.fmi.activityrecommender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.fmi.activityrecommender.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
