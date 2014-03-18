
package hmod.domain.mkp;

/**
 *
 * @author Enrique Urra C.
 */
public interface MKPSolutionBuilder
{
    MKPSolutionBuilder add(int itemId);
    MKPSolutionBuilder remove(int itemId);
    boolean isAddFeasible(int nextItemId);
    boolean contains(int itemId);
    Item[] getAvailable();
    Item[] getCurrent();
    boolean isCurrentFeasible();
    MKPSolution build();
}
