package hello;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marija on 6/26/2017.
 */
@RestController
@RequestMapping(value = "/play" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayController
{
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping("/{id}")
    public String getById(@PathVariable Long id) {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("theaters", false);
        String ip = instance.getIPAddr();
        String play = restTemplate.getForObject("http://"+ip+":8080/play/getbyid?id=" +id, String.class);

        instance = eurekaClient.getNextServerFromEureka("rating", false);
        ip = instance.getIPAddr();
        String rating = restTemplate.getForObject("http://"+ip+":8080/rate/getbyitemid?id=" +id, String.class);


        return play + " ,{  \"stars\" : "+rating+"}";
    }

    @RequestMapping(value="/createnew", method = RequestMethod.POST)
    public String CreateNew( String name, String desc, Long theaterId){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("theaters", false);
        String ip = instance.getIPAddr();
        String play = restTemplate.postForObject("http://"+ip+":8080/play/createnew?name=" +name +"&desc=" +desc+ "&theaterId=" +theaterId, null, String.class);


        return null;
    }
}
