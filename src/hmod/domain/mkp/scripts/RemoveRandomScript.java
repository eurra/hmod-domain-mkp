
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class RemoveRandomScript extends BuildScript
{
    private BranchBuilder callLoad, callRemoveRandom, callSave;
    private Buildable loadStart, removeRandomStart, saveStart;

    public RemoveRandomScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        callLoad = branch(MKPIds.MKP_HEURISTIC_REMOVE_RANDOM);
        callRemoveRandom = branch();
        callSave = branch();
        
        loadStart = ref(MKPIds.MKP_LOAD_SOLUTION);
        removeRandomStart = ref(MKPIds.MKP_OPERATION_REMOVE_RANDOM);
        saveStart = ref(MKPIds.MKP_SAVE_SOLUTION);
    }
    
    @Override
    public void process() throws BuildException
    {        
        callLoad.setBuildable(
            subProcessStep().setNextStep(callRemoveRandom).
            setSubStep(loadStart)
        );
        
        callRemoveRandom.setBuildable(
            subProcessStep().setNextStep(callSave).
            setSubStep(removeRandomStart)
        );
        
        callSave.setBuildable(
            subProcessStep().
            setSubStep(saveStart)
        );
    }
}
