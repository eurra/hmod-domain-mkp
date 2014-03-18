
package hmod.hyperheuristic.adapter.basic.mkp;

import hmod.domain.mkp.MKPSolution;
import hmod.domain.mkp.components.SolutionData;
import hmod.hyperheuristic.model.basic.components.UploadSolutionOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class MKPUpload extends UploadSolutionOperator<MKPSolution>
{
    private SolutionData solutionData;

    public void setSolutionData(SolutionData solutionData)
    {
        this.solutionData = solutionData;
    }

    @Override
    public MKPSolution upload()
    {
        return solutionData.getInputSolution();
    }
}
