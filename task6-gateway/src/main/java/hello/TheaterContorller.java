package hello;

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

import java.util.List;
import java.util.Random;

/**
 * Created by Marija on 6/17/2017.
 */

@RestController
@RequestMapping(value = "/theater" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class TheaterContorller {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/get/{id}")
    public ResponseEntity<?> getTheater(@PathVariable Long id){
        Random rnd = new Random();
        List<ServiceInstance> services2 = discoveryClient.getInstances("theaters");
        EurekaDiscoveryClient.EurekaServiceInstance service2 = (EurekaDiscoveryClient.EurekaServiceInstance) services2.get(rnd.nextInt(services2.size()));
        String ip2 = service2.getInstanceInfo().getIPAddr();
        String greeting2 = this.restTemplate.getForObject("http://"+ip2+":8080/theater/getbyid?id=" + id, String.class);


        return null;
    }
}
