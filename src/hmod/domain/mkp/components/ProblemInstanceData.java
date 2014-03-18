
package hmod.domain.mkp.components;

import hmod.core.DataInterface;
import hmod.domain.mkp.MKPInstance;

/**
 *
 * @author Enrique Urra C.
 */
public interface ProblemInstanceData extends DataInterface
{
    void setInstanceFile(String file);
    String getInstanceFile();
    
    void setInstance(MKPInstance instance);
    MKPInstance getInstance();
}
