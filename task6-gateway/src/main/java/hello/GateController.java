package hello;

//import com.netflix.discovery.DiscoveryClient;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
        import java.util.Random;

/**
 * Created by carbo on 4/9/17.
 */
@RestController
public class GateController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping("/clients")
    public List<ServiceInstance> clients(@RequestParam(value="name") String name) {
        try {
            return this.discoveryClient.getInstances(name);
        }
        catch (Exception e){
            return null;
        }
    }

    @RequestMapping("/greeting")
    public String greeting() {
        Random rnd = new Random();
//
//        //my-app1
//        List<ServiceInstance> services1 = discoveryClient.getInstances("users");
//        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
//        String ip1 = service1.getInstanceInfo().getIPAddr();
//        String greeting1 = this.restTemplate.getForObject("http://"+ip1+":8080/greeting", String.class);

        //my-app2
//        List<ServiceInstance> services2 = discoveryClient.getInstances("theaters");
//        EurekaDiscoveryClient.EurekaServiceInstance service2 = (EurekaDiscoveryClient.EurekaServiceInstance) services2.get(rnd.nextInt(services2.size()));
//        String ip2 = service2.getInstanceInfo().getIPAddr();
       // JSONPObject greeting2 = this.restTemplate.getForObject("http://"+ip2+":8080/theater/greeting", JSONPObject.class);
        String greeting2 = this.restTemplate.postForObject("https://gurujsonrpc.appspot.com/guru",null, String.class);


        return String.format("Got  my-app2 answer: %s", greeting2.toString());
    }

    @RequestMapping("/greeting2")
    public String authGreeting() {

        return String.format("Got  my-app2 answer: %s", "auth");
    }

}
