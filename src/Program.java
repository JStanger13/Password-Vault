
public class Program {

	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		System.runFinalizersOnExit(true);
		
		Vault user = new Vault();
		//new user
		user.createAccount("Justin", "Password91!");
		user.add("Netflix","Jusstin5" ,"password");
		user.add("Amazon", "Luis10","1234password");
		user.logout();
		
		//new user
		user.createAccount("Luis", "1234paSsword!");
		user.add("HBO Go", "Bill", "1234somethingIDK");
		user.logout();
		
		//new user
		user.createAccount("Bill", "Thisisapassowrd!78");
		user.add("Gmail", "BillMail", "Petsname23");
		user.logout();
	
	}
}

