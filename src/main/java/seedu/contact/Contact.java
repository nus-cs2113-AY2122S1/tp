package seedu.contact;

public class Contact {
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

    public String getName() {
        return name;
    }

    public String getGithub() {
        return github;
    }

    public void setName(String name) {
        this.name = name;
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
}
