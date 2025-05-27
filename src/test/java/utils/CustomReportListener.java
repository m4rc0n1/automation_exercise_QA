package utils;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import java.util.List;

public class CustomReportListener implements IReporter {
    @Override
    public  void generateReport(List<XmlSuite> xmlSuites,List<ISuite> suites,String outputDirectory){
        for(ISuite suite:suites){
            System.out.println("Generating report for suit: " + suite.getName());
            suite.getResults().forEach((testName,result)->{
                System.out.println("Test name: " + testName);
                System.out.println("Passed test: " + result.getTestContext().getPassedTests().size());
                System.out.println("Failed test: " + result.getTestContext().getFailedTests().size());
            });

        }
    }

}
