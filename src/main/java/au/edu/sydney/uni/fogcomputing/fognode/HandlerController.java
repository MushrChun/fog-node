package au.edu.sydney.uni.fogcomputing.fognode;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HandlerController {

    @RequestMapping("/handler")
    String handler() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "handler function finished the work";
    }

}