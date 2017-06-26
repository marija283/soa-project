package hello;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.bouncycastle.crypto.tls.ConnectionEnd.client;

/**
 * Created by Marija on 6/17/2017.
 */

@RestController
@RequestMapping(value = "/theater" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class TheaterContorller {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;


    @RequestMapping("/serviceinfo")
    public String serviceInfo(){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("theaters", false);
        return instance.getIPAddr();
    }


    @RequestMapping("/get")
    public String getTheaters(){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("theaters", false);
        String ip = instance.getIPAddr();
        String getAll = this.restTemplate.getForObject("http://"+ip+":8080/theater/getall", String.class);
        return getAll;
    }

    @RequestMapping("/get/{id}")
    public String getTheater(@PathVariable Long id){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("theaters", false);
        String ip = instance.getIPAddr();
        String getById = this.restTemplate.getForObject("http://"+ip+":8080/theater/getbyid?id=" + id, String.class);

        return getById;
    }


    @RequestMapping("/getplays/{id}")
    public String getPlays(@PathVariable Long id){
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("theaters", false);
        String ip = instance.getIPAddr();
        String getAll = this.restTemplate.getForObject("http://"+ip+":8080/theater/getplays?id=" +id, String.class);
        return getAll;
    }
}
