package rating.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rating.Entity.Stars;
import rating.Repository.StarsRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Marija on 6/12/2017.
 */

@Service
public class StarsService {

    @Autowired
    StarsRepository starsRepository;

    public Stars rateItem(long itemId,long userId,int stars) {
        Stars stars1 = getRateStateForUserObj(itemId, userId);
        if (stars1 == null) {
            stars1 = new Stars(itemId, userId, stars);
        } else {
            stars1.setStars(stars);
        }
        return starsRepository.save(stars1);
    }

    public double findAverageRatingOnItem(Long itemId) {
        Iterable<Stars> allstars = starsRepository.findAll();
        Stream<Stars> targetStream = StreamSupport.stream(allstars.spliterator(), false);

        return targetStream
                .filter((Stars s) -> s.getRatedItemId() == itemId)
                .mapToInt((Stars s) -> s.getStars())
                .average().orElse(0);
    }

    public long getTotalUserRatings(Long itemId) {
        Iterable<Stars> allstars = starsRepository.findAll();
        Stream<Stars> targetStream = StreamSupport.stream(allstars.spliterator(), false);

        return targetStream
                .filter(s -> s.getRatedItemId() == itemId)
                .count();
    }

    public int getRateStateForUser(Long usetId, Long itemId) {
        Iterable<Stars> allstars = starsRepository.findAll();
        Stream<Stars> targetStream = StreamSupport.stream(allstars.spliterator(), false);
        Stars star = targetStream
                .filter(s -> s.getRatedItemId() == itemId && s.getUserId() == usetId)
                .findFirst()
                .orElse(null);
        return star != null ? star.getStars() : 0;

    }

    private Stars getRateStateForUserObj(Long itemId, Long UserId) {
        Iterable<Stars> allstars = starsRepository.findAll();
        Stream<Stars> targetStream = StreamSupport.stream(allstars.spliterator(), false);
        return targetStream
                .filter(s -> s.getRatedItemId() == itemId && s.getUserId() == UserId)
                .findFirst()
                .orElse(null);

    }
}
