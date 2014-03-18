
package hmod.hyperheuristic.adapter.basic.mkp;

import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.scripts.MKPIds;

/**
 *
 * @author Enrique Urra C.
 */
public class OperatorsScript extends BuildScript
{
    public OperatorsScript(TreeHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        branch("mkp_baseAdapter_decoder").setBuildable(
            operator(MKPDecode.class)
        );
        
        branch("mkp_baseAdapter_encoder").setBuildable(
            operator(MKPEncode.class)
        );
        
        branch("mkp_baseAdapter_downloader").setBuildable(
            operator(MKPDownload.class).
            injectData(ref(MKPIds.MKP_DATA_COMMON))
        );
        
        branch("mkp_baseAdapter_uploader").setBuildable(
            operator(MKPUpload.class).
            injectData(ref(MKPIds.MKP_DATA_COMMON))
        );
    }
}
