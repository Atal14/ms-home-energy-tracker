package ms_home_energy_tracker.user_service.repository;

import ms_home_energy_tracker.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
