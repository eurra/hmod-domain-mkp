
package hmod.domain.mkp;

/**
 *
 * @author Enrique Urra C.
 */
public interface MKPInstance
{
    MKPSolutionBuilder createSolutionBuilder();
    
    int getItemsCount();
    int getResourcesCount();
    
    Item getItem(int itemId);
    Resource getResource(int resourceId);
    
    int getWeight(int itemId, int resourceId);
}
