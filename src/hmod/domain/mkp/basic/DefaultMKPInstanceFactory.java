
package hmod.domain.mkp.basic;

import hmod.domain.mkp.MKPSolutionBuilder;
import hmod.domain.mkp.MKPInstanceFactory;
import hmod.domain.mkp.MKPInstance;
import java.io.IOException;

/**
 *
 * @author Enrique Urra C.
 */
public class DefaultMKPInstanceFactory implements MKPInstanceFactory
{
    @Override
    public MKPInstance createProblemInstance(String input)
    {
        try
        {
            return new MKPParser().parse(input);
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
