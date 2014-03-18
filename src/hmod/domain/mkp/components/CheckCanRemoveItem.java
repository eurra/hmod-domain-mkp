
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.domain.mkp.Item;
import java.util.List;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckCanRemoveItem extends BooleanOperator
{
    private ItemListData itemListData;

    public void setItemListData(ItemListData itemListData)
    {
        this.itemListData = itemListData;
    }

    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        List<Item> items = itemListData.getItems();
        return items.size() > 1;
    }
}
