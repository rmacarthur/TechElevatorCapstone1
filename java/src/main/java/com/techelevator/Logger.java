package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Logger {
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa");
	private File log;
    private PrintWriter writer;



    public Logger(String fileName) {

        log = new File(fileName);  //create new file object

        try {
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
    }

    // write new line to file
    public void writeLine(String formattedString) {

        writer.println(getTimeStamp() + formattedString);
        writer.flush();

    }
    public String getTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }
}
