//@@author WU-LUOYU-SERENA

package scene.clue;

public class Clue {

    protected String clueName = "default name";
    protected String image = "default image :)";
    protected String description = "default description";
    protected boolean isChecked;

    public Clue() {
        isChecked = false;
    }

    /**
     * Constructs a Clue object by setting the clue name, clue image and clue
     * description to those passed in correspondingly.
     *
     * @param clueName Name of the clue (usually summarises the gist of the clue).
     * @param image Ascii art that gives visual representation of a clue.
     * @param description Details about a clue in words.
     */
    public Clue(String clueName, String image, String description) {
        this.clueName = clueName;
        this.image = image;
        this.description = description;
        isChecked = false;
    }

    public void setChecked() {
        this.isChecked = true;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getClueName() {
        return clueName;
    }

    @Override
    public String toString() {
        assert !clueName.equals("default name");
        assert !image.equals("default image :)");
        assert !description.equals("default description");
        return "------------------------------------------------\n"
                + clueName
                + "\n"
                + image
                + description;
    }
}
