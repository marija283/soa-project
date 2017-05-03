package teatar.Entities;

import javax.persistence.*;

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

    @ManyToOne
    private Theater theater;

    Play(){}

    public Play(String name, String description, Theater theater) {
        this.name = name;
        this.description = description;
        this.theater = theater;
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

    public Theater getTheater() {
        return theater;
    }
}
