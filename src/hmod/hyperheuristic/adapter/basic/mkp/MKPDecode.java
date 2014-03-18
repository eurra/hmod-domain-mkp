
package hmod.hyperheuristic.adapter.basic.mkp;

import hmod.core.AlgorithmException;
import hmod.domain.mkp.MKPSolution;
import hmod.hyperheuristic.model.basic.components.DecodeSolutionOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class MKPDecode extends DecodeSolutionOperator<MKPHHSolution, MKPSolution>
{
    @Override
    public MKPSolution decode(MKPHHSolution solution) throws AlgorithmException
    {
        return solution.getInnerSolution();
    }
}
