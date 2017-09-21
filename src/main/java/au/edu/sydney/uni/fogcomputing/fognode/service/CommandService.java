package au.edu.sydney.uni.fogcomputing.fognode.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Service("Command")
public class CommandService {


    private ProcessBuilder builder = new ProcessBuilder();


    private String content;

    public int execute(String inputFile, String outputFile){
        System.out.println("start execute command!");
        builder.command("/home/cshe6391/Tools/spark-2.2.0-bin-hadoop2.7/bin/spark-submit",
                "--master",
                "spark://pc-4e55-0.it.usyd.edu.au:7077",
                "/home/cshe6391/fog-space/kmeans_example.py",
                inputFile,
                outputFile);
        builder.directory(new File(System.getProperty("user.home") + File.separator + "fog-space"));
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MyStream myStream = new MyStream(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(myStream);

        int exitCode = 1;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("finish execute command!");

        return exitCode;
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
