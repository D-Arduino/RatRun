 import processing.sound.*;
SoundFile soundtrack;

PImage wall1;
PImage wall2;
PImage ShopKrisa;
PImage Krisa;
PImage Menu;
PImage Shop;
PImage Doshik;
PImage Musor;

int Price[] = 
  {0, 25, 50, 60, 75, 
  80, 100, 110, 120};

boolean bought_skin[] = {true, true, false, false, false, false, false, false, false};

boolean ShopFlag;
boolean Ddside = false;
boolean Mmside = true;
int Dside = 32;
int Mside = 267;
int score = 0;
int skin;
int Statik_skin;
int speed = 2;

long Timer;
long Timer1;
long Timer2;
long Timer3;

float wall1Y;
float DoshikY = -100;
float MusorY = -100;
float wall2Y = -700;

boolean side;
boolean DoshikR = true;
boolean MusorR = true;
int menu;
String[] progras;
String[] skins;
String[] currentLine;
String[] currentSkins;
boolean te;
void setup() {
  size(400, 700);
  strokeWeight(90);
  textFont(createFont("rostov.ttf", 30, true));
  textSize(30);
  background(0);

  soundtrack = new SoundFile(this, "Peremen.aiff");

  wall1 = loadImage("Steni1.png");
  wall2 = loadImage("Steni2.png");
  Menu = loadImage("Menu.png");
  Shop = loadImage("Shop.png");
  Doshik = loadImage("Mel.png");
  Musor = loadImage("Mana.png");

  progras = loadStrings("progras.mouse");
  skins = loadStrings("skins.mouse");

  for ( int i = 0; i < progras.length; i++ ) {
    currentLine = split(progras[i], ";");
    for (int j = 0; j < currentLine.length; j++) {
      println( currentLine[j] );
    }
  }
  for ( int d = 0; d < skins.length; d++ ) {
    currentSkins = split(skins[d], ";");
    for (int s = 0; s < currentSkins.length; s++) {
      bought_skin[s] = boolean(currentSkins[s]);
      ;
      println( bought_skin[s] );
    }
  }
  score = int(currentLine[0]);
  Statik_skin = int(currentLine[1]);
  switch (int(currentLine[1])) {
  case 0: 
    Krisa = loadImage("АМУСИС.png");
    break;
  case 1: 
    Krisa = loadImage("Ananim_Kris.png");
    break;
  case 2: 
    Krisa = loadImage("ВДВ_Kris.png");
    speed = 3;
    break;
  case 3: 
    Krisa = loadImage("ICE_Kris.png");
    break;
  case 4: 
    Krisa = loadImage("CHOKO_Kris.png");
    break;
  case 5: 
    Krisa = loadImage("SHREK_Kris.png");
    break;
  case 6: 
    Krisa = loadImage("GOLDEN_Kris.png");
    break;
  case 7: 
    Krisa = loadImage("AMONGUS_Kris.png");
    break;
  case 8: 
    Krisa = loadImage("KIBER_Kris.png");
    speed = 4;
    break;
  }
       soundtrack.play();
}
void draw() {
  background(139);

  switch (menu) {
  case 1: 
    OtrisivkaRun();
    break;
  case 0: 
    OtrisivkaMenu();
    break;
  case 2: 
    OtrisivkaShop();
    break;
  }
  textSize(30);
  text(score, 33, 33);
  fill(#D6FC7D);
  

   if (millis() - Timer1 >= 80000) {   
      Timer1 = millis();
   soundtrack.play();
  }
}
