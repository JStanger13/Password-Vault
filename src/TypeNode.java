public class TypeNode {
	String typeName;
	String typeUsername;
	String typePassword;
	
	TypeNode(){
		typeName = "";
		typeUsername = "";
		typePassword = "";
	}
	TypeNode(String typename, String typeusername, String typepassword){
		typeName = typename;
		typeUsername = typeusername;
		typePassword = typepassword;
	}
}
