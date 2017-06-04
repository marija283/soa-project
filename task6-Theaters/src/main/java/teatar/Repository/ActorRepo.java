package teatar.Repository;

import org.springframework.data.repository.CrudRepository;
import teatar.Entities.Actor;

/**
 * Created by Marija on 6/2/2017.
 */
public interface ActorRepo extends CrudRepository<Actor, Long> {
}
