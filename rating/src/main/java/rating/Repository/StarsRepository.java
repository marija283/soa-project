package rating.Repository;

import org.springframework.data.repository.CrudRepository;
import rating.Entity.Stars;

/**
 * Created by Marija on 6/12/2017.
 */
public interface StarsRepository extends CrudRepository<Stars, Long>
{
       // void get
}
