
package hmod.domain.mkp.scripts;

import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.PropertiesConfigScript;
import flexbuilders.tree.TreeHandler;

/**
 *
 * @author Enrique Urra C.
 */
public class ConfigScript extends PropertiesConfigScript
{
    public static final String ENTRY_INSTANCE_FILE = "hmod.domain.mkp.config.instanceFile";
    public static final String ENTRY_FILL_METHOD = "hmod.domain.mkp.config.fillMethod";

    public ConfigScript(TreeHandler input, String propertiesFile) throws BuildException
    {
        super(input, propertiesFile);
    }

    @Override
    public void process() throws BuildException
    {
        branch(MKPIds.MKP_CONFIG_INSTANCE_FILE).setBuildable(value(getEntry(ENTRY_INSTANCE_FILE)));
        branch(MKPIds.MKP_CONFIG_FILL_METHOD).setBuildable(ref(getEntry(ENTRY_FILL_METHOD)));
    }
}
