package hello;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import hello.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Marija on 8/28/2017.
 */
@RestController
@RequestMapping(value = "/auth" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;


    @RequestMapping("/serviceinfo")
    public String serviceInfo(){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("135026-login", false);
        return instance.getIPAddr();
    }


    @RequestMapping("/sign-up")
    public String singUpUser(@RequestBody ApplicationUser user){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("135026-login", false);
        String ip = instance.getIPAddr();
        String login = restTemplate.postForObject("http://"+ip+":8080/users/sign-up", user , String.class);


        return login;
    }


    @RequestMapping("/login")
    public String login(@RequestBody ApplicationUser user){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("135026-login", false);
        String ip = instance.getIPAddr();
        HttpServletResponse res = restTemplate.postForObject("http://"+ip+":8080/login", user , HttpServletResponse.class);
        String authorizationToken = res.getHeader("Authorization");
        return authorizationToken;
    }

}
