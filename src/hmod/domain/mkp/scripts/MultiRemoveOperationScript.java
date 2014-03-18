
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import hmod.core.DataObjectProxy;
import flexbuilders.core.Buildable;
import hmod.domain.mkp.components.CheckCanRemoveItem;
import hmod.domain.mkp.components.GetCurrentItems;
import hmod.domain.mkp.components.GetItemSubset;
import hmod.domain.mkp.components.ItemData;
import hmod.domain.mkp.components.ItemListData;
import hmod.domain.mkp.components.RemoveItemFromBuild;
import hmod.domain.mkp.components.SelectRandomItem;
import static flexbuilders.basic.BasicBuilders.*;
import static flexbuilders.basic.SetterBuilders.*;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.components.CheckRemainingItems;
import hmod.domain.mkp.components.RemoveItemFromList;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class MultiRemoveOperationScript extends BuildScript
{
    private BranchBuilder getCurrents, checkCanRemove, getSubset, checkRemaining, removeRandom;
    private Buildable commonData, origItemsData, destItemsData;

    public MultiRemoveOperationScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        getCurrents = branch(MKPIds.MKP_OPERATION_MULTI_REMOVE);
        checkCanRemove = branch();
        getSubset = branch();
        checkRemaining = branch();
        removeRandom = branch();
        
        commonData = ref(MKPIds.MKP_DATA_COMMON);
        origItemsData = value(DataObjectProxy.createFor(ItemListData.class));
        destItemsData = value(DataObjectProxy.createFor(ItemListData.class, ItemData.class));
    }
    
    @Override
    public void process() throws BuildException
    {
        getCurrents.setBuildable(
            sequentialStep().
            setNextStep(checkCanRemove).
            addOperator(
                operator(GetCurrentItems.class).
                injectData(origItemsData).
                injectData(commonData)
            )
        );
        
        checkCanRemove.setBuildable(
            booleanStep().setTrueStep(getSubset).
            setDecider(
                operator(CheckCanRemoveItem.class).
                injectData(origItemsData)
            )
        );
        
        getSubset.setBuildable(
            sequentialStep().setNextStep(checkRemaining).
            addOperator(
                operator(GetItemSubset.class).
                injectData(beanSetter().setMethodName("setOrigListData"), origItemsData).
                injectData(beanSetter().setMethodName("setDestListData"), destItemsData)
            )
        );
        
        checkRemaining.setBuildable(
            booleanStep().setTrueStep(removeRandom).
            setDecider(
                operator(CheckRemainingItems.class).
                injectData(destItemsData)
            )
        );
        
        removeRandom.setBuildable(
            sequentialStep().setNextStep(checkRemaining).
            addOperator(
                operator(SelectRandomItem.class).
                injectData(destItemsData)
            ).
            addOperator(
                operator(RemoveItemFromBuild.class).
                injectData(destItemsData).
                injectData(commonData)
            ).
            addOperator(
                operator(RemoveItemFromList.class).
                injectData(destItemsData)
            )
        );
    }
}
