package teatar.Repository;

import org.springframework.data.repository.CrudRepository;
import teatar.Entities.Play;
import teatar.Entities.Theater;

import java.util.Collection;

/**
 * Created by Marija on 3/14/2017.
 */
public interface TheaterRepo extends CrudRepository<Theater, Long> {
    Collection<Theater> findAll();
    //Collection<Play> getPlays();
}
