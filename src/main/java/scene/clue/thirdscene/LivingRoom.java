//@@author WU-LUOYU-SERENA

package scene.clue.thirdscene;

import scene.clue.Clue;

public class LivingRoom extends Clue {

    public LivingRoom() {
        super();
        this.clueName = "               Lipstick Stain";
        this.image = "                    .. ..\n"
                + "                 ..'  `  `..\n"
                + "              ..'__.-...-.__`..\n"
                + "                `..       ..'\n"
                + "                   `-...-'";
        this.description = "There is a bottle of half-drank soy milk on the\n"
                + "table, with a faint lipstick stain at the mouth\n"
                + "of the bottle.";
    }
}
