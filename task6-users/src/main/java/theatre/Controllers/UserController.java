package theatre.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import theatre.Entities.Users;
import theatre.Repository.UserRepo;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Marija on 3/14/2017.
 */
@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return counter.incrementAndGet() + "";
    }


    @RequestMapping(value= "/createNewUser", method = RequestMethod.POST)
    public Users CreateNew(@RequestParam String username, @RequestParam String password){
        Users user = new Users("test","stest",username, password);
        userRepo.save(user);
        return user;
    }

    @RequestMapping("/getbyid")
    public Users getById(@RequestParam(value="id", defaultValue = "-1") Long id) {
        Users foundCustomer = userRepo.findOne(id);
        return foundCustomer;
    }


}
