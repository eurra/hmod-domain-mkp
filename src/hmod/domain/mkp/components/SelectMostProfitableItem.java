
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.Item;
import java.util.List;

/**
 *
 * @author Enrique Urra C.
 */
public class SelectMostProfitableItem implements Operator
{
    private ItemListData itemListData;
    private ItemData itemData;

    public void setItemListData(ItemListData itemListData)
    {
        this.itemListData = itemListData;
    }
    
    public void setItemData(ItemData itemData)
    {
        this.itemData = itemData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        List<Item> items = itemListData.getItems();
        int listSize = items.size();
        Item selected = null;
            
        for(int i = 0; i < listSize; i++)
        {
            Item currItem = items.get(i);
            
            if(selected == null || selected.getProfit() < currItem.getProfit())
                selected = currItem;
        }

        itemData.setItem(selected);
    }
}
