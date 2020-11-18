package com.squad.parker.utils;

import java.awt.Desktop;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonUtils {

    private CommonUtils() {}

    public static final Logger LOG = Logger.getLogger(CommonUtils.class.getName());

    public static void openWebpage(final String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (final Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage(), exception);
        }
    }
    
}