package scene;

import exceptions.MissingNarrativeException;
import scene.narrative.Narrative;
import scene.suspect.Suspect;
import scene.suspect.SuspectList;

import java.io.FileNotFoundException;

public class Scene {
    private final Narrative narrative;
    private final SuspectList suspectList;
    private final SceneTypes sceneType;

    public Scene(Narrative narrative, SuspectList suspectList, SceneTypes sceneType) {
        this.narrative = narrative;
        this.suspectList = suspectList;
        this.sceneType = sceneType;
    }

    public SuspectList getSuspectList() {
        return suspectList;
    }

    public SceneTypes getSceneType() {
        return this.sceneType;
    }

    public Suspect investigateSuspect(String name) {
        return suspectList.getSuspects().get(name);
    }

    public void runScene() throws MissingNarrativeException {
        try {
            this.narrative.displayNarrative();
        } catch (FileNotFoundException e) {
            throw new MissingNarrativeException("Narrative file is missing");
        }
    }

    @Override
    public String toString() {
        try {
            return this.narrative.getNarrative()
                    + "\n"
                    + "Suspects: "
                    + this.getSuspectList().toString();
        } catch (FileNotFoundException e) {
            System.out.println("Narrative has not been selected!");
            return "Incomplete Scene";
        }
    }
}
