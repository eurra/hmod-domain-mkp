
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.MKPInstance;
import hmod.domain.mkp.MKPSolutionBuilder;

/**
 *
 * @author Enrique Urra C.
 */
public class InitSolutionBuilder implements Operator
{
    private SolutionBuilderData solutionBuilderData;
    private ProblemInstanceData problemInstanceData;
    
    public void setSolutionBuilderData(SolutionBuilderData solutionBuilderData)
    {
        this.solutionBuilderData = solutionBuilderData;
    }

    public void setProblemInstanceData(ProblemInstanceData problemInstanceData)
    {
        this.problemInstanceData = problemInstanceData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        MKPInstance instance = problemInstanceData.getInstance();
        MKPSolutionBuilder builder = instance.createSolutionBuilder();
        solutionBuilderData.setBuilder(builder);
    }
}
