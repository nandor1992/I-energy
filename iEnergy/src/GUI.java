import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	
    private JPanel panel=new JPanel();
	private JButton connectButton=new JButton("Connect");
	private JButton disconnectButton=new JButton("Disconnect");
	private JTextField textField=new JTextField();
	
	Database database;
	
	public GUI(final Database database){
		 this.database=database;
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setSize(300,300);
         this.setTitle("Database Manipulation");
         this.setLocationRelativeTo(null);
         
         panel.setLayout(null);
         panel.setSize(300,300);              
         connectButton.setBounds(30,30,100,50);
         disconnectButton.setBounds(150,30,100,50);
         textField.setBounds(50,100, 180, 40);
         textField.setHorizontalAlignment(JTextField.CENTER);
         panel.setBackground(Color.pink);
         panel.add(connectButton);
         panel.add(disconnectButton);
         panel.add(textField);
         this.add(panel);
         
         this.setVisible(true);
         
         connectButton.addActionListener(this);
        	  
         disconnectButton.addActionListener(this);
	}
	
	
	 public void actionPerformed(ActionEvent a){
               
		 ArduinoTransmission main = new ArduinoTransmission(database);
		/*	Thread t=new Thread() {
   				public void run() {
   					//the following line will keep this app alive for 1000 seconds,
   					//waiting for events to occur and responding to them (printing incoming messages to console).
   					try { Thread.sleep(1000000);
   					} 
   					catch (InterruptedException ie) {}
   				} 
   		    	}; */
		 
		 JButton pressedButton=(JButton) a.getSource();
               if (pressedButton.getText()=="Connect")
               {
            	    database.connectToDatabase();
            	    textField.setText(database.getStatus());
            	   
            	    if(database.getStatus()=="Database connected")
            	    { main.initialize();
       		    	 
       			//    t.start();
       			    System.out.println("Started"); 
                  } 
                    else System.out.println("Please connect the database first");
               }
               else if(pressedButton.getText()=="Disconnect")
               {
            	   database.disconnetFromDatabase();
            	   main.close();
            	   textField.setText(database.getStatus());
               }
	 }
}
