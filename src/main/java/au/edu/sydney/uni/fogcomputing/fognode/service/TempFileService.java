package au.edu.sydney.uni.fogcomputing.fognode.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service("TempFile")
public class TempFileService {

    public final static  String basePath = "temp";

    public TempFileService(){
        File dir = new File(basePath);
        if(!dir.exists()){
         dir.mkdir();
        }
    }

    public Message storeTmpFile(InputStream inputStream){

        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        String tmpDirStr = basePath + File.separator + uuidStr;
        File tmpDir = new File(tmpDirStr);
        tmpDir.mkdir();
        String tmpFileStr = tmpDirStr + File.separator + "input";
        File tmpFile = new File(tmpFileStr);
        try {
            FileUtils.copyInputStreamToFile(inputStream, tmpFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message message = new Message(true, tmpDirStr);

        return message;
    }

    public class Message{
        public boolean status;
        public String fileName;

        public Message(boolean status, String fileName){
            this.status = status;
            this.fileName = fileName;
        }
    }
}
