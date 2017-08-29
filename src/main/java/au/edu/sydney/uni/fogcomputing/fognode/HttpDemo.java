package au.edu.sydney.uni.fogcomputing.fognode;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpDemo {

    private static String url = "http://54.252.194.114:8080/handler";
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args){
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseStr = EntityUtils.toString(entity);
            System.out.println(responseStr);

            response.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
