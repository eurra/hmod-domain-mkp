
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
public class AddRandomScript extends BuildScript
{   
    private BranchBuilder callLoad, callAddRandom, callSave;
    private Buildable loadStart, addRandomStart, saveStart;
    
    public AddRandomScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        callLoad = branch(MKPIds.MKP_HEURISTIC_ADD_RANDOM);
        callAddRandom = branch();
        callSave = branch();
        
        loadStart = ref(MKPIds.MKP_LOAD_SOLUTION);
        addRandomStart = ref(MKPIds.MKP_OPERATION_ADD_RANDOM);
        saveStart = ref(MKPIds.MKP_SAVE_SOLUTION);
    }
    
    
    @Override
    public void process() throws BuildException
    {
        callLoad.setBuildable(
            subProcessStep().setNextStep(callAddRandom).
            setSubStep(loadStart)
        );
        
        callAddRandom.setBuildable(
            subProcessStep().setNextStep(callSave).
            setSubStep(addRandomStart)
        );
        
        callSave.setBuildable(
            subProcessStep().
            setSubStep(saveStart)
        );
    }
}
