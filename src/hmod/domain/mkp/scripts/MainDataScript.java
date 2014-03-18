
package hmod.domain.mkp.scripts;

import static flexbuilders.basic.BasicBuilders.*;
import static flexbuilders.basic.SetterBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.core.DataObjectProxy;
import hmod.domain.mkp.components.ProblemInstanceData;
import hmod.domain.mkp.components.SolutionBuilderData;
import hmod.domain.mkp.components.SolutionData;

/**
 *
 * @author Enrique Urra C.
 */
public class MainDataScript extends BuildScript
{
    public MainDataScript(TreeHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        Object dataObj = DataObjectProxy.createFor(
            ProblemInstanceData.class,
            SolutionData.class,
            SolutionBuilderData.class
        );
        
        branch(MKPIds.MKP_DATA_COMMON).setBuildable(
            setterInvoker(value(dataObj)).
            set(beanSetter().setMethodName("setInstanceFile"), ref(MKPIds.MKP_CONFIG_INSTANCE_FILE))
        );
    }
}
