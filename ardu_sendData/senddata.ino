#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
#include <DHT11.h>
/*-----( Declare Constants and Pin Numbers )-----*/
#define CE_PIN   9
#define CSN_PIN 10
#define JOYSTICK_X A0
#define JOYSTICK_Y A1

// NOTE: the "LL" at the end of the constant is "LongLong" type
const uint64_t pipe = 0xE8E8F0F0E1LL; // Define the transmit pipe
int pin=4;
DHT11 dht11(pin); 

/*-----( Declare objects )-----*/
RF24 radio(CE_PIN, CSN_PIN); // Create a Radio
/*-----( Declare Variables )-----*/
int data[3];  // 3 element array holding Joystick readings
float humi, temp;
void setup()   /****** SETUP: RUNS ONCE ******/
{
  Serial.begin(9600);
  radio.begin();
  radio.openWritingPipe(pipe);
}//--(end setup )---


void loop()   /****** LOOP: RUNS CONSTANTLY ******/
{ dht11.read(humi, temp);
  Serial.print("Humidity:");
  Serial.print(humi);
  data[0]=humi;
  
  //int temperature = analogRead(0); 
  int temperature =(int)readTempInCelsius(10,0);
  
  Serial.print("Temp: ");
  Serial.print(temperature);
  data[1]=temperature;
  
  int light = analogRead(1);
   Serial.print(" light: ");
  Serial.println(light);
  data[2]=light;
  
  
  radio.write(data, sizeof(data));

}//--(end main loop )---

/*-----( Declare User-written Functions )-----*/


float readTempInCelsius(int count, int pin) {
  float temperaturaMediata = 0;
  float sumaTemperatura;
  for (int i =0; i < count; i++) {
    int reading = analogRead(pin);
    float voltage = reading * 5.0;
    voltage /= 1024.0;
    float temperatureCelsius = (voltage - 0.5) * 100 ;
    sumaTemperatura = sumaTemperatura + temperatureCelsius;
  }
  return sumaTemperatura / (float)count;
}
