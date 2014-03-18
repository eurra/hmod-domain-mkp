
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.components.LoadSolution;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class LoadSolutionScript extends BuildScript
{
    public LoadSolutionScript(TreeHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {        
        branch(MKPIds.MKP_LOAD_SOLUTION).setBuildable(
            subProcessStep().
            setSubStep(ref(MKPIds.MKP_INIT_SOLUTION)).
            setNextStep(
                sequentialStep().
                addOperator(
                    operator(LoadSolution.class).
                    injectData(ref(MKPIds.MKP_DATA_COMMON))
                )
            )
        );
    }
}
