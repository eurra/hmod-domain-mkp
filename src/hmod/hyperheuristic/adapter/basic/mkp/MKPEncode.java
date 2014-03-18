
package hmod.hyperheuristic.adapter.basic.mkp;

import hmod.core.AlgorithmException;
import hmod.domain.mkp.MKPSolution;
import hmod.hyperheuristic.model.basic.components.EncodeSolutionOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class MKPEncode extends EncodeSolutionOperator<MKPHHSolution, MKPSolution>
{
    @Override
    public MKPHHSolution encode(MKPSolution llSolution) throws AlgorithmException
    {
        return new MKPHHSolution(llSolution);
    }
}
