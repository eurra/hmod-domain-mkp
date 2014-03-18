
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.domain.mkp.MKPSolutionBuilder;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckFeasibleBuild extends BooleanOperator
{
    private SolutionBuilderData solutionBuilderData;

    public void setSolutionBuilderData(SolutionBuilderData solutionBuilderData)
    {
        this.solutionBuilderData = solutionBuilderData;
    }

    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        MKPSolutionBuilder solutionBuilder = solutionBuilderData.getBuilder();
        return solutionBuilder.isCurrentFeasible();
    }
}
