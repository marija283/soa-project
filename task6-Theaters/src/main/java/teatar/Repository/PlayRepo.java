package teatar.Repository;

import org.springframework.data.repository.CrudRepository;
import teatar.Entities.Play;

/**
 * Created by Marija on 3/14/2017.
 */
public interface PlayRepo extends CrudRepository<Play, Long> {
}
