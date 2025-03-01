package com.example.backend_tutorial.integration.momo.shared.utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class LogUtils {

    static Logger logger = Logger.getLogger(LogUtils.class);

    static {
        BasicConfigurator.configure(); // Cấu hình log4j khi class được load
    }

    public static void info(String serviceCode, Object object) {
        logger.info(new StringBuilder().append("[").append(serviceCode).append("]: ").append(object));
    }

    public static void info(Object object) {
        logger.info(object);
    }

    public static void debug(Object object) {
        logger.debug(object);
    }

    public static void error(Object object) {
        logger.error(object);
    }

    // public static void error(Object object) {
    // logger.error(object);
    // }

    public static void warn(Object object) {
        logger.warn(object);
    }
}
