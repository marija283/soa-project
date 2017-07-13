package theatre.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import theatre.Entities.Users;
import theatre.Repository.UserRepo;
import theatre.Service.UserService;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Marija on 3/14/2017.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return counter.incrementAndGet() + "";
    }


    @RequestMapping(value= "/createNewUser", method = RequestMethod.POST)
    public Users CreateNew(@RequestParam String username, @RequestParam String password) throws Exception {
        Users u = userService.findUsersByUsername(username);
        if( u != null)
            throw new Exception("Username taken");
        return userService.save(username, "name","stest", passwordEncoder.encode(password));
    }


    @RequestMapping(value= "/update", method = RequestMethod.POST)
    public Users UpdateUser(@RequestParam String username, @RequestParam String password) throws Exception {
        Users u = userService.findUsersByUsername(username);
        if( u != null)
            throw new Exception("No such user");
        u.setName("name");
        u.setName("surname");
        u.setName(passwordEncoder.encode(password));
        return userService.save(username, "name", "stest", password);
    }

    @RequestMapping("/getbyid")
    public Users getById(@RequestParam(value="id", defaultValue = "-1") Long id) {
        Users foundCustomer = userService.findOne(id);
        return foundCustomer;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password){

        if(!username.trim().isEmpty() && !password.trim().isEmpty())
        {
            Users u = userService.login(username, password);
            if(u != null)
            {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                System.out.println(session.getId());
                return new ResponseEntity<Users>(u,HttpStatus.OK);
            }

        }
        return new ResponseEntity<Object>(password, HttpStatus.OK);
    }


}
