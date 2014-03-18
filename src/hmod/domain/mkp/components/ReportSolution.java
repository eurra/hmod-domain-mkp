
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.MKPSolution;
import optefx.util.output.OutputManager;

/**
 *
 * @author Enrique Urra C.
 */
public class ReportSolution implements Operator
{
    private SolutionData solutionData;

    public void setSolutionData(SolutionData solutionData)
    {
        this.solutionData = solutionData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        MKPSolution finalSolution = solutionData.getBestSolution();
        OutputManager.println(OutputIds.FINAL_SOLUTION_INFO, "***********************\n\n" + finalSolution + "\n");
    }
}
