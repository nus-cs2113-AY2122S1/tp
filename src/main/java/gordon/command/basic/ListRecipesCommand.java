package gordon.command.basic;

import gordon.command.Command;
import gordon.kitchen.Cookbook;


public class ListRecipesCommand extends Command {
    private String listType;

    public ListRecipesCommand(String listType) {
        this.listType = listType;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method prints the name of every recipe in the cookbook, or all the tags in the cookbook</p>
     *
     * @param cookbook The recipe object to be referenced
     */
    @Override
    public void execute(Cookbook cookbook) {
        if (listType.equals("listRecipes")) {
            System.out.print(cookbook);
        } else {
            System.out.print(cookbook.listCookbookTags());
        }
    }
}

