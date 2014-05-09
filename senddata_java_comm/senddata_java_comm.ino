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
const uint64_t pipes[2] = {0xDEDEDEDEE9LL , 0xDEDEDEDEE7LL };
int pin=4;
DHT11 dht11(pin); 
char RecvPayload[31] = "";
/*-----( Declare objects )-----*/
RF24 radio(CE_PIN, CSN_PIN); // Create a Radio
/*-----( Declare Variables )-----*/
int data[3];  // 3 element array holding Joystick readings
float humi, temp;
char incomingChar;
void setup()   /****** SETUP: RUNS ONCE ******/
{
  Serial.begin(9600);
   pinMode(7, OUTPUT);
  radio.begin();
  radio.openWritingPipe(pipes[1]);
  radio.openReadingPipe(0,pipes[0]); 
  radio.stopListening();
}//--(end setup )---


void loop()   /****** LOOP: RUNS CONSTANTLY ******/
{ 
  radio.startListening();
  int len = 0;
  if ( radio.available() ) {
      bool done = false;
      while ( !done ) {
        len = radio.getDynamicPayloadSize();
        done = radio.read(&RecvPayload,len);
        delay(5);
      }
  
    RecvPayload[len] = 0; // null terminate string
    Serial.write(RecvPayload);
  }
  if (Serial.available())
  {
    incomingChar = Serial.read();
  }
  radio.stopListening();
  radio.write(&incomingChar, sizeof(incomingChar));
  
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
