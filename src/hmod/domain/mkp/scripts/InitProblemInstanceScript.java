
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.components.CheckProblemInstance;
import hmod.domain.mkp.components.InitProblemInstance;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class InitProblemInstanceScript extends BuildScript
{
    public InitProblemInstanceScript(TreeHandler input) throws BuildException
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        branch(MKPIds.MKP_INIT_PROBLEM_INSTANCE).setBuildable(
            booleanStep().
            setDecider(
                operator(CheckProblemInstance.class).
                injectData(ref(MKPIds.MKP_DATA_COMMON))
            ).
            setTrueStep(
                sequentialStep().
                addOperator(
                    operator(InitProblemInstance.class).
                    injectData(ref(MKPIds.MKP_DATA_COMMON))
                )
            )
        );
    }
}
