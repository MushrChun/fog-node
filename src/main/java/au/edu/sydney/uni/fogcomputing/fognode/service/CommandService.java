package au.edu.sydney.uni.fogcomputing.fognode.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Service("Command")
public class CommandService {


    private ProcessBuilder builder = new ProcessBuilder();


    private String content;

    public String execute(){
        builder.command("sh", "-c", "ls -la");
        builder.directory(new File(System.getProperty("user.home")));
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MyStream myStream = new MyStream(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(myStream);
        try {
            int exitCode = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "true";
    }


    private static class MyStream implements Runnable {

        private InputStream inputStream;
        private Consumer<String> consumer;

        public MyStream(InputStream inputStream, Consumer<String> consumer){
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);

        }
    }

}
