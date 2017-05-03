package teatar.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by 135026 on 3/7/2017.
 */

@Entity
public class Date {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private java.util.Date date;

   // private DateTime
}
