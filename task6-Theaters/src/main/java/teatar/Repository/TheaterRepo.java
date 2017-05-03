package teatar.Repository;

import org.springframework.data.repository.CrudRepository;
import teatar.Entities.Theater;

/**
 * Created by Marija on 3/14/2017.
 */
public interface TheaterRepo extends CrudRepository<Theater, Long> {
}
