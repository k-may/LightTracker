import processing.serial.*;
Serial myPort;
import controlP5.*;
ControlP5 cp5;

int s0 = 0;
int s1 = 0;
int s2 = 0;
int s3 = 0;
int s4 = 0;
int s5 = 0;
int s6 = 0;

int r = 45;
int padding = 20;
int out = 0;
Knob controller;
void setup() {
  size (200, 800);
  myPort = new Serial(this, Serial.list()[0], 2400);
  smooth();
  noStroke();
  cp5 = new ControlP5(this);
  for (int i = 0 ; i < 7; i ++) {
    controller = cp5.addKnob("s" + i)
      .setRange(0, 100)
        .setValue(0)
          .setPosition(50, (r*2 + padding)*i + padding)
            .setRadius(r)
              .setDragDirection(Knob.HORIZONTAL)
                ;
  }
}

void draw() {
  background(5);
  // println(byte(Servo));
  myPort.write(getSerial());
  delay(15);
  myPort.write('\n');
}

String getSerial() {
  //return "0" + str(s0) + "1" + str(s1)  + "2" + str(s2) + "3" + str(s3) + "4" + str(s4) + "5"+ str(s5) + "6" + str(s6);
  return str(s0) + str(s1) + str(s2) + str(s3) + str(s4) + str(s5) + str(s6);
}

byte[] integerToBytes(int value) {
  byte[] bytes = new byte[2];
  bytes[0] = (byte)((value >> 8) & 0xff);
  bytes[1] = (byte)((value & 0xff));
  return bytes;
}

