package org.redhat.examples;

import com.redhat.exhort.impl.ExhortApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;



public class Main {
    public static void main(String[] args) {
//        Logger log = LoggerFactory.getLogger("test-java-api");

        //uncomment these 2 lines to activate SL4J Jul Bridge, if used Library application using Java Logging(JUL) ==> System.getLogger
        // If client library using slf4j facade, like this example code, then keep it commented and the client caller ( this example) will choose
        // the implementation/binding/provider for logging framework(logback/log4j , etc) - the idea is that logs will be formatted and handled by the same
        // logging framework, and sl4fj gives you an option to let it happen in both cases:
        // 1. slf4j is used by both client and library - in this case the following 2 lines are redundant.
        // 2. slf4j is used only on the client and no in the library - in this case you need to use slf4j bridge for the logging framework being used by the library, in this case
        //    it's a bridge to intercept Java JUL LogRecord(s) by SLF4J Bridge handler (in the library), and route it to the SLF4J api (as you called slf4j logger.log directly, like here in the client).
        // more details at https://www.slf4j.org/legacy.html
//        SLF4JBridgeHandler.removeHandlersForRootLogger();
//        SLF4JBridgeHandler.install();
        Logger logger = LoggerFactory.getLogger(Main.class.getClass().getName());
        ExhortApi exhortApi = new ExhortApi();
        logger.info("after called Exhort API");
        logger.info(exhortApi.getExhortUrl());
    }
}