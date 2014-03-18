
package hmod.domain.mkp.basic;

import hmod.domain.mkp.Item;
import hmod.domain.mkp.MKPInstance;
import hmod.domain.mkp.MKPSolutionBuilder;
import hmod.domain.mkp.Resource;

/**
 *
 * @author Enrique Urra C.
 */
class DefaultMKPInstance implements MKPInstance
{
    private Item[] items;
    private Resource[] resources;
    private int[][] weights;

    public DefaultMKPInstance(Item[] items, Resource[] resources, int[][] weights)
    {
        this.items = items;
        this.resources = resources;
        this.weights = weights;
    }
    
    @Override
    public MKPSolutionBuilder createSolutionBuilder()
    {
        return new DefaultMKPSolutionBuilder(this);
    }

    @Override
    public int getItemsCount()
    {
        return items.length;
    }

    @Override
    public int getResourcesCount()
    {
        return resources.length;
    }
    
    private void checkItemIndex(int index)
    {
        if(index < 0 || index >= items.length)
            throw new IllegalArgumentException("Wrong item index: " + index);
    }
    
    private void checkResourceIndex(int index)
    {
        if(index < 0 || index >= resources.length)
            throw new IllegalArgumentException("Wrong resource index: " + index);
    }

    @Override
    public Item getItem(int itemId)
    {
        checkItemIndex(itemId);
        return items[itemId];
    }

    @Override
    public Resource getResource(int resourceId)
    {
        checkResourceIndex(resourceId);        
        return resources[resourceId];
    }

    @Override
    public int getWeight(int itemId, int resourceId)
    {
        checkItemIndex(itemId);
        checkResourceIndex(resourceId);
        
        return weights[resourceId][itemId];
    }
}
