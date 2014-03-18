
package hmod.domain.mkp;

/**
 *
 * @author Enrique Urra C.
 */
public interface MKPSolution
{
    MKPInstance getInstance();
    int getTotalProfit();
    Item[] getItems();
    int[] getResourceUsage();
    boolean isFeasible();
    MKPSolutionBuilder getBuilder();
}
