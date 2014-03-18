
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.components.ReportSolution;

/**
 *
 * @author Enrique Urra C.
 */
public class ReportSolutionScript extends BuildScript
{
    public ReportSolutionScript(TreeHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {        
        branch(MKPIds.MKP_REPORT_SOLUTION).setBuildable(
            sequentialStep().
            addOperator(
                operator(ReportSolution.class).
                injectData(ref(MKPIds.MKP_DATA_COMMON))
            )
        );
    }
}
