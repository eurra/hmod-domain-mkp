
package hmod.domain.mkp.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import hmod.core.DataObjectProxy;
import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.Buildable;
import flexbuilders.tree.TreeHandler;
import hmod.domain.mkp.components.CheckCanRemoveItem;
import hmod.domain.mkp.components.GetCurrentItems;
import hmod.domain.mkp.components.ItemData;
import hmod.domain.mkp.components.ItemListData;
import hmod.domain.mkp.components.RemoveItemFromBuild;
import hmod.domain.mkp.components.SelectRandomItem;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class RemoveRandomOperationScript extends BuildScript
{
    private BranchBuilder getCurrents, checkCanRemove, removeRandom;
    private Buildable commonData, itemsData;

    public RemoveRandomOperationScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        getCurrents = branch(MKPIds.MKP_OPERATION_REMOVE_RANDOM);
        checkCanRemove = branch();
        removeRandom = branch();
        
        commonData = ref(MKPIds.MKP_DATA_COMMON);
        itemsData = value(DataObjectProxy.createFor(ItemListData.class, ItemData.class));
    }
    
    @Override
    public void process() throws BuildException
    {
        getCurrents.setBuildable(
            sequentialStep().setNextStep(checkCanRemove).
            addOperator(
                operator(GetCurrentItems.class).
                injectData(itemsData).
                injectData(commonData)
            )
        );
        
        checkCanRemove.setBuildable(
            booleanStep().setTrueStep(removeRandom).
            setDecider(
                operator(CheckCanRemoveItem.class).
                injectData(itemsData)
            )
        );
        
        removeRandom.setBuildable(
            sequentialStep().
            addOperator(
                operator(SelectRandomItem.class).
                injectData(itemsData)
            ).
            addOperator(
                operator(RemoveItemFromBuild.class).
                injectData(itemsData).
                injectData(commonData)
            )
        );
    }
}
