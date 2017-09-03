package hello.task;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.client.http.HttpResponse;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Marija on 9/3/2017.
 */
public class TheaterController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping(value="/createnew", method = RequestMethod.POST)
    public String CreateNew( String name, String desc, Long theaterId){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("theaters", false);
        String ip = instance.getIPAddr();
        String theater = restTemplate.postForObject("http://"+ip+":8080/theater/createnewtheater?name=" + name, null, String.class);


        return theater;
    }
}
