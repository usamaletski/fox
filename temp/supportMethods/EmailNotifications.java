package supportMethods;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import testRunner.TestRunner;
import webDriver.Driver;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailNotifications {
    private static final long NANOS_PER_SEC = 1000L * 1000 * 1000;
    private static String content = "";
    private static long currentTime;
    private static int emailRetriveSec = Integer.parseInt(TestRunner.config.get("emailRetriveSec"));

    public static boolean checkMailSubject(String uname, String pwd, String textToVerify) throws MessagingException {
        long startTime = System.nanoTime();

        //Create POP3 store object and connect with the server
        Store store = getEmailStore(uname, pwd);
        //Create folder object and open it in read-only mode
        Folder emailFolderObj = store.getFolder("inbox");
        emailFolderObj.open(Folder.READ_ONLY);

        while (true) {
            try {
                currentTime = System.nanoTime();

                //Fetch messages from the folder
                Message[] messageobjs = emailFolderObj.getMessages();
                //Handling case when mailbox is empty
                if (messageobjs.length == 0) {
                    if ((currentTime - startTime) >= emailRetriveSec * NANOS_PER_SEC) {
                        return false;
                    }
                }
                //Verify content of the latest email
                Message latestMsg = messageobjs[messageobjs.length - 1];
                content = latestMsg.getSubject();

                assertThat(content).contains(textToVerify);

                TestRunner.scenario.write("\nLatest message subject is: " + content);
                return true;
            } catch (AssertionError err) {
                if ((currentTime - startTime) >= emailRetriveSec * NANOS_PER_SEC) {
                    TestRunner.scenario.write("\nLast message subject: \"" + content + "\" doesn't contains text \"" + textToVerify + "\"");
                    //Now close all the objects
                    emailFolderObj.close(false);
                    store.close();
                    return false;
                }
            } catch (Exception exp) {
                if ((currentTime - startTime) >= emailRetriveSec * NANOS_PER_SEC) {
                    //Now close all the objects
                    emailFolderObj.close(false);
                    store.close();
                    exp.printStackTrace();
                    return false;
                }
            }
        }
    }

    public static boolean checkMailContent(String uname, String pwd, String textToVerify) throws MessagingException {
        TestRunner.scenario.write("\nINFO: Searching for text '" + textToVerify + "'");

        long startTime = System.nanoTime();

        //Create POP3 store object and connect with the server
        Store store = getEmailStore(uname, pwd);
        //Create folder object and open it in read-only mode
        Folder emailFolderObj = store.getFolder("inbox");
        emailFolderObj.open(Folder.READ_ONLY);

        while (true) {
            try {
                currentTime = System.nanoTime();

                //Fetch messages from the folder
                Message[] messageobjs = emailFolderObj.getMessages();
                //Handling case when mailbox is empty
                if (messageobjs.length == 0) {
                    if ((currentTime - startTime) >= emailRetriveSec * NANOS_PER_SEC) {
                        return false;
                    }
                }
                //Verify content of the latest email
                Message latestMsg = messageobjs[messageobjs.length - 1];
                Object mp;
                try {
                    mp = latestMsg.getContent();
                    if (mp instanceof String) {
                        content = (String) mp;
                    } else if (mp instanceof Multipart) {
                        Multipart mpp = (Multipart) mp;
                        for (int count = 0; count < mpp.getCount(); count++) {
                            BodyPart bodyPart = mpp.getBodyPart(count);

                            String disposition = bodyPart.getDisposition();

                            if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                                TestRunner.scenario.write("Mail have some attachment");

                                DataHandler handler = bodyPart.getDataHandler();
                                TestRunner.scenario.write("file name : " + handler.getName());
                            } else {
                                content = getText(bodyPart);  // the changed code
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assertThat(content).contains(textToVerify);

                TestRunner.scenario.write("\nLatest message body is: " + Jsoup.parse(content).body().text());
                return true;
            } catch (AssertionError err) {
                if ((currentTime - startTime) >= emailRetriveSec * NANOS_PER_SEC) {
                    TestRunner.scenario.write("\nLast message body content: \"" + Jsoup.parse(content).body().text() + "\" doesn't contains text \"" + textToVerify + "\"");
                    //Now close all the objects
                    emailFolderObj.close(true);
                    store.close();
                    return false;
                }
            } catch (Exception exp) {
                if ((currentTime - startTime) >= emailRetriveSec * NANOS_PER_SEC) {
                    exp.printStackTrace();
                    //Now close all the objects
                    emailFolderObj.close(true);
                    store.close();
                    return false;
                }
            }
        }
    }

    private static String getText(Part p) throws
            MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            return (String) p.getContent();
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart) p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }
        return null;
    }

    public static void sendConfirmation(String uname, String pwd) throws MessagingException {
        String emailForConfirm = Objects.requireNonNull(parseEmailForConfirmAndRejectLinks(uname, pwd))[0];
        sendMessageTo(uname, pwd, emailForConfirm);
    }

    public static void sendRejection(String uname, String pwd) throws MessagingException {
        String emailForReject = Objects.requireNonNull(parseEmailForConfirmAndRejectLinks(uname, pwd))[0];
        sendMessageTo(uname, pwd, emailForReject);
    }

    private static void sendMessageTo(String uname, String pwd, String emailTo) throws MessagingException {
        String emailSubject = "Cucumber scenario: " + TestRunner.scenario.getName();
        String emailBody = "Cucumber automation scenario: " + TestRunner.scenario.getName() + "\n" + Driver.timeStamp;
        TestRunner.scenario.write("\nSending email " +
                "\nFROM: " + uname + "\\" + pwd +
                "\nTO: " + emailTo +
                "\nSUBJECT: " + emailSubject +
                "\nBODY: " + emailBody + "\n");

        Properties props = getEmailProperties();
        props.setProperty("mail.user", uname);
        props.setProperty("mail.password", pwd);

        Session emailSession = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(uname, pwd);
            }
        });
        Message message = new MimeMessage(emailSession);
        message.setFrom(new InternetAddress(uname));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
        message.setSubject(emailSubject);
        message.setText(emailBody);
        Transport.send(message);

    }

    public static String[] parseEmailForConfirmAndRejectLinks(String uname, String pwd) throws MessagingException {
        Store store = getEmailStore(uname, pwd);

        //Create folder object and open it in read-only mode
        Folder emailFolderObj = store.getFolder("inbox");
        emailFolderObj.open(Folder.READ_ONLY);

        String[] actions = new String[2];
        int i = 0;
        try {

            //Fetch messages from the folder
            Message[] messageobjs = emailFolderObj.getMessages();

            Message latestMsg;
            try {
                latestMsg = messageobjs[messageobjs.length - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                TestRunner.scenario.write("\nWARNING: Seems like the inbox is empty!\n");
                e.printStackTrace();
                return null;
            }

            Object mp;
            try {
                mp = latestMsg.getContent();
                if (mp instanceof String) {
                    content = (String) mp;
                } else if (mp instanceof Multipart) {
                    Multipart mpp = (Multipart) mp;
                    for (int count = 0; count < mpp.getCount(); count++) {
                        MimeBodyPart bp = (MimeBodyPart) mpp.getBodyPart(count);
                        InputStream fileNme = bp.getInputStream();
                        StringWriter writer = new StringWriter();
                        IOUtils.copy(fileNme, writer, "UTF-8");
                        content += writer.toString();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Now close all the objects
            emailFolderObj.close(false);
            store.close();

            content = content.replace("=", "").replace("\n", "").replace("\r", "");
            TestRunner.scenario.write("\nLatest message source is: \n" + content);
            Pattern p = Pattern.compile(Pattern.quote("mailto:") + "(.*?)" + Pattern.quote("?"));
            Matcher m = p.matcher(content);

            TestRunner.scenario.write("\n\nFound confirmation and rejection emails: ");
            while (m.find()) {
                actions[i] = m.group(1);
                TestRunner.scenario.write("\n" + m.group(1));
                i++;
                if (i == 2) {
                    break;
                }
            }

            return actions;
        } catch (MessagingException e) {
            emailFolderObj.close(false);
            store.close();
            e.printStackTrace();
            return null;
        }
    }

    public static void delete(String uname, String pwd) throws MessagingException {
        //Create POP3 store object and connect with the server
        Store store = getEmailStore(uname, pwd);
        // create the folder object and open it
        Folder emailFolder = store.getFolder("inbox");
        emailFolder.open(Folder.READ_WRITE);

        try {
            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            for (Message msj : messages) {
                String subject = msj.getSubject();
                // set the DELETE flag to true
                msj.setFlag(Flags.Flag.DELETED, true);
                TestRunner.scenario.write("\nMarked DELETE for message: " + subject);
            }
            // expunges the folder to remove messages which are marked deleted
        } catch (Exception e) {
            e.printStackTrace();
        }
        emailFolder.close(true);
        store.close();
    }

    private static Properties getEmailProperties() {
        Properties emailProperties = new Properties();
        emailProperties.put("mail.smtp.auth", "false");
        emailProperties.put("mail.smtp.host", "citest.desk-net.com");
        emailProperties.put("mail.smtp.port", "25");
        emailProperties.put("mail.smtp.starttls.enable", "false");
        emailProperties.put("mail.smtp.ssl.enable", "false");
        return emailProperties;
    }

    private static Session getEmailSession() {
        return Session.getDefaultInstance(getEmailProperties());
    }

    private static Store getEmailStore(String userName, String userPassword) throws MessagingException {
        Session emailSession = getEmailSession();
        TestRunner.scenario.write("\nConnecting to Email server as " + userName + " " + userPassword);
        Store store = emailSession.getStore("imap");
        store.connect("citest.desk-net.com", userName, userPassword);
        return store;
    }
}