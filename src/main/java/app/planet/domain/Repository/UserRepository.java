package app.planet.domain.Repository;


import app.planet.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    User findUserByUserId(Long userId);

    User findByUserIdAndEmail(Long userId, String email);

}
