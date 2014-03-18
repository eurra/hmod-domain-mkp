
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import hmod.core.DataObjectProxy;
import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.Buildable;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.components.AddItemToBuild;
import hmod.domain.mkp.components.CheckFeasibleAdd;
import hmod.domain.mkp.components.CheckRemainingItems;
import hmod.domain.mkp.components.GetAvailableItems;
import hmod.domain.mkp.components.ItemData;
import hmod.domain.mkp.components.ItemListData;
import hmod.domain.mkp.components.RemoveItemFromList;
import hmod.domain.mkp.components.SelectRandomItem;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class RandomFillOperationScript extends BuildScript
{
    private BranchBuilder getAvailable, selectRandom, checkFeasibleAdd, addToBuild, removeFromList, checkRemaining;
    private Buildable itemsData, commonData;

    public RandomFillOperationScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        getAvailable = branch(MKPIds.MKP_OPERATION_RANDOM_FILL);
        selectRandom = branch();
        checkFeasibleAdd = branch();
        addToBuild = branch();
        removeFromList = branch();
        checkRemaining = branch();
        
        commonData = ref(MKPIds.MKP_DATA_COMMON);
        itemsData = value(DataObjectProxy.createFor(ItemListData.class, ItemData.class));
    }
    
    @Override
    public void process() throws BuildException
    {
        getAvailable.setBuildable(
            sequentialStep().setNextStep(selectRandom).
            addOperator(
                operator(GetAvailableItems.class).
                injectData(itemsData).
                injectData(ref(MKPIds.MKP_DATA_COMMON))
            )
        );
        
        selectRandom.setBuildable(
            sequentialStep().setNextStep(checkFeasibleAdd).
            addOperator(
                operator(SelectRandomItem.class).
                injectData(itemsData)
            )
        );
        
        checkFeasibleAdd.setBuildable(
            booleanStep().setTrueStep(addToBuild).setFalseStep(removeFromList).
            setDecider(
                operator(CheckFeasibleAdd.class).
                injectData(itemsData).
                injectData(commonData)
            )
        );
        
        addToBuild.setBuildable(
            sequentialStep().setNextStep(removeFromList).
            addOperator(
                operator(AddItemToBuild.class).
                injectData(itemsData).
                injectData(commonData)
            )
        );
        
        removeFromList.setBuildable(
            sequentialStep().setNextStep(checkRemaining).
            addOperator(
                operator(RemoveItemFromList.class).
                injectData(itemsData)
            )
        );
        
        checkRemaining.setBuildable(
            booleanStep().setTrueStep(selectRandom).
            setDecider(
                operator(CheckRemainingItems.class).
                injectData(itemsData)
            )
        );
    }
}
