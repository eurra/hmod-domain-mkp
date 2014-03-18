
package hmod.hyperheuristic.adapter.basic.mkp;

import hmod.domain.mkp.MKPSolution;
import hmod.hyperheuristic.model.selection.HHSolution;

/**
 *
 * @author Enrique Urra C.
 */
public class MKPHHSolution implements HHSolution<MKPHHSolution>
{
    private MKPSolution innerSolution;

    public MKPHHSolution(MKPSolution innerSolution)
    {
        this.innerSolution = innerSolution;
    }

    public MKPSolution getInnerSolution()
    {
        return innerSolution;
    }
    
    @Override
    public double getEvaluation()
    {
        return innerSolution.getTotalProfit();
    }

    @Override
    public int compareTo(MKPHHSolution otherSolution)
    {
        if(getEvaluation() > otherSolution.getEvaluation())
            return 1;
        else if(getEvaluation() < otherSolution.getEvaluation())
            return -1;
        else
            return 0;
    }
}
