package au.edu.sydney.uni.fogcomputing.fognode.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:config.properties")
@Service("Queue")
public class QueueService {

    @Value("${queue.max_capacity}")
    private String MAX_CAPACITY;

    private int currentTasks = 0;
    private int maxCapacity;


    @PostConstruct
    public void init() {
        maxCapacity = Integer.parseInt(MAX_CAPACITY);
    }

    public boolean pushTask(){
        if(currentTasks < maxCapacity){
            currentTasks++;
            return true;
        }else{
            return false;
        }
    }

    public boolean popTask(){
        if(currentTasks > 0){
            currentTasks--;
            return true;
        }else{
            return false;
        }
    }

    public int getCurrentTasksAmount(){
        return currentTasks;
    }
}
