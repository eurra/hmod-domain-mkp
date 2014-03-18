
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.Item;
import java.util.Collection;

/**
 *
 * @author Enrique Urra C.
 */
public class RemoveItemFromList implements Operator
{
    private ItemData itemData;
    private ItemListData itemListData;

    public void setItemData(ItemData itemData)
    {
        this.itemData = itemData;
    }

    public void setItemListData(ItemListData itemListData)
    {
        this.itemListData = itemListData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        Collection<Item> collection = itemListData.getItems();
        Item item = itemData.getItem();
        collection.remove(item);
    }
}
