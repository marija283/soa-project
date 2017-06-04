package teatar.Entities;

import javax.persistence.*;
import java.util.*;

/**
 * Created by 135026 on 3/7/2017.
 */

@Entity
public class Play {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Date fromDate;

    private Date toDate;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="Play_author",
            joinColumns=@JoinColumn(name="Play_id"),
            inverseJoinColumns=@JoinColumn(name="Author_id"))
    private List<Actor> actors;


    Play(){}

    public Play(String name, String description) {
        this.name = name;
        this.description = description;
        this.actors = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setActors(List<Actor> actors){
        actors = actors;
    }

    public List<Actor> getActors(){
        return  actors;
    }

    public void addActor(Actor actor){
        actors.add(actor);
    }


}
