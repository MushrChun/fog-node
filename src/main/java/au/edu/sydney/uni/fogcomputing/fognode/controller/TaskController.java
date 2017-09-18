package au.edu.sydney.uni.fogcomputing.fognode.controller;

import au.edu.sydney.uni.fogcomputing.fognode.service.CommandService;
import au.edu.sydney.uni.fogcomputing.fognode.service.QueueService;
import au.edu.sydney.uni.fogcomputing.fognode.service.TempFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TaskController {
    @Autowired
    private QueueService queueService;

    @Autowired
    private TempFileService tempFileService;

    @Autowired
    private CommandService commandService;


    @PostMapping("/task")
    public String doTask(@RequestParam("file") MultipartFile file) {
        try {
            TempFileService.Message message = tempFileService.storeTmpFile(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        queueService.pushTask();
        String result =  commandService.execute();
        queueService.popTask();
        return result;
    }
}
