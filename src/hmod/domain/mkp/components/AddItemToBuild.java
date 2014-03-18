
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.Item;
import hmod.domain.mkp.MKPSolutionBuilder;

/**
 *
 * @author Enrique Urra C.
 */
public class AddItemToBuild implements Operator
{
    private ItemData itemData;
    private SolutionBuilderData solutionBuilderData;

    public void setItemData(ItemData itemData)
    {
        this.itemData = itemData;
    }

    public void setSolutionBuilderData(SolutionBuilderData solutionBuilderData)
    {
        this.solutionBuilderData = solutionBuilderData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        MKPSolutionBuilder currBuilder = solutionBuilderData.getBuilder();
        Item currItem = itemData.getItem();
        currBuilder.add(currItem.getId());
    }
}
