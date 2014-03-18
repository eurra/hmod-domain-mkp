
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
public class RemoveAndGreedyFillScript extends BuildScript
{
    private BranchBuilder callLoad, callMultiRemove, callGreedyFill, callSave;
    private Buildable loadStart, multiRemoveStart, greedyFillStart, saveStart;

    public RemoveAndGreedyFillScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        callLoad = branch(MKPIds.MKP_HEURISTIC_REMOVE_AND_GREEDY_FILL);
        callMultiRemove = branch();
        callGreedyFill = branch();
        callSave = branch();
        
        loadStart = ref(MKPIds.MKP_LOAD_SOLUTION);
        multiRemoveStart = ref(MKPIds.MKP_OPERATION_MULTI_REMOVE);
        greedyFillStart = ref(MKPIds.MKP_OPERATION_GREEDY_FILL);
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
            subProcessStep().setNextStep(callGreedyFill).
            setSubStep(multiRemoveStart)
        );
        
        callGreedyFill.setBuildable(
            subProcessStep().setNextStep(callSave).
            setSubStep(greedyFillStart)
        );
        
        callSave.setBuildable(
            subProcessStep().
            setSubStep(saveStart)
        );
    }
}
