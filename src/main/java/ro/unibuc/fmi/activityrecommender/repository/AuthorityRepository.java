package ro.unibuc.fmi.activityrecommender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.fmi.activityrecommender.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
