
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.MKPSolution;
import hmod.domain.mkp.MKPSolutionBuilder;
import optefx.util.output.OutputManager;

/**
 *
 * @author Enrique Urra C.
 */
public class SaveSolution implements Operator
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
        MKPSolutionBuilder currBuilder = solutionBuilderData.getBuilder();
        MKPSolution newSolution = currBuilder.build();
        solutionData.setInputSolution(newSolution);
        solutionBuilderData.setBuilder(null);
        OutputManager.println(OutputIds.NEW_SOLUTION_INFO, "***********************\n\n" + newSolution + "\n");
        
        MKPSolution bestSolution = solutionData.getBestSolution();
        
        if(bestSolution == null || bestSolution.getTotalProfit() < newSolution.getTotalProfit())
        {
            solutionData.setBestSolution(newSolution);  
            OutputManager.println(OutputIds.NEW_BEST_SOLUTION_INFO, "***********************\n\n" + newSolution + "\n");
        }
    }
}
