package rating.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rating.Entity.Stars;
import rating.Repository.StarsRepository;
import rating.Service.StarsService;

/**
 * Created by Marija on 6/12/2017.
 */

@RestController
@RequestMapping(value = "/rate" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class StarsController {

    @Autowired
    StarsRepository starsRepository;

    @Autowired
    StarsService starsService;

    @RequestMapping(value="/createnew", method = RequestMethod.POST)
    public Stars CreateNew(@RequestParam(value = "item") Long itemId, @RequestParam(value = "user") Long userId, int stars){
        return starsService.rateItem(itemId, userId, stars);
    }

    @RequestMapping("/getbyid")
    public Stars getById(@RequestParam(value="id", defaultValue = "-1") Long id) {
        Stars found = starsRepository.findOne(id);
        return found;
    }

    @RequestMapping("/getbyitemid")
    public double getByItemId(@RequestParam(value="id", defaultValue = "-1") Long id) {
        return starsService.findAverageRatingOnItem(id);
    }

    @RequestMapping("/hasuserrated")
    public int hasUserRated(@RequestParam(defaultValue = "-1") Long user, Long item) {
        return starsService.getRateStateForUser(user, item);
    }

    @RequestMapping("/gettotalusersrated")
    public long getTotalUserRatings(@RequestParam(defaultValue = "-1")Long item) {
        return starsService.getTotalUserRatings(item);
    }


}
