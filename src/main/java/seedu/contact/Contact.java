package seedu.contact;

public class Contact {
    public static final int NAME_INDEX = 0;
    public static final int GITHUB_INDEX = 1;
    public static final int LINKEDIN_INDEX = 2;
    public static final int TELEGRAM_INDEX = 3;
    public static final int TWITTER_INDEX = 4;
    public static final int EMAIL_INDEX = 5;
    private String name;
    private String github;
    private String linkedin;
    private String telegram;
    private String twitter;
    private String email;

    public Contact(String name, String github, String linkedin, String telegram, String twitter, String email) {
        this.name = name;
        this.github = github;
        this.linkedin = linkedin;
        this.telegram = telegram;
        this.twitter = twitter;
        this.email = email;
    }

    //@@author ng-andre
    public void setDetail(String updatedDetail, int index) {
        switch (index) {
        case NAME_INDEX:
            setName(updatedDetail);
            break;
        case GITHUB_INDEX:
            setGithub(updatedDetail);
            break;
        case LINKEDIN_INDEX:
            setLinkedin(updatedDetail);
            break;
        case TELEGRAM_INDEX:
            setTelegram(updatedDetail);
            break;
        case TWITTER_INDEX:
            setTwitter(updatedDetail);
            break;
        case EMAIL_INDEX:
            setEmail(updatedDetail);
            break;
        default:
            //control should never reach here
            assert false;
            return;
        }
    }

    //@@author ng-andre
    public void editContact(String[] contactDetails) {
        for (int i = 0; i < contactDetails.length; i++) {
            if (contactDetails[i] != null) {
                setDetail(contactDetails[i], i);
            }
        }
    }

    //@@author marcusbory
    public void deleteContactFields(boolean[] hasDeletedDetails) {
        for (int i = GITHUB_INDEX; i <= EMAIL_INDEX; i++) {
            if (hasDeletedDetails[i]) {
                setDetail(null, i);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }
    //@@author

    //@@author ng-andre
    public String[] getContactStringArray() {
        return new String[]{name, github, linkedin, telegram, twitter, email};
    }
}
