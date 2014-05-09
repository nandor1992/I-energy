
public class Launcher {

	public static void main(String[] args) {
		Database database=new Database("jdbc:mysql://localhost:3306/ienergy_database","root", "");
	   
		@SuppressWarnings("unused")
		GUI gui=new GUI(database);

	}

}
