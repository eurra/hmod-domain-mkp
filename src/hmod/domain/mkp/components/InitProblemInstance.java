
package hmod.domain.mkp.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.domain.mkp.MKPInstanceFactory;
import hmod.domain.mkp.MKPInstance;
import hmod.domain.mkp.basic.DefaultMKPInstanceFactory;

/**
 *
 * @author Enrique Urra C.
 */
public class InitProblemInstance implements Operator
{
    private ProblemInstanceData problemInstanceData;
    
    public void setProblemInstanceData(ProblemInstanceData problemInstanceData)
    {
        this.problemInstanceData = problemInstanceData;
    }

    @Override
    public void execute() throws AlgorithmException
    {        
        String file = problemInstanceData.getInstanceFile();            
        MKPInstanceFactory factory = new DefaultMKPInstanceFactory();
        MKPInstance instance = factory.createProblemInstance(file);
        problemInstanceData.setInstance(instance);

        /*OutputManager.println(OutputIds.INSTANCE_INFO, "**** Instance information ****\n");
        OutputManager.println(OutputIds.INSTANCE_INFO, instance.getInstanceInfo());

        OutputManager.println(OutputIds.EVAL_INFO, "**** Evaluation parameters information ****\n");
        OutputManager.println(OutputIds.EVAL_INFO, "1) Operator system weights:\n");
        OutputManager.println(OutputIds.EVAL_INFO, "Transit time: " + evaluationProfileHandler.getTransitTimeWeight());
        OutputManager.println(OutputIds.EVAL_INFO, "Route duration: " + evaluationProfileHandler.getRouteDurationWeight());
        OutputManager.println(OutputIds.EVAL_INFO, "Slack time: " + evaluationProfileHandler.getSlackTimeWeight());                
        OutputManager.println(OutputIds.EVAL_INFO, "\n2) Service quality weights:\n");
        OutputManager.println(OutputIds.EVAL_INFO, "Ride time: " + evaluationProfileHandler.getRideTimeWeight());
        OutputManager.println(OutputIds.EVAL_INFO, "Excess ride time: " + evaluationProfileHandler.getExcessRideTimeWeight());
        OutputManager.println(OutputIds.EVAL_INFO, "Wait time: " + evaluationProfileHandler.getWaitTimeWeight());                
        OutputManager.println(OutputIds.EVAL_INFO, "\n3) Unfeasibility weights:\n");
        OutputManager.println(OutputIds.EVAL_INFO, "Time windows violation: " + evaluationProfileHandler.getTimeWindowsViolationWeight());
        OutputManager.println(OutputIds.EVAL_INFO, "Max. route duration violation: " + evaluationProfileHandler.getMaximumRouteDurationViolationWeight());
        OutputManager.println(OutputIds.EVAL_INFO, "Max. ride time violation: " + evaluationProfileHandler.getMaximumRideTimeViolationWeight());
        */
    }
}
