
package hmod.domain.mkp.basic;

import hmod.domain.mkp.Item;
import hmod.domain.mkp.MKPInstance;
import hmod.domain.mkp.Resource;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Enrique Urra C.
 */
class MKPParser
{
    private static final String separator = " +";
    private int lineNumber;
    
    public MKPInstance parse(String file) throws IOException
    {
        lineNumber = 0;
        BufferedReader reader = null;
        Item[] items;
        Resource[] resources;
        int[][] weigths;
        
        try
        {
            reader = new BufferedReader(new FileReader(file));
            
            String[] headers = processLine(reader);
            items = new Item[Integer.parseInt(headers[0])];
            resources = new Resource[Integer.parseInt(headers[1])];
            weigths = new int[resources.length][items.length];
            
            String[] itemProfits = processLine(reader);
            
            for(int i = 0; i < items.length; i++)
                items[i] = new Item(i, Integer.parseInt(itemProfits[i]));
            
            for(int i = 0; i < resources.length; i++)
            {
                String[] itemWeigths = processLine(reader);
                
                for(int j = 0; j < items.length; j++)
                    weigths[i][j] = Integer.parseInt(itemWeigths[j]);
            }
            
            String[] constraints = processLine(reader);
            
            for(int i = 0; i < weigths.length; i++)
                resources[i] = new Resource(i, Integer.parseInt(constraints[i]));
        }
        catch(FileNotFoundException ex)
        {
            throw new IOException("Wrong data file", ex);
        }
        catch(EOFException ex)
        {
            throw new IOException("Unexpected end of file", ex);
        }
        catch(IOException ex)
        {
            throw new IOException("[Line " + lineNumber + "] Error reading file", ex);
        }
        catch(IndexOutOfBoundsException ex)
        {
            throw new IOException("[Line " + lineNumber + "] Wrong number of entries", ex);
        }
        catch(NumberFormatException ex)
        {
            throw new IOException("[Line " + lineNumber + "] Wrong number format", ex);
        }
        finally
        {
            if(reader != null)
                reader.close();
        }
        
        return new DefaultMKPInstance(items, resources, weigths);
    }
    
    private String[] processLine(BufferedReader reader) throws IOException
    {
        lineNumber++;
        String line = reader.readLine();
        
        if(line == null)
            throw new EOFException();
        
        return (line.trim()).split(separator);
    }
}
