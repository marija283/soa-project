package teatar.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teatar.Entities.Theater;
import teatar.Repository.TheaterRepo;


import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Marija on 3/14/2017.
 */
@RestController
public class TheaterController {

    @Autowired
    TheaterRepo theaterRepo;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return counter.incrementAndGet() + "";
    }

    @RequestMapping(value="/createNewTheater", method = RequestMethod.POST)
    public Theater CreateNew(@RequestParam(value = "name") String name){
        Theater t = new Theater(name);
        theaterRepo.save(t);
        return t;
    }

    @RequestMapping("/getbyid")
    public Theater getById(@RequestParam(value="id", defaultValue = "-1") Long id) {
        Theater found = theaterRepo.findOne(id);
        return found;
    }
}
