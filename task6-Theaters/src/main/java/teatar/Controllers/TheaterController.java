package teatar.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teatar.Entities.Play;
import teatar.Entities.Theater;
import teatar.Repository.TheaterRepo;


import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Marija on 3/14/2017.
 */
@RestController
@RequestMapping(value = "/theater" )
        //,produces = MediaType.APPLICATION_JSON_VALUE)
public class TheaterController {

    @Autowired
    TheaterRepo theaterRepo;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return counter.incrementAndGet() + "";
    }

    @RequestMapping(value="/createnewtheater", method = RequestMethod.POST)
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

    @RequestMapping("/getall")
    public Collection<Theater> getAll(){
        return theaterRepo.findAll();
    }

    @RequestMapping("/getplays")
    public Collection<Play> getPlays(Long id){
        return theaterRepo.findOne(id).getPlays();
    }
}
