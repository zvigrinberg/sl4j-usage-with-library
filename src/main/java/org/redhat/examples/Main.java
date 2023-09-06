package org.redhat.examples;

import com.redhat.exhort.impl.ExhortApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;



public class Main {
    public static void main(String[] args) {
//        Logger log = LoggerFactory.getLogger("test-java-api");

        //uncomment these 2 lines to activate SL4J Jul Bridge, if used Library application using Java Logging(JUL) ==> System.getLogger
//        SLF4JBridgeHandler.removeHandlersForRootLogger();
//        SLF4JBridgeHandler.install();
        Logger logger = LoggerFactory.getLogger(Main.class.getClass().getName());
        ExhortApi exhortApi = new ExhortApi();
        logger.info("after called Exhort API");
        logger.info(exhortApi.getExhortUrl());
    }
}