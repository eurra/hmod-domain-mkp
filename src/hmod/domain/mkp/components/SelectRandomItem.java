
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.Item;
import java.util.List;
import optefx.util.random.RandomTool;

/**
 *
 * @author Enrique Urra C.
 */
public class SelectRandomItem implements Operator
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
        Item randomItem = items.get(RandomTool.getInt(items.size()));
        itemData.setItem(randomItem);
    }
}
