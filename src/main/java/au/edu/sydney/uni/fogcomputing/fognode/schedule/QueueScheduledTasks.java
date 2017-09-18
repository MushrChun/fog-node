package au.edu.sydney.uni.fogcomputing.fognode.schedule;

import au.edu.sydney.uni.fogcomputing.fognode.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QueueScheduledTasks {

    @Autowired
    QueueService queueServie;

    @Scheduled(fixedRate = 5000)
    public void digestingTask(){
        queueServie.popTask();
    }
}
