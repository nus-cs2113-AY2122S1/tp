package gordon.command;

import gordon.kitchen.Cookbook;

public class ListRecipesCommand extends Command {
    private String listType;

    public ListRecipesCommand(String listType) {
        this.listType = listType;
    }

    @Override
    public void execute(Cookbook cookbook) {
        if (listType.equals("listRecipes")) {
            System.out.print(cookbook);
        } else {
            System.out.print(cookbook.listTags());
        }
    }
}
