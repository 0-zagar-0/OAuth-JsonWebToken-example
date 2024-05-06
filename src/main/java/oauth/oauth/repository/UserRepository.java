package oauth.oauth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import oauth.oauth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
