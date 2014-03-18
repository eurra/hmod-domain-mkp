
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.domain.mkp.MKPInstance;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckProblemInstance extends BooleanOperator
{
    private ProblemInstanceData problemInstanceData;

    public void setProblemInstanceData(ProblemInstanceData problemInstanceData)
    {
        this.problemInstanceData = problemInstanceData;
    }

    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        MKPInstance instance = problemInstanceData.getInstance();
        return instance == null;
    }
}
