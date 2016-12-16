import java.util.Iterator;
import java.util.LinkedList;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Vault {
	int size;
	LinkedList<VaultNode> memory[];
	int tableSize;
	VaultNode current;

	Vault(){
		size = 0;
		memory = new LinkedList[100]; 
		tableSize = 100;
		current = null;
	}
	
	//methods defined in the VaultNode class~

	
	protected void finalize(){
		saveRecords();
		System.out.println("Calling finalized function");
	}
	
	public void loadRercords(){
		//NOTE: when would this be called?? think carefully, what makes more sense
		//reads from text file (for now)
		//parses input into valid Vault Accounts and valid sub Type Accounts
		
		try {
			BufferedReader read = new BufferedReader(new FileReader("records.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void saveRecords(){
		
		System.out.println("In SaveRecords function");
		
		String Filename = "records.txt";
		BufferedWriter Writer = null;
		try {
			Writer = new BufferedWriter(new FileWriter(Filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//char[] string;		
		
		for(int i = 0; i < tableSize; i++){
			if(memory[i] != null){
				Iterator <VaultNode> save = memory[i].iterator();
				while(save.hasNext()){
					VaultNode saveVault = save.next();
					try {
						Writer.write(saveVault.username + " " + saveVault.password + " " + saveVault.accountsize + " ");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//VaultNode Saves
					if(saveVault.Accounts != null){
						Iterator <TypeNode> sub = saveVault.Accounts.iterator(); 
						while(sub.hasNext()){
							TypeNode saveType= sub.next();
							try {
								//Writer.write("Hello");
								Writer.write(saveType.typeName + " " + saveType.typeUsername + " " + saveType.typePassword);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							////print this VaultNode to text file;
						}
					}

					try {
						Writer.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		try {
			Writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add(String typename, String typeusername, String typepassword){
		if(current != null){
			current.addSubAccount(typename, typeusername, typepassword);
		}else{
			System.out.println("Must Login First!");
			System.out.println("");
		}
	}
	
	public void delete(String typename){
		if(current != null)current.deleteSubAccount(typename);
	}
	
	public void viewSubAccount(String typename){
		if(current != null)current.viewSubAccount(typename);
	}
	
	public void viewAllAccounts(){
		if(current != null)current.viewAllAcounts();
	}
	
	//methods defined in the Vault class
	public void login(String username, String password){
		VaultNode user = loginValid(username, password);
		if(user != null){
			current = user;	
			return;
		}
		System.out.print("Your account cannot be found.");
	}
	
	public void logout(){
		//save current back to hash table
		int key = hashFunction(current.username);
		ListIterator<VaultNode> temp = memory[key].listIterator();
		
		while(temp.hasNext() == true){
			if(temp.next().username == current.username){
				temp.set(current);
			}
		}
		current = null;
	}
	
	public int getSize(){
		return size;
	}
	
	private int hashFunction(String key){
		int hashVal = 0;
		for(int i = 0; i < key.length(); i++){
			hashVal += key.charAt(i);
		}
		return hashVal % tableSize;
	}
	
	public void insert(String username, String password){
		VaultNode user = new VaultNode(username, password);
		lookup(username).add(user);
		size++;
	}
	
	public LinkedList<VaultNode>lookup(String username){
		return memory[hashFunction(username)];
	}
	
	public VaultNode lookupVault(String username){
		int key = hashFunction(username);
		if (memory[key] == null){
			memory[key] = new LinkedList<VaultNode>();
			return null;
		}
		Iterator<VaultNode>temp = memory[key].iterator();
		VaultNode user = new VaultNode();
		while(temp.hasNext() == true){
			user = (VaultNode) temp.next();
			System.out.println("Vault Username: " + user.username);
			System.out.println("Loggin Username: " + username);
			if(username  == user.username){
				return user;
			}
		}
		return null;
	}
	
	public VaultNode loginValid(String username, String password){
		VaultNode user = lookupVault(username);
			if(user == null){
				return null;
			}
			if(password.equals(user.password) == true){
				System.out.println("12345");
				return user;
			}
			System.out.print("Password is invalid.");
			return null;
		}
	
	public void createAccount(String username, String password){
		if(lookupVault(username) != null){
			System.out.println("Username taken already");
			return;
		}
		if(passwordVal(password) == false){
			System.out.println("Password does not meet requirements.");
				return;
		}
		current = new VaultNode(username, password);
		System.out.println("Password has been created.");
		insert(username, password);
		System.out.println("Account has been created.");
	}
	
	public void delete(String username, String password){
		if(loginValid(username, password) != null ){
			memory[hashFunction(username)] = null;
			System.out.println("Accaount has been deleted!");
			size--;
			return;
		}
		System.out.println("Failed to delete.");
	}
	
	private boolean passwordVal(String password){
		if(password.length() < 6){
			return false;
		}
		boolean num = false;
		boolean upperCase = false;
		boolean lowerCase = false;
		boolean specialCharacter = false;
			
			for(int i = 0; i < password.length(); i++){
				int p = password.charAt(i);
				//System.out.println("Char = " + c);
				//System.out.println("ASCII int = " + p);
				
				 if(p > 96 && p < 123){  	//check for lower case
						 lowerCase = true;
				 } else if(p > 64 && p < 90){	//check for upper case
					 upperCase = true;
				 } else if(p > 47 && p < 58) {	//checking for numbers
				    	num = true;
				  } else if(p > 32 && p < 48){ 	//check for special characters
					  specialCharacter = true;
				  }
			}
			if(num == true && upperCase== true && lowerCase == true && specialCharacter== true){
				System.out.println("Password is valid");
				return true;
	    	}else{		    		
	    		System.out.println("Password is invalid");
	    		return false;
	    	}
		}	
}



	