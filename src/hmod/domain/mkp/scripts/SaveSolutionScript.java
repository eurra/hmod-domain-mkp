
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.components.SaveSolution;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class SaveSolutionScript extends BuildScript
{
    public SaveSolutionScript(TreeHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {        
        branch(MKPIds.MKP_SAVE_SOLUTION).setBuildable(
            sequentialStep().
            addOperator(
                operator(SaveSolution.class).
                injectData(ref(MKPIds.MKP_DATA_COMMON))
            )
        );
    }
}
