
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.domain.mkp.MKPSolution;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckSolution extends BooleanOperator
{
    private SolutionData solutionData;

    public void setSolutionData(SolutionData solutionData)
    {
        this.solutionData = solutionData;
    }

    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        MKPSolution solution = solutionData.getInputSolution();
        return solution != null;
    }
}
