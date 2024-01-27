package in.codelantern.cmrvideostreaming.repositories;

import in.codelantern.cmrvideostreaming.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
     Optional<User> findByEmail(String email);
}
