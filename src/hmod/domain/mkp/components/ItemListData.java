
package hmod.domain.mkp.components;

import hmod.core.DataInterface;
import hmod.domain.mkp.Item;
import java.util.List;

/**
 *
 * @author Enrique Urra C.
 */
public interface ItemListData extends DataInterface
{
    void setItems(List<Item> items);
    List<Item> getItems();
}
