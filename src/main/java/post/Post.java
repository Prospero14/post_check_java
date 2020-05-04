package post;

import javax.mail.*;
import javax.mail.search.FlagTerm;

import java.util.Properties;

public class Post {
    public static void check(String host, String storeType, String user,
                             String password) {
        try {


            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            //properties.put("mail.store.protocol", "imaps");

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

            //Message[] messages = emailFolder.getMessages();
            Flags seen = new Flags(Flags.Flag.RECENT);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);


             Message messages[] = emailFolder.search(unseenFlagTerm);
            System.out.println("количество писем - " + messages.length);


            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0].toString());

            }
            //emailFolder.close(true);
            //store.close();

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
        String mailStoreType = "pop3s";
        String username = "mail@yandex.ru";
        String password = "passwrd";

        check(host, mailStoreType, username, password);

    }
    }

