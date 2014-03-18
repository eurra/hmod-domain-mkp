
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.components.CheckSolution;
import hmod.domain.mkp.components.InitSolutionBuilder;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class InitSolutionScript extends BuildScript
{
    private BranchBuilder initProblemCall, checkSolution, initBuilder, callFillMethod, callSave;
    private Buildable commonData, initProblemStart, fillMethodStart, saveStart;

    public InitSolutionScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        initProblemCall = branch(MKPIds.MKP_INIT_SOLUTION);
        checkSolution = branch();
        initBuilder = branch();
        callFillMethod = branch();
        callSave = branch();
        
        commonData = ref(MKPIds.MKP_DATA_COMMON);
        initProblemStart = ref(MKPIds.MKP_INIT_PROBLEM_INSTANCE);
        fillMethodStart = ref(MKPIds.MKP_CONFIG_FILL_METHOD);
        saveStart = ref(MKPIds.MKP_SAVE_SOLUTION);
    }
    
    @Override
    public void process() throws BuildException
    {        
        initProblemCall.setBuildable(
            subProcessStep().setNextStep(checkSolution).
            setSubStep(initProblemStart)
        );
        
        checkSolution.setBuildable(
            booleanStep().setFalseStep(initBuilder).
            setDecider(
                operator(CheckSolution.class).
                injectData(commonData)
            )
        );
        
        initBuilder.setBuildable(
            sequentialStep().setNextStep(callFillMethod).
            addOperator(
                operator(InitSolutionBuilder.class).
                injectData(commonData)
            )
        );
        
        callFillMethod.setBuildable(
            subProcessStep().setNextStep(callSave).
            setSubStep(fillMethodStart)
        );
        
        callSave.setBuildable(
            subProcessStep().
            setSubStep(saveStart)
        );
    }
}
