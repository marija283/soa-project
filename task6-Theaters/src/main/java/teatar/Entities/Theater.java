package teatar.Entities;

import javax.persistence.*;
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
    private List<Play> plays;

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
}
