package theatre.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import theatre.Entities.Users;
import theatre.Repository.UserRepo;

/**
 * Created by Marija on 7/9/2017.
 */

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Users findUsersByUsername(String username) {
        return userRepo.findByUsername(username).stream().findFirst().orElse(null);
    }

    public Users findUserById(Long id) {
        return userRepo.findOne(id);

    }

    public Users save(String username, String name, String surname,String password) {
        Users u =  findUsersByUsername(username);
        if(u == null){
            u = new Users( name, surname, username, password);
        }else{
            u.setName(name);
            u.setSurname(surname);
            u.setPassword(password);
        }
        return userRepo.save(u);
    }

    public Users save(Users user) {
        return userRepo.save(user);
    }




//    public Users createUser(String fname, String lname, String username, String password, String biography,
//                           String image) {
//        Users user = new Users();
//        user.setBiography(biography);
//        user.setFname(fname);
//        user.setImage(image);
//        user.setLname(lname);
//        user.setPassword(password);
//        user.setUsername(username.toLowerCase());
//       // user.setDateCreated(new Date());
//
//        return saveOrUpdateUser(user);
//    }


    public Users login(String username, String password) {
        List<Users> usersByUsername = userRepo.findByUsername(username.toLowerCase());

        if(usersByUsername != null && usersByUsername.size()>0){
            Users user = usersByUsername.get(0);
            if(passwordEncoder.matches(password, user.getPassword()))
                return user;
        }
        return null;
    }


    public Users updateUser(Long id, String fname, String lname,String bio) {
        Users userOld = userRepo.findOne(id);
        userOld.setName(fname);
        userOld.setBiography(bio);
        userOld.setSurname(lname);

        return userRepo.save(userOld);
    }


    public Users findOne(Long id) {
        return userRepo.findOne(id);
    }



    public Iterable<Users> findAllUsers() {
        return userRepo.findAll();
    }




}
