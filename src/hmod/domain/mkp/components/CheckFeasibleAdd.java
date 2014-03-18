
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.domain.mkp.Item;
import hmod.domain.mkp.MKPSolutionBuilder;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckFeasibleAdd extends BooleanOperator
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
    public Boolean evaluate() throws AlgorithmException
    {
        MKPSolutionBuilder builder = solutionBuilderData.getBuilder();
        Item item = itemData.getItem();
        
        return builder.isAddFeasible(item.getId());
    }
}
