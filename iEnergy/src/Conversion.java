
public class Conversion {
 		
public int tempConv(int temp)
{   return temp;
}


public int luminosityConv(int lumi)
{
	int rez=(int) (10+30.3*lumi);
	return rez;
	//in Candella
}

public int proximityConv(int proximity) {
	return proximity;
}

public int currentConv(int current) {
	// TODO Auto-generated method stub
	return current;
}

public int humidityConv(int humidity) {
	// TODO Auto-generated method stub
	return humidity;
}

}