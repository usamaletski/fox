package stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import enums.UserMail;
import supportMethods.EmailNotifications;
import supportMethods.FileRead;
import supportMethods.MySql;
import testRunner.TestRunner;
import webDriver.Driver;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Hooks {
    private static Boolean runOnce = false;

    @Before("@removeSearchIndex")
    public static void removeSearchIndex() throws IOException, InterruptedException {
        System.out.println("\nINFO: Removing Search Indexes...");
        long startTime, elapsedTime;
        startTime = System.currentTimeMillis();
        String rmIndexCmd = "ssh root@" + TestRunner.config.get("siteHost") + " '/etc/init.d/*******lucene stop; rm -r /var/log/tomcat8/index; /etc/init.d/*******lucene start; sleep 10'";
        Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", rmIndexCmd});
        int exitVal = p.waitFor();
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nINFO: Removing Search Indexes has been finished in " + elapsedTime + "ms (with exit code [" + exitVal + "])");
    }

    private static void processExecutor(String[] processCmd) throws IOException, InterruptedException {
        Process pb = Runtime.getRuntime().exec(processCmd);
        BufferedReader reader = new BufferedReader(new InputStreamReader(pb.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitVal = pb.waitFor();
        System.out.println("\n Process execution exit code: [" + exitVal + "]");
    }

    @Before("@restoreSampleData")
    public static void restoreSampleData() throws Exception {
        System.out.println("\nINFO: Restoring sample data from " + System.getProperty("user.dir") + "/sampleData/sample_data.sql ...");
        long startTime, elapsedTime;
        startTime = System.currentTimeMillis();
        String restoreCmd = "mysql --user='" + TestRunner.mysql.get("dbUname") +
                "' --password='" + TestRunner.mysql.get("dbPwd") +
                "' -h " + TestRunner.mysql.get("dbHost") +
                " -P " + TestRunner.mysql.get("dbPort") +
                " d_qa < \"" + System.getProperty("user.dir") + "/sampleData/sample_data.sql\"";
        Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", restoreCmd});
        int exitVal = p.waitFor();
        if (exitVal != 0) {
            throw new Exception("Sample data was not restored!");
        }
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nINFO: Restoring sample data has been finished in " + elapsedTime + "ms.");
    }

    @Before("@emailsCleanup")
    public static void cleanupEmails() throws MessagingException {
        for (UserMail umail : UserMail.values()) {
            System.out.println("\nClean up emails for user: " + umail.toString().toLowerCase());
            String acc = TestRunner.emails.get(umail.toString());
            EmailNotifications.delete(acc, TestRunner.emails.get("emailPwd"));
        }
    }

    @Before(order = 1)
    public void beforeAll() throws IOException {
        if (!runOnce) {
            TestRunner.config = FileRead.applicationProperties();
            TestRunner.users = FileRead.userProperties();
            TestRunner.drivers = FileRead.driverProperties();
            TestRunner.mysql = FileRead.mysqlProperties();
            TestRunner.emails = FileRead.emailProperties();
        }
    }

    @Before
    public void before(Scenario scenario) {
        TestRunner.scenario = scenario;
    }

    @Before("@resetOrg1")
    public void resetOrg1() throws SQLException, ClassNotFoundException {
        MySql.QueryExecution("DELETE FROM ******* WHERE **** = ********");
    }

    @After("@rollbackCrossUser")
    public void rollbackCrossUser() throws SQLException, ClassNotFoundException {
        MySql.QueryExecution("insert into ****** (****id, ****_id, read_only, ***, **, ***, *****) " + "values (*****, ******, '**', '**', '*', *, *)");
        MySql.QueryExecution("insert into user_department (***, **, STATUS) values (*****, *****, *****)");
    }

    @Before("@changeDate")
    public void changeDate() throws IOException, InterruptedException {
        System.out.println("\nINFO: Changing client's time to 09 FEB 2018 18:30...");
        long startTime, elapsedTime;
        startTime = System.currentTimeMillis();
        String[] changeLocal = {"/bin/bash", "-c", "sudo " + System.getProperty("user.dir") + "/bash-support/change_date_local.sh"};
        processExecutor(changeLocal);
        String[] changeServer = {"/bin/bash", "-c", System.getProperty("user.dir") + "/bash-support/change_date_server.sh"};
        processExecutor(changeServer);
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nINFO: Changing date process has been finished in " + elapsedTime + "ms");
    }

    @After("@changeDate")
    public void restoreDate() throws IOException, InterruptedException {
        System.out.println("\nINFO: Restoring client's time to actual state...");
        long startTime, elapsedTime;
        startTime = System.currentTimeMillis();
        String[] restoreLocal = {"/bin/bash", "-c", "sudo " + System.getProperty("user.dir") + "/bash-support/restore_date_local.sh"};
        processExecutor(restoreLocal);
        String[] restoreServer = {"/bin/bash", "-c", System.getProperty("user.dir") + "/bash-support/restore_date_server.sh"};
        processExecutor(restoreServer);
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nINFO: Restoring date process has been finished in " + elapsedTime + "ms");
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            Driver.embedScreenshot();
        }
    }
}