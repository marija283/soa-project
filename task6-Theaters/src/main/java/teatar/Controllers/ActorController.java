package teatar.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teatar.Entities.Actor;
import teatar.Entities.Play;
import teatar.Entities.Theater;
import teatar.Repository.ActorRepo;

/**
 * Created by Marija on 6/2/2017.
 */
@RestController
@RequestMapping(value = "/actor" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorController {

    @Autowired
    ActorRepo actorRepo;


    @RequestMapping(value="/createnew", method = RequestMethod.POST)
    public Actor CreateNew(@RequestParam(value = "name") String name, String surname){
        Actor actor = new Actor(name, surname);
        actorRepo.save(actor);
        return actor;
    }

    @RequestMapping("/getbyid")
    public Actor getById(@RequestParam(value="id", defaultValue = "-1") Long id) {
        Actor found = actorRepo.findOne(id);
        return found;
    }
}
