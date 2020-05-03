package post;

import com.sun.mail.imap.protocol.FLAGS;

import javax.mail.*;
import javax.mail.search.FlagTerm;

import java.util.Properties;

public class Post {
    public static void check(String host, String storeType, String user,
                             String password)
    {
        try {


            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");


            Session emailSession = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    user, password);
                        }
                    });


            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);


            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            //Flags seen = new Flags(Flags.Flag.SEEN);
            //FlagTerm unSeen = new FlagTerm(seen, true);

            Message[] messages = emailFolder.getMessages();
            System.out.println("количество писем - " + messages.length);
            System.out.println(emailFolder.getUnreadMessageCount());

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0].toString());

            }
            //123David


            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String host = "pop.yandex.ru";
        String mailStoreType = "pop";
        String username = "prosperiansun@yandex.ru";
        String password = "Dd31415926";

        check(host, mailStoreType, username, password);

    }
    }

