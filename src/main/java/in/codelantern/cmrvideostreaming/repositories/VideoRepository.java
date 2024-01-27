package in.codelantern.cmrvideostreaming.repositories;

import in.codelantern.cmrvideostreaming.models.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video,Long> {
}
