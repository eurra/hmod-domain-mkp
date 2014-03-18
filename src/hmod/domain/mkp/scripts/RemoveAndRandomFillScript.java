
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
public class RemoveAndRandomFillScript extends BuildScript
{
    private BranchBuilder callLoad, callMultiRemove, callRandomFill, callSave;
    private Buildable loadStart, multiRemoveStart, randomFillStart, saveStart;

    public RemoveAndRandomFillScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        callLoad = branch(MKPIds.MKP_HEURISTIC_REMOVE_AND_RANDOM_FILL);
        callMultiRemove = branch();
        callRandomFill = branch();
        callSave = branch();
        
        loadStart = ref(MKPIds.MKP_LOAD_SOLUTION);
        multiRemoveStart = ref(MKPIds.MKP_OPERATION_MULTI_REMOVE);
        randomFillStart = ref(MKPIds.MKP_OPERATION_RANDOM_FILL);
        saveStart = ref(MKPIds.MKP_SAVE_SOLUTION);
    }
    
    @Override
    public void process() throws BuildException
    { 
        callLoad.setBuildable(
            subProcessStep().setNextStep(callMultiRemove).
            setSubStep(loadStart)
        );
        
        callMultiRemove.setBuildable(
            subProcessStep().setNextStep(callRandomFill).
            setSubStep(multiRemoveStart)
        );
        
        callRandomFill.setBuildable(
            subProcessStep().setNextStep(callSave).
            setSubStep(randomFillStart)
        );
        
        callSave.setBuildable(
            subProcessStep().
            setSubStep(saveStart)
        );
    }
}
