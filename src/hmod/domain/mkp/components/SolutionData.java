
package hmod.domain.mkp.components;

import hmod.core.DataInterface;
import hmod.domain.mkp.MKPSolution;

/**
 *
 * @author Enrique Urra C.
 */
public interface SolutionData extends DataInterface
{
    void setInputSolution(MKPSolution solution);
    MKPSolution getInputSolution();
    
    void setBestSolution(MKPSolution solution);
    MKPSolution getBestSolution();
}
