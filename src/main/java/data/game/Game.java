package data.game;

import java.io.Serializable;

public abstract class Game implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;

    public abstract int execute(boolean difficultLevel);

    public abstract String getName();
}