
package hmod.domain.mkp.components;

import hmod.core.DataInterface;
import hmod.domain.mkp.Item;

/**
 *
 * @author Enrique Urra C.
 */
public interface ItemData extends DataInterface
{
    void setItem(Item item);
    Item getItem();
}
