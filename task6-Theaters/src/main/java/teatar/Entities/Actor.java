package teatar.Entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 135026 on 3/7/2017.
 */

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;

    @ManyToMany
    private List<Play> plays;
}
