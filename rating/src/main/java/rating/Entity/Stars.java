package rating.Entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Marija on 6/2/2017.
 */
@Entity
public class Stars {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long ratedItemId;

    private Long userId;

    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    private int stars;

    public Stars(){}

    public Stars(Long ratedItemId, Long userId, int stars) {
        this.ratedItemId = ratedItemId;
        this.userId = userId;
        this.dateCreated = new Date();
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Long getRatedItemId() {
        return ratedItemId;
    }

    public void setRatedItemId(Long ratedItemId) {
        this.ratedItemId = ratedItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}