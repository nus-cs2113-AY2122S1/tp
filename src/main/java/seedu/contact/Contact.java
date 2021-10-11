package seedu.contact;

public class Contact {
    private String name;
    private String github;
    private String email;
    private String linkedin;
    private String twitter;
    private String telegram;

    public Contact(String name, String github) {
        this.name = name;
        this.github = github;
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
