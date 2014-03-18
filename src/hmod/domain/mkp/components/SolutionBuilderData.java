
package hmod.domain.mkp.components;

import hmod.core.DataInterface;
import hmod.domain.mkp.MKPSolutionBuilder;

/**
 *
 * @author Enrique Urra C.
 */
public interface SolutionBuilderData extends DataInterface
{
    void setBuilder(MKPSolutionBuilder solutionBuilder);
    MKPSolutionBuilder getBuilder();
}
