import java.util.*;

//Make sure you make certain methods private!!!
public class VaultNode {
	String username;
	String password;
	LinkedList<TypeNode> Accounts;
	int accountsize;

	public VaultNode(String userName, String Password) {
		username = userName;
		password = Password;
		Accounts = new LinkedList<TypeNode>();
		accountsize = 0;
	}
	public VaultNode(){
		username = "";
		password = "";
		Accounts = new LinkedList<TypeNode>();
		accountsize = 0;
	}
	public TypeNode addSubAccount(String typename, String typeusername, String typepassword){
		TypeNode user = new TypeNode(typename, typeusername, typepassword);
		Accounts.add(user);
		accountsize++;
		return user;
	}

	//replace Accounts.remove() with an iterator through the linked list...
	//this way you DON'T need the username/password
	public TypeNode deleteSubAccount(String typename){
		//TypeNode user = new TypeNode(typename, typeusername, typepassword);
		Iterator <TypeNode> user = Accounts.iterator();
		TypeNode temp = new TypeNode(); 
		while(Accounts != null){
			temp = user.next();
			if(temp.typeName == typename){
				user.remove();
				accountsize--;
			}
		}
		return temp;
	}
	
	
	public void viewSubAccount(String typename){
		Iterator<TypeNode>temp = Accounts.iterator();
		while(temp.hasNext() == true){
			TypeNode user = (TypeNode) temp.next();
			if(typename == user.typeName){
				System.out.println("Account Type = " + user.typeName);
				System.out.println("Username = " + user.typeUsername);
				System.out.println("Password = " + user.typePassword);
				return;
			}
		}
		System.out.println("Could not find account!");
		return;
	}
	
	public void viewAllAcounts(){
		Iterator<TypeNode>temp = Accounts.iterator();
		System.out.println("");
		System.out.println("Number of Accounts: " + accountsize);
		System.out.println("");
		while(temp.hasNext() == true){
			TypeNode user = (TypeNode) temp.next();
			System.out.println("Account Type = " + user.typeName);
			System.out.println("Username = " + user.typeUsername);
			System.out.println("Password = " + user.typePassword);
			System.out.println("");
		}
		return;
	}
}
