package com.squad.parker;

import java.util.logging.Logger;

import com.squad.parker.processor.command.CommandProcessor;
import com.squad.parker.processor.file.FileProcessor;
import com.squad.parker.utils.CommonUtils;

public class App {

    public static final Logger LOG = Logger.getLogger(App.class.getName());

    public static final FileProcessor fileProcessor = new FileProcessor(new CommandProcessor());

    public static void main(final String[] args) {
        if (args.length == 1) {
            fileProcessor.processFile(args[0]);
        } else {
            CommonUtils.openWebpage("https://github.com/lovlin-thakkar/Parker/blob/main/README.md");
        }
    }
    
}
