
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.Item;
import hmod.domain.mkp.MKPSolutionBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Enrique Urra C.
 */
public class GetCurrentItems implements Operator
{
    private ItemListData itemListData;
    private SolutionBuilderData solutionBuilderData;

    public void setItemListData(ItemListData itemListData)
    {
        this.itemListData = itemListData;
    }
    
    public void setSolutionBuilderData(SolutionBuilderData solutionBuilderData)
    {
        this.solutionBuilderData = solutionBuilderData;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
        MKPSolutionBuilder builder = solutionBuilderData.getBuilder();
        Item[] itemsArray = builder.getCurrent();
        List<Item> items = new ArrayList<>(itemsArray.length);
        Collections.addAll(items, itemsArray);
        itemListData.setItems(items);
    }
}
