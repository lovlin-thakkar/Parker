package com.squad.parker;

import java.util.logging.Logger;

import com.squad.parker.constants.Constants;
import com.squad.parker.processor.command.CommandProcessor;
import com.squad.parker.processor.file.FileProcessor;
import com.squad.parker.utils.CommonUtils;

public class App {

    public static final Logger LOG = Logger.getLogger(App.class.getName());

    public static final FileProcessor fileProcessor = new FileProcessor(new CommandProcessor());

    public static void main(final String[] args) {
        if (args.length == 1) {
            boolean success = fileProcessor.process(args[0]);
            System.out.println(success ? "\nSuccessfully Processed :)" : "\nFailed :(");
        } else {
            CommonUtils.openWebpage(Constants.README_WEBPAGE_URL);
        }
    }
    
}
