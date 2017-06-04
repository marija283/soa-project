package teatar.Entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by 135026 on 3/7/2017.
 */

@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    private Collection<Play> plays;

    Theater(){}

    public Theater(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Play> getPlays() {
        return plays;
    }

    public void setPlays(List<Play> plays) {
        this.plays = plays;
    }

    public void setPlays(Play play){
        plays.add(play);
    }
}
