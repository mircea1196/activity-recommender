package ro.unibuc.fmi.activityrecommender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.fmi.activityrecommender.entity.UserIntervals;

public interface UserIntervalsRepository extends JpaRepository<UserIntervals, Long> {
}
