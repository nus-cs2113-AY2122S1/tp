package seedu.contact;

import seedu.exception.InvalidFlagException;

//should be interface?
public class PersonalContact extends Contact {
    public static final int NAME_INDEX = 0;
    public static final int GITHUB_INDEX = 1;
    public static final int LINKEDIN_INDEX = 2;
    public static final int TELEGRAM_INDEX = 3;
    public static final int TWITTER_INDEX = 4;
    public static final int EMAIL_INDEX = 5;

    public PersonalContact(String name, String github, String linkedin, String telegram, String twitter, String email) {
        super(name, github, linkedin, telegram, twitter, email);
    }

    public void editPersonalContact(String[] contactDetails) {
        for (int i = 0; i < contactDetails.length; i++) {
            if (contactDetails[i] != null) {
                switch (i) {
                case NAME_INDEX:
                    setName(contactDetails[i]);
                    break;
                case GITHUB_INDEX:
                    setGithub(contactDetails[i]);
                    break;
                case LINKEDIN_INDEX:
                    setLinkedin(contactDetails[i]);
                    break;
                case TELEGRAM_INDEX:
                    setTelegram(contactDetails[i]);
                    break;
                case TWITTER_INDEX:
                    setTwitter(contactDetails[i]);
                    break;
                case EMAIL_INDEX:
                    setEmail(contactDetails[i]);
                    break;
                default:
                    //control should never reach here
                    assert false;
                    break;
                }
            }
        }
    }



}
