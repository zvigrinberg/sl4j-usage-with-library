package org.redhat.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.exhort.Api;
import com.redhat.exhort.api.AnalysisReport;
import com.redhat.exhort.impl.ExhortApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


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
        try {
            ObjectMapper om = new ObjectMapper();
//            System.setProperty("EXHORT_SNYK_TOKEN","ee64316c-a4ba-4ca0-a785-18cb05ed3f25");
//            CompletableFuture<AnalysisReport> content = exhortApi.componentAnalysis("package.json", Files.readAllBytes(Path.of("/home/zgrinber/git/test-java-api/tests/backstage/package.json")));
//            CompletableFuture<byte[]> content = exhortApi.stackAnalysisHtml("/home/zgrinber/git/test-java-api/tests/backstage/package.json");
                System.out.println("Print Stack analysis json:");
                AnalysisReport analysisReport = exhortApi.stackAnalysis("/home/zgrinber/git/fabric8-analytics-lsp-server/package.json").get();
                System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(analysisReport));
               System.out.println("Print Component analysis path:");
                analysisReport = exhortApi.componentAnalysis("/home/zgrinber/git/fabric8-analytics-lsp-server/package.json").get();
                System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(analysisReport));
                byte[] bytes = Files.readAllBytes(Path.of("/home/zgrinber/git/fabric8-analytics-lsp-server/package.json"));
                System.out.println("Print Component analysis content:");
                analysisReport = exhortApi.componentAnalysis("package.json",bytes).get();
                System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(analysisReport));
                System.out.println("Print Stack analysis html:");
                String html = new String(exhortApi.stackAnalysisHtml("/home/zgrinber/git/fabric8-analytics-lsp-server/package.json").get());
                System.out.println(html);
                System.out.println("Print Stack analysis mixed:");
                Api.MixedReport mixedReport = exhortApi.stackAnalysisMixed("/home/zgrinber/git/fabric8-analytics-lsp-server/package.json").get();
                System.out.println(mixedReport.json);
                System.out.println(new String(mixedReport.html));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
              catch (Exception e) {
                throw new RuntimeException(e);
            }





    }
}