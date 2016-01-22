/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils.log;

import java.util.logging.Level;
import org.apache.log4j.Logger;


/**
 *
 * @author Nabil
 */
public class SystemLogger {

    private static Logger logger;

    static {
        try {
            logger = Logger.getLogger(SystemLogger.class.getName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Logger getLogger(String callingClassName) {
        return Logger.getLogger(callingClassName);
    }

    private SystemLogger() {
    }

    private SystemLogger(String callingClassName) {
        logger = Logger.getLogger(callingClassName);

    }

    /**
     * Write Error Message
     *
     * @param message String
     */
    public void error(String message) {

        try {
            logger.error(getMessage(message));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Error Message
     *
     * @param message String
     * @param e Throwable
     */
    public void error(String message, Throwable e) {

        try {
            logger.error(getMessage(message), e);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Warning Message
     *
     * @param message String
     */
    public void warn(String message) {

        try {
            logger.warn(message);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Warning Message
     *
     * @param message String
     * @param e Throwable
     */
    public void warn(String message, Throwable e) {

        try {
            logger.warn(getMessage(message), e);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Info Message
     *
     * @param message String
     */
    public void info(String message) {
        try {
            logger.info(getMessage(message));

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Info Message
     *
     * @param message String
     * @param e Throwable
     */
    public void info(String message, Throwable e) {

        try {
            logger.info(getMessage(message), e);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Debug Message
     *
     * @param message String
     */
    public void debug(String message) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug(getMessage(message));
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Debug Message
     *
     * @param message String
     * @param w Throwable
     */
    public void debug(String message, Throwable e) {

        try {
            if (logger.isDebugEnabled()) {
                logger.debug(getMessage(message), e);
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Trace Message
     *
     * @param message String
     */
    public void trace(String message) {
        try {
            if (logger.isTraceEnabled()) {
                logger.trace(getMessage(message));
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Trace Message
     *
     * @param message String
     * @param e Throwable
     */
    public void trace(String message, Throwable e) {

        try {
            if (logger.isTraceEnabled()) {
                logger.trace(getMessage(message), e);
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Fatal Error Message
     *
     * @param message String
     * @param e Throwable
     */
    public void fatal(String message, Throwable e) {
        try {
            logger.fatal(getMessage(message), e);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Fatal Error Message
     *
     * @param message String
     */
    public void fatal(String message) {
        try {
            logger.fatal(getMessage(message));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SystemLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write Log Message
     *
     * @param message String
     * @param e Throwable
     */
    public void log(String message, Throwable e) {
        error(message, e);
    }

    /**
     * Write Log Message
     *
     * @param message String
     */
    public void log(String message) {
        info(message);
    }

    private String getMessage(String message) {
        return message;
    }
}
