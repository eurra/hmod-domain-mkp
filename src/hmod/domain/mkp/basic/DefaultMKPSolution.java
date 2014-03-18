
package hmod.domain.mkp.basic;

import hmod.domain.mkp.Item;
import hmod.domain.mkp.MKPInstance;
import hmod.domain.mkp.MKPSolution;
import hmod.domain.mkp.MKPSolutionBuilder;
import hmod.domain.mkp.Resource;

/**
 *
 * @author Enrique Urra C.
 */
class DefaultMKPSolution implements MKPSolution
{
    private int totalProfit;
    private int constraintViolation;
    private Item[] items;
    private int[] resourceUsage;
    private MKPInstance instance;

    public DefaultMKPSolution(MKPInstance instance, int totalProfit, int constraintViolation, Item[] items, int[] resourceUsage)
    {
        this.instance = instance;
        this.totalProfit = totalProfit;
        this.constraintViolation = constraintViolation;
        this.items = items;
        this.resourceUsage = resourceUsage;
    }

    @Override
    public int getTotalProfit()
    {
        return totalProfit - constraintViolation * 100;
    }

    @Override
    public Item[] getItems()
    {
        Item[] copy = new Item[items.length];
        System.arraycopy(items, 0, copy, 0, copy.length);
        
        return copy;
    }
    
    @Override
    public int[] getResourceUsage()
    {
        int[] copy = new int[resourceUsage.length];
        System.arraycopy(resourceUsage, 0, copy, 0, copy.length);
        
        return copy;
    }

    @Override
    public MKPInstance getInstance()
    {
        return instance;
    }

    @Override
    public boolean isFeasible()
    {
        return constraintViolation == 0;
    }

    @Override
    public MKPSolutionBuilder getBuilder()
    {
        MKPSolutionBuilder builder = instance.createSolutionBuilder();
        
        for(int i = 0; i < items.length; i++)
            builder.add(items[i].getId());
        
        return builder;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Total profit: ").append(getTotalProfit()).append("\n");
        sb.append("Feasible: ").append(isFeasible()).append("\n");
        sb.append("Item list: ");
        
        for(int i = 0; i < items.length; i++)
            sb.append(items[i].getId()).append(" ");
        
        sb.append("\nResource usage: ");
        
        for(int i = 0; i < resourceUsage.length; i++)
        {
            Resource res = instance.getResource(i);
            sb.append(resourceUsage[i]).append(res.getCapacity() < resourceUsage[i] ? "!" : "").append(" ");
        }
        
        return sb.append("\n").toString();
    }
}
