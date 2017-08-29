package au.edu.sydney.uni.fogcomputing.fognode;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String home() {
        return "Hello, this is Fog Node speaking";
    }

}