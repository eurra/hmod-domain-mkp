
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import hmod.core.DataObjectProxy;
import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.Buildable;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.components.AddItemToBuild;
import hmod.domain.mkp.components.CheckRemainingItems;
import hmod.domain.mkp.components.GetAvailableItems;
import hmod.domain.mkp.components.ItemData;
import hmod.domain.mkp.components.ItemListData;
import hmod.domain.mkp.components.SelectRandomItem;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class AddRandomOperationScript extends BuildScript
{
    private BranchBuilder getAvailable, checkRemaining, selectRandom, addItemToBuild;
    private Buildable itemsData, commonData;
    
    public AddRandomOperationScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        getAvailable = branch(MKPIds.MKP_OPERATION_ADD_RANDOM);
        checkRemaining = branch();
        selectRandom = branch();
        addItemToBuild = branch();
        
        itemsData = value(DataObjectProxy.createFor(
            ItemListData.class,
            ItemData.class
        ));
        
        commonData = ref(MKPIds.MKP_DATA_COMMON);
    }
    
    @Override
    public void process() throws BuildException
    {
        getAvailable.setBuildable(
            sequentialStep().setNextStep(checkRemaining).
            addOperator(
                operator(GetAvailableItems.class).
                injectData(itemsData).
                injectData(commonData)
            )
        );
        
        checkRemaining.setBuildable(
            booleanStep().setTrueStep(selectRandom).
            setDecider(
                operator(CheckRemainingItems.class).
                injectData(itemsData)
            )
        );
        
        selectRandom.setBuildable(
            sequentialStep().setNextStep(addItemToBuild).
            addOperator(
                operator(SelectRandomItem.class).
                injectData(itemsData)
            )
        );
        
        addItemToBuild.setBuildable(
            sequentialStep().
            addOperator(
                operator(AddItemToBuild.class).
                injectData(itemsData).
                injectData(commonData)
            )
        );
    }
}
