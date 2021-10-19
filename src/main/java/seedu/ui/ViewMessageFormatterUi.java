package seedu.ui;

import seedu.contact.Contact;

public class ViewMessageFormatterUi {
    public static String viewNameFormatter(Contact contact) {
        if (contact.getName() == null) {
            return "";
        }
        return contact.getName();
    }

    public static String viewGithubFormatter(Contact contact) {
        if (contact.getGithub() == null) {
            return "";
        }
        return "\nGithub:   github.com/" + contact.getGithub();
    }

    public static String viewEmailFormatter(Contact contact) {
        if (contact.getEmail() == null) {
            return "";
        }
        return "\nEmail:    " + contact.getEmail();
    }

    public static String viewTelegramFormatter(Contact contact) {
        if (contact.getTelegram() == null) {
            return "";
        }
        return "\nTelegram: t.me/" + contact.getTelegram();
    }

    public static String viewLinkedinFormatter(Contact contact) {
        if (contact.getLinkedin() == null) {
            return "";
        }
        return "\nLinkedIn: linkedin.com/in/" + contact.getLinkedin();
    }

    public static String viewTwitterFormatter(Contact contact) {
        if (contact.getTwitter() == null) {
            return "";
        }
        return "\nTwitter:  twitter.com/" + contact.getTwitter();
    }

}
