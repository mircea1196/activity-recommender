package ro.unibuc.fmi.activityrecommender.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.activityrecommender.entity.Interval;

@Repository
public interface IntervalRepository extends JpaRepository<Interval, Long> {
}
