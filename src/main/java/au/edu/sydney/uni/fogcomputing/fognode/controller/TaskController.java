package au.edu.sydney.uni.fogcomputing.fognode.controller;

import au.edu.sydney.uni.fogcomputing.fognode.service.CommandService;
import au.edu.sydney.uni.fogcomputing.fognode.service.QueueService;
import au.edu.sydney.uni.fogcomputing.fognode.service.TempFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private QueueService queueService;

    @Autowired
    private TempFileService tempFileService;

    @Autowired
    private CommandService commandService;


    private static final String INPUT_FILE = "input";
    private static final String OUTPUT_FILE = "output";

    @PostMapping("/task")
    public String doTask(@RequestParam("file") MultipartFile file) {
        System.out.println("start do Task!");
        TempFileService.Message message = null;
        try {
            message = tempFileService.storeTmpFile(file.getInputStream());
            System.out.println("finish store tmp file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        queueService.pushTask();

        int resultCode;
        if(message!=null){
            resultCode =  commandService.execute(message.dirStr + File.separator + INPUT_FILE, message.dirStr + File.separator + OUTPUT_FILE);
        }else{
            return "Spark Error";
        }

        queueService.popTask();

        System.out.println("resultCode: " + resultCode);
        if(resultCode == 0){
            File tmpFile = new File(message.dirStr + File.separator + OUTPUT_FILE);
            List lines;
            try {
                lines = FileUtils.readLines(tmpFile, "UTF-8");
                return lines.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Result Parser Error";
        }else{
            return "Internal Error";
        }

    }
}
