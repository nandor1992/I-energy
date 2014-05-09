import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 

import java.util.Enumeration;

public class ArduinoTransmission implements SerialPortEventListener {
	SerialPort serialPort;
	Conversion convert=new Conversion();
        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			"COM6", // Windows
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	@SuppressWarnings("unused")
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
	
    int temp, humidity, proximity, luminosity, current;
    
    Database database;
        
    public ArduinoTransmission(Database database){
    	this.database=database;
    }
	public void fromStringToData(String rawData){
		String aux;
		rawData=rawData+";; ";
		char[] string = new char[100];
		string=rawData.toCharArray();
		//System.out.println(string);
		char[] dataArray=new char[100];
		
		
		int i=0, j, dataIndex=0;
		
		while(dataIndex<5){		
		      while(!(string[i]>='0' && string[i]<='9') && string[i]!='\n')
		      {     	  i++;
		         	
		      }
		      
		    dataArray[0] = string[i];
		    j=1;
		    i++;
		    while(string[i]>='0' && string[i]<='9' && string[i]!='\n')
	      	{      
			       dataArray[j]=string[i];
			       i++; j++;
	     	}
		
		
		  dataIndex++;
		  aux=String.valueOf(dataArray, 0, j);
		 switch (dataIndex) {
		     case 1:  
		    	     temp= Integer.parseInt(aux);		    	     
	               break;
             case 2:  humidity= Integer.parseInt(aux);                     
                   break;
             case 3:  luminosity = Integer.parseInt(aux);
                   break;
             case 4:  proximity =Integer.parseInt(aux);
                   break;
             case 5:  current = Integer.parseInt(aux);
                   break;
      }
	}
	}
	public void initialize() {
		CommPortIdentifier portId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}


	 /* Handle an event on the serial port. Read the data and print it.
	 */
	
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				
				System.out.print(inputLine);
			    
				fromStringToData(inputLine);
			    System.out.println("inserting the following: "+temp+" hum "+humidity+" current "+current+" lumina "+luminosity);
			    int temperature=convert.tempConv(temp);
			    proximity=convert.proximityConv(proximity);
			    current=convert.currentConv(current);
			    luminosity=convert.luminosityConv(luminosity);
			    humidity=convert.humidityConv(humidity);
			    System.out.println("inserting the following: temp:"+temperature+" hum "+humidity+" current "+current+" lumina "+luminosity);
			    database.insertIntoDatabase(temperature, proximity,  current,  luminosity,  humidity);
			    System.out.println("Inserted");
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	/*
	public static void main(String[] args) throws Exception {
		 SerialTest main = new SerialTest();
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started"); 
		

		
	} */
}