
package hmod.hyperheuristic.adapter.basic.mkp;

import hmod.core.AlgorithmException;
import hmod.domain.mkp.MKPSolution;
import hmod.domain.mkp.components.SolutionData;
import hmod.hyperheuristic.model.basic.components.DownloadSolutionOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class MKPDownload extends DownloadSolutionOperator<MKPSolution>
{
    private SolutionData solutionData;

    public void setSolutionData(SolutionData solutionData)
    {
        this.solutionData = solutionData;
    }
    
    @Override
    public void download(MKPSolution llSolution) throws AlgorithmException
    {
        solutionData.setInputSolution(llSolution);
    }
}
