package seedu.contact;

public class Contact {
    private String name;
    private String github;

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
}
