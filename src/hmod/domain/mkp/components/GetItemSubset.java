
package hmod.domain.mkp.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.domain.mkp.Item;
import java.util.ArrayList;
import java.util.List;
import optefx.util.random.RandomTool;

/**
 *
 * @author Enrique Urra C.
 */
public class GetItemSubset implements Operator
{
    private ItemListData origListData;
    private ItemListData destListData;

    public void setOrigListData(ItemListData origListData)
    {
        this.origListData = origListData;
    }

    public void setDestListData(ItemListData destListData)
    {
        this.destListData = destListData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        List<Item> origList = origListData.getItems();
        List<Item> destList = new ArrayList<>(origList);
        int randCount = RandomTool.getInt(origList.size());
        
        for(int i = 0; i < randCount; i++)
            destList.remove(RandomTool.getInt(destList.size()));
        
        destListData.setItems(destList);
    }
}
