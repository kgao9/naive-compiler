/**
 * Since we add hash maps to the front and remove hash maps from the front,
 * we will implement a stack of hashmaps
 *
 * @author kenny
 * @version 12/12/16
 */

import java.util.*;

public class SymTable {
    private Stack <HashMap <String, Sym>> hashTable = new Stack <HashMap <String, Sym>> ();
    
    /**
     * The purpose of the constructor is to initialize table to contain
     * a single HashMap
     */
    
    public SymTable ()
    {
    	hashTable.push(new HashMap <String, Sym> ());
    }
    
    /**
     * if name is null, sym is null, or name == '', throw null pointer
     * if hash table is empty, throw table is empty error
     * if duplicate sym, throw error
     * Else, add to first hash map
     * 
     * @param name
     * @param sym
     * @throws DuplicateSymException
     * @throws EmptySymTableException
     * @throws NullPointerException
     */
    public void addDecl(String name, Sym sym) throws DuplicateSymException, EmptySymTableException
    {
    	//error checking
    	if(hashTable.empty())
    	{
    		throw new EmptySymTableException();
    	}
    	
    	if(name.equals("") || name == null || sym == null)
    	{
    		throw new NullPointerException();
    	}
    	
    	//get first
    	HashMap <String, Sym> first = hashTable.pop();
    	
    	//check for errors
    	if(first.containsKey(name))
    	{
    		throw new DuplicateSymException();
    	}
    	
    	//otherwise add name and sym to map and push back
    	first.put(name, sym);
    	hashTable.push(first);
    }
    
    /**
     * Adds new hashmap to stack
     */
    
    public void addScope()
    {
    	hashTable.push(new HashMap <String, Sym> ());
    }
    
    /**
     * If hashtable is empty, throw exception
     * Otherwise, if first hashmap contains key, return said Sym
     * Else return null
     * @param name
     * @return symbol if found, null otherwise
     * @throws EmptySymTableException
     */
    
    public Sym lookupLocal(String name) throws EmptySymTableException
    {
    	if(hashTable.empty())
    		throw new EmptySymTableException();
    	
    	HashMap <String, Sym> first = hashTable.peek();
    	
    	if(first.containsKey(name))
    	{
    		return first.get(name);
    	}
    	
    	return null;
    }
    
    /**
     * We are going to look globally
     * If hash table is empty, throw exception
     * If any hashmap contains name, return symbol
     * null otherwise
     * @param name
     * @return symbol if found, null otherwise
     * @throws EmptySymTableException
     */
    
    public Sym lookupGlobal(String name) throws EmptySymTableException
    {
    	if(hashTable.empty())
    		throw new EmptySymTableException();
    	
    	//flag for if found or not
    	boolean found = false;
    	
    	Stack <HashMap <String, Sym>> temp = new Stack <HashMap <String, Sym>> ();
    	
    	Sym symbolFound = null;
    	
    	//while symbol not found and the table isn't empty
    	//we will look to see if it is in the first table
    	//if it is, we stop and put our original stack back together
    	//if it is not, we keep going until our table is empty.
    	while(!found && !hashTable.empty())
    	{
    		symbolFound = lookupLocal(name);
    		
    		if(symbolFound != null)
    		{
    			found = true;
    		}
    		
    		else
    		{
    			temp.push(hashTable.pop());
    		}
    	}
    	
    	//put table back together
    	while(!temp.empty())
    	{
    		hashTable.push(temp.pop());
    	}
    	
    	//if found, return found Obj
    	if(found)
    	{
    		return symbolFound;
    	}
    	
    	return null;
    }
    
    /**
     * If empty, throw exception, else
     * removes first hashmap from stack
     * @throws EmptySymTableException
     */
    
    public void removeScope() throws EmptySymTableException
    {
    	if(hashTable.empty())
    		throw new EmptySymTableException();
    	
    	hashTable.pop();
    }
    
    /**
     * For debugging
     * prints out everything in hashtable in a way that is easy to read
     */
    
    public void print()
    {
    	System.out.println("\nSym Table\n");
    	
        Stack <HashMap <String, Sym>> temp = new Stack <HashMap <String, Sym>> ();
    	
    	//for each hash map
    	//print it
    	//Put stack back together
    	while(!hashTable.empty())
    	{
    		HashMap <String, Sym> first = hashTable.peek();
    		
    		System.out.println(first.toString());
  
    		temp.push(hashTable.pop());
    	}
    	
    	//put table back together
    	while(!temp.empty())
    	{
    		hashTable.push(temp.pop());
    	}
    }
}
