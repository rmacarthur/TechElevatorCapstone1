package com.techelevator;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
public class Logger {//implements Closeable {
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa");
    private File log;
    public Logger(String fileName) {
        log = new File(fileName);  //create new file object
/*        try {
            log.createNewFile();   // create new file
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer = new PrintWriter(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
        // write new line to file
    }
    public void write(String logMessage){
        PrintWriter printWriter = null;
        if (log.exists()) { // if the logfile already exists, we need to append
            try {
                // anonymous object of type FileWriter
                // FileWriter lets us pass in a boolean for appending
                FileOutputStream outputStream = new FileOutputStream(this.log, true);
                printWriter = new PrintWriter(outputStream);
                printWriter.println(logMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {  // if file doesn't already exist, we just need to finish setting up writer
            try {
                printWriter = new PrintWriter(this.log);
                printWriter.println(logMessage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        printWriter.flush();
        printWriter.close();
    }
    /* public void writeLine (String formattedString){
           writer.println(getTimeStamp() + formattedString);
           writer.flush();
       }*/
    public String getTimeStamp () {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }
}