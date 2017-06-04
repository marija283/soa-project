package rating.Entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Marija on 6/2/2017.
 */
@Entity
public class Stars<T, S> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long ratedItemId;

    private Long userId;

    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    private int stars;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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