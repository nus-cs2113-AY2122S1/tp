//@@author ashrafjfr

package seedu.ui;

import seedu.contact.Contact;

public class ViewMessageFormatterUi {
    public static final int NAME_INDEX = 0;
    public static final int GITHUB_INDEX = 1;
    public static final int LINKEDIN_INDEX = 2;
    public static final int TELEGRAM_INDEX = 3;
    public static final int TWITTER_INDEX = 4;
    public static final int EMAIL_INDEX = 5;

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

    public static String viewDetailFormatter(Contact contact, int index) {
        switch (index) {
        case NAME_INDEX:
            return viewNameFormatter(contact);
        case GITHUB_INDEX:
            return viewGithubFormatter(contact);
        case LINKEDIN_INDEX:
            return viewLinkedinFormatter(contact);
        case TELEGRAM_INDEX:
            return viewTelegramFormatter(contact);
        case TWITTER_INDEX:
            return viewTwitterFormatter(contact);
        case EMAIL_INDEX:
            return viewEmailFormatter(contact);
        default:
            assert false; //should never reach here
            return null;
        }
    }
}
