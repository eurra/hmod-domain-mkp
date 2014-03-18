
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.MKPSolution;
import hmod.domain.mkp.MKPSolutionBuilder;

/**
 *
 * @author Enrique Urra C.
 */
public class LoadSolution implements Operator
{
    private SolutionData solutionData;
    private SolutionBuilderData solutionBuilderData;

    public void setSolutionData(SolutionData solutionData)
    {
        this.solutionData = solutionData;
    }
    
    public void setSolutionBuilderData(SolutionBuilderData solutionBuilderData)
    {
        this.solutionBuilderData = solutionBuilderData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        MKPSolution solution = solutionData.getInputSolution();
        MKPSolutionBuilder builder = solution.getBuilder();
        solutionBuilderData.setBuilder(builder);
    }
}
