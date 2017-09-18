package au.edu.sydney.uni.fogcomputing.fognode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is for checking the status of node server
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "Hello, this is Fog Node speaking";
    }

}