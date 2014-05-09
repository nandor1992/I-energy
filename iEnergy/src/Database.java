import java.sql.*;
import java.util.Calendar;

public class Database {
	
    private  Connection conn;
    private String address, username, password, status;
    private Statement statement;
    
    Database(String address, String username, String password)
    {
    	this.address=address;
    	this.username=username;
    	this.password=password;
    	
    }
    
	public void connectToDatabase(){
         try {
             //incarcare driver petru baza de date
             Class.forName("com.mysql.jdbc.Driver");
            
             //conectare la baza de date
             conn = DriverManager.getConnection(address,username,password);
             System.out.println("Database connected");
             status="Database connected";
           
             
         } catch (Exception ex) {
             System.out.println("Could not connect");
             status="Could not connect";
         }
        //deconectare
        

         
}

	public void disconnetFromDatabase(){
	
			try {
				conn.close();
				System.out.println("Database disconnected");
				 status="Database disconnected";
			}
			catch (Exception ex2){
				System.out.println("Could not disconnect");
				status="Could not disconnect";
			}
		}
	
	public String getStatus(){
		return status;
	}
	
	public int getId(){
		int id=0;
		try {
			statement=conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT id FROM ienergy");
			while(result.next()){
			        id = result.getInt(1);	      
			      }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id+1;
	}
	
	public void insertIntoDatabase(float temperature, int proximity, int current, int luminosity, int humidity){
		int id=getId();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		month=month+1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int day_of_week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		day_of_week--;
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int min = Calendar.getInstance().get(Calendar.MINUTE);
		int sec = Calendar.getInstance().get(Calendar.SECOND);
		try {
			statement=conn.createStatement();
			statement.executeUpdate("INSERT INTO `ienergy_database`.`ienergy` (`id`,`Year`, `Month`, `Day`, `Week_Day`, `Hour`, `Min`, `Sec`,`Temperature`, `Proximity`, `Current`, `Luminosity`, `Humidity`) VALUES ("+id+","+year+","+month+","+day+","+day_of_week+","+hour+","+min+","+sec+","+temperature+","+proximity+","+current+","+luminosity+","+humidity+" ); "); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
	}
	
}          