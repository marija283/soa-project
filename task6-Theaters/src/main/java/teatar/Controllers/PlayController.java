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
import teatar.Repository.PlayRepo;
import teatar.Repository.TheaterRepo;

/**
 * Created by Marija on 5/28/2017.
 */
@RestController
@RequestMapping(value = "/play" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayController {

    @Autowired
    PlayRepo playRepo;

    @Autowired
    TheaterRepo theaterRepo;

    @Autowired
    ActorRepo actorRepo;

    @RequestMapping(value="/createnew", method = RequestMethod.POST)
    public Play CreateNew(@RequestParam(value = "name") String name, String desc, Long theaterId){
        Theater t = theaterRepo.findOne(theaterId);
        Play play = new Play(name, desc);
        playRepo.save(play);
        t.setPlays(play);
        theaterRepo.save(t);
        return play;
    }

    @RequestMapping("/getbyid")
    public Play getById(@RequestParam(value="id", defaultValue = "-1") Long id) {
        Play found = playRepo.findOne(id);
        return found;
    }


    @RequestMapping(value="/addactor", method = RequestMethod.POST)
    public Play CreateNew(Long playId, Long actorId){
        Play found = playRepo.findOne(playId);
        Actor ac = actorRepo.findOne(actorId);
        if(found != null && ac != null){
            found.addActor(ac);
        }
        return found;
    }




}
