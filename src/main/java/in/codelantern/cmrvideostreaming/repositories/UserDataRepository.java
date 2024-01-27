package in.codelantern.cmrvideostreaming.repositories;

import in.codelantern.cmrvideostreaming.models.UserData;
import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends CrudRepository<UserData,Long> {
}
