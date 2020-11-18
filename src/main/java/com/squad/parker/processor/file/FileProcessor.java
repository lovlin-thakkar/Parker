package com.squad.parker.processor.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.squad.parker.constants.Constants;
import com.squad.parker.processor.Processor;
import com.squad.parker.utils.CommonUtils;

public class FileProcessor implements Processor<String, Boolean> {

    public static final Logger LOG = Logger.getLogger(FileProcessor.class.getName());

    private Processor<String, String> commandProcessor; 

    public FileProcessor(final Processor<String, String> commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public Boolean process(final String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            for (String command : bufferedReader.lines().collect(Collectors.toList())) {
                System.out.println(commandProcessor.process(command));
            }

            return true;

        } catch (final FileNotFoundException exception) {
            LOG.log(Level.SEVERE, String.format(Constants.FILE_NOT_FOUND_ERROR, filePath), exception);
            CommonUtils.openWebpage(Constants.README_WEBPAGE_URL);

        } catch (final IOException exception) {
            LOG.log(Level.SEVERE, exception.getMessage(), exception);

        }

        return false;
    }
}