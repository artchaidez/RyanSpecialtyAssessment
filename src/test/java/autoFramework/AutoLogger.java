package autoFramework;

import jdk.jfr.Timespan;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;

public class AutoLogger {

    private TestContextLogger testContextLogger = new TestContextLogger();

    private int stepNumber = 1;

    private LocalDateTime testStartDate = LocalDateTime.now();
    private LocalDateTime testEndDate = LocalDateTime.now();
    private String suiteName;

    private String testName;

    private List<Timespan> timeTakenList = new ArrayList<Timespan>() {};

    public String GetCurrentTestName()
    {
        return testName;
    }

    public boolean IsCurrentTestPassed()
    {
        return false;
    }

    private void ResetTestStartTime()
    {
        testStartDate = LocalDateTime.now();
    }

    public void Debug(String message)
    {
        testContextLogger.Debug(message);
    }

    public void Info(String message)
    {
        testContextLogger.Info(message);
    }

    public void Error(String message)
    {
        testContextLogger.Error(message);
    }

    public void Warning(String message)
    {
        testContextLogger.Warning(message);
    }

    public void Pass(String message)
    {
        testContextLogger.Pass("   (PASS)   Got : " + message);
    }

    public void Pass(String message, String actualValue, Boolean includedGot)
    {
        if(includedGot == null)
            includedGot = true;

        String got = includedGot ? "Got: " : "";
        String defaultMessage = "   (PASS)  " + got + actualValue;

        testContextLogger.Pass(defaultMessage);
    }

    public void FailCompare(String message, Exception ex)
    {
        StringBuilder exceptionMessage = new StringBuilder(ex.getMessage());
        String splitOnTerm = "";
        String eMessage = "";

        int idx = exceptionMessage.lastIndexOf(("With configuration:\n - User declared types and members"));
        if (idx >= 0)
            eMessage = exceptionMessage.deleteCharAt(idx).toString();

        try
        {
            List<String> options = new ArrayList<String>() {};
            options.add("actual");
            options.add("subject");
            options.add("Object");

            for (String option : options)
            {
                splitOnTerm = MessageFormat.format("Expected {0} to be ", option);

                if(eMessage.contains(splitOnTerm))
                {
                    break;
                }

                String exp = eMessage.split(splitOnTerm)[1];
                exp = exp.split(", but found")[0];

                String got = eMessage.split(splitOnTerm)[1];
                got = got.split(", but found")[1].trim();

                testContextLogger.Fail("   (FAIL) Got: " + got);
                testContextLogger.Info("          Exp: " + exp);

            }
        } catch(Exception e)
        {
            testContextLogger.Fail(eMessage);
        }
    }

    public void Step(String message)
    {
        testContextLogger.Step(message,stepNumber);

        stepNumber++;
    }
}
