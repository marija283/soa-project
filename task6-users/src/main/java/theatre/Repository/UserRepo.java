package theatre.Repository;

import org.springframework.data.repository.CrudRepository;
import theatre.Entities.Users;

import java.util.List;

/**
 * Created by Marija on 3/14/2017.
 */
public interface UserRepo extends CrudRepository<Users, Long> {
    List<Users> findByUsername(String username);
}
