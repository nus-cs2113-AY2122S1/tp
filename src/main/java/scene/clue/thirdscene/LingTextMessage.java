//@@author WU-LUOYU-SERENA

package scene.clue.thirdscene;

import scene.clue.Clue;

public class LingTextMessage extends Clue {

    public LingTextMessage() {
        super();
        this.clueName = "                Text Message";
        this.image = "                    *\n"
                + "                    |_\n"
                + "                    (O)\n"
                + "                    |#|\n"
                + "                    '-'\n";
        this.description = "There was a text message in the notifications of\n"
                + "Ling Ling's phone. I could only see the first\n"
                + "line: \"he also found out about me. Today it's all\n"
                + "up to you. No matter what you have to...\"\n";
    }
}
