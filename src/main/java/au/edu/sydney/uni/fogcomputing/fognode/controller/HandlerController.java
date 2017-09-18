package au.edu.sydney.uni.fogcomputing.fognode.controller;

import au.edu.sydney.uni.fogcomputing.fognode.service.QueueService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * This controller is for mimic 3 tiers structure
 */
@RestController
public class HandlerController {

    @Autowired
    private QueueService queueService;

    private static String url = "http://54.252.194.114:8080/handler";
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    @RequestMapping("/handler")
    public String handler() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(queueService.pushTask()){
            return "task is handled in fog node! Current task load is " + queueService.getCurrentTasksAmount();
        }else{

            HttpGet httpGet = new HttpGet(url);
            String resString = null;
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                resString = EntityUtils.toString(entity);

                response.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return "task is transferred to cloud node for handling and the response from cloud node is: " + resString;
        }
    }

}