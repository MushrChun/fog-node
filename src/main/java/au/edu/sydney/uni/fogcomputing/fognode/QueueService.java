package au.edu.sydney.uni.fogcomputing.fognode;

import org.springframework.stereotype.Service;

@Service("Queue")
public class QueueService {

    private static int MAX_CAPACITY = 2;

    private int currentTasks = 0;

    public boolean pushTask(){
        if(currentTasks < MAX_CAPACITY){
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
