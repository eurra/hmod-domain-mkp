
package hmod.domain.mkp.basic;

import hmod.domain.mkp.Item;
import hmod.domain.mkp.MKPSolutionBuilder;
import hmod.domain.mkp.MKPInstance;
import hmod.domain.mkp.MKPSolution;
import hmod.domain.mkp.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Enrique Urra C.
 */
class DefaultMKPSolutionBuilder implements MKPSolutionBuilder
{
    private MKPInstance instance;
    private Map<Integer, Item> currItems;
    private int[] currResourceUsage;

    public DefaultMKPSolutionBuilder(MKPInstance instance)
    {
        this.instance = instance;
        this.currItems = new HashMap<>();
        this.currResourceUsage = new int[instance.getResourcesCount()];
    }
    
    private void addWeightTo(int[] resourceUsage, int itemId)
    {
        for(int i = 0; i < resourceUsage.length; i++)
            resourceUsage[i] += instance.getWeight(itemId, i);
    }
    
    private void removeWeightTo(int[] resourceUsage, int itemId)
    {
        for(int i = 0; i < resourceUsage.length; i++)
            resourceUsage[i] -= instance.getWeight(itemId, i);
    }
    
    private int getConstraintViolation(int[] resourceUsage)
    {
        int constraintViolation = 0;
        
        for(int i = 0; i < resourceUsage.length; i++)
        {
            Resource res = instance.getResource(i);
            
            if(res.getCapacity() < resourceUsage[i])
                constraintViolation += resourceUsage[i] - res.getCapacity();
        }
        
        return constraintViolation;
    }

    @Override
    public DefaultMKPSolutionBuilder add(int itemId)
    {
        if(!currItems.containsKey(itemId))
        {
            Item item = instance.getItem(itemId);
            currItems.put(itemId, item);
            addWeightTo(currResourceUsage, itemId);
        }            
        
        return this;
    }
    
    @Override
    public DefaultMKPSolutionBuilder remove(int itemId)
    {
        if(currItems.containsKey(itemId))
        {
            currItems.remove(itemId);
            removeWeightTo(currResourceUsage, itemId);
        }            
        
        return this;
    }
    
    @Override
    public boolean isAddFeasible(int nextItemId)
    {
        int[] checkResourceUsage = new int[currResourceUsage.length];
        System.arraycopy(currResourceUsage, 0, checkResourceUsage, 0, currResourceUsage.length);
        addWeightTo(checkResourceUsage, nextItemId);
        
        return getConstraintViolation(checkResourceUsage) == 0;
    }

    @Override
    public boolean contains(int itemId)
    {
        return currItems.keySet().contains(itemId);
    }

    @Override
    public Item[] getAvailable()
    {
        int allItemCount = instance.getItemsCount();
        Item[] available = new Item[allItemCount - currItems.size()];
        int availableIndex = 0;
        
        for(int i = 0; i < allItemCount; i++)
        {
            if(!currItems.containsKey(i))
                available[availableIndex++] = instance.getItem(i);
        }
        
        return available;
    }

    @Override
    public Item[] getCurrent()
    {
        return currItems.values().toArray(new Item[0]);
    }

    @Override
    public boolean isCurrentFeasible()
    {
        return getConstraintViolation(currResourceUsage) > 0;
    }

    @Override
    public MKPSolution build()
    {
        Item[] finalItems = new Item[currItems.size()];
        int totalProfit = 0;
        int pos = 0;
        
        for(Item item : currItems.values())
        {
            finalItems[pos++] = item;
            totalProfit += item.getProfit();
        }
        
        int constraintViolation = getConstraintViolation(currResourceUsage);
        
        return new DefaultMKPSolution(instance, totalProfit, constraintViolation, finalItems, currResourceUsage);
    }
}
