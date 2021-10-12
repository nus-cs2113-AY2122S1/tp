package seedu.ui;

import seedu.contact.Contact;

public class ViewMessageFormatterUi {
    public static String viewNameFormatter(Contact contact) {
        try {
            if (contact.getName() == null | contact.getName().equals("null")) {
                return "";
            } else {
                assert (!contact.getName().equals("null"));
                return contact.getName();
            }
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static String viewGithubFormatter(Contact contact) {
        try {
            if (contact.getGithub() == null | contact.getGithub().equals("null")) {
                return "";
            } else {
                assert (!contact.getGithub().equals("null"));
                return "\nGithub:   github.com/" + contact.getGithub();
            }
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static String viewEmailFormatter(Contact contact) {
        try {
            if (contact.getEmail() == null | contact.getEmail().equals("null")) {
                return "";
            } else {
                assert (!contact.getEmail().equals("null"));
                return "\nEmail:    " + contact.getEmail();
            }
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static String viewTelegramFormatter(Contact contact) {
        try {
            if (contact.getTelegram() == null | contact.getTelegram().equals("null")) {
                return "";
            } else {
                assert (!contact.getTelegram().equals("null"));
                return "\nTelegram: t.me/" + contact.getTelegram();
            }
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static String viewLinkedinFormatter(Contact contact) {
        try {
            if (contact.getLinkedin() == null | contact.getLinkedin().equals("null")) {
                return "";
            } else {
                assert (!contact.getLinkedin().equals("null"));
                return "\nLinkedIn: linkedin.com/in/" + contact.getLinkedin();
            }
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static String viewTwitterFormatter(Contact contact) {
        try {
            if (contact.getTwitter() == null | contact.getTwitter().equals("null")) {
                return "";
            } else {
                assert (!contact.getTwitter().equals("null"));
                return "\nTwitter:  twitter.com/" + contact.getTwitter();
            }
        } catch (NullPointerException e) {
            return "";
        }
    }

}
