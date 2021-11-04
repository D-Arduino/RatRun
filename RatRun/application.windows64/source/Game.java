import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Game extends PApplet {

 
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
public void setup() {
  
  strokeWeight(90);
  textFont(createFont("rostov.ttf", 30, true));
  textSize(30);
  background(0);

  soundtrack = new SoundFile(this, "Peremen.aiff");

  wall1 = loadImage("Steni1.png");
  wall2 = loadImage("Steni2.png");
  Menu = loadImage("Menu.png");
  Shop = loadImage("Shop.png");
  Doshik = loadImage("Doshik.png");
  Musor = loadImage("packet.png");

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
      bought_skin[s] = PApplet.parseBoolean(currentSkins[s]);
      ;
      println( bought_skin[s] );
    }
  }
  score = PApplet.parseInt(currentLine[0]);
  Statik_skin = PApplet.parseInt(currentLine[1]);
  switch (PApplet.parseInt(currentLine[1])) {
  case 0: 
    Krisa = loadImage("Krisa.png");
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
public void draw() {
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
  fill(0xffD6FC7D);
  

   if (millis() - Timer1 >= 80000) {   
      Timer1 = millis();
   soundtrack.play();
  }
}
public void OtrisivkaRun() {

  
  image(wall1, 0, wall1Y); 
  image(wall2, 0, wall2Y);  
if (side)  image(Krisa, 33, 550); 
if (!side) image(Krisa, 240, 550); 


  wall2Y+=speed;
  wall1Y+=speed;


if (wall2Y > 700) wall2Y = -700;
if (wall1Y > 700) wall1Y = -700;

if (MusorY == DoshikY) DoshikR = true;

  if ((millis() - Timer >= random(10000, 13000) && DoshikR == false) && MusorY > 282) {  // ищем разницу (500 мс)
    Timer = millis();              // сброс таймера
   DoshikR = true;
   switch (PApplet.parseInt(random(0, 2))) {
     case 1: Dside = 267; Ddside = true;
     break;
     case 0: Dside = 32; Ddside = false;
     break;
   }
   
  }
  
    if ((millis() - Timer2 >= random(8000, 11000) && MusorR == false) && DoshikY > 282) {  // ищем разницу (500 мс)
    Timer2 = millis();              // сброс таймера
   MusorR = true;
   switch (PApplet.parseInt(random(0, 2))) {
     case 1: Mside = 267; Mmside = true;
     break;
     case 0: Mside = 32; Mmside = false;
     break;
   }
   
  }


   
 image(Doshik, Dside, DoshikY); 
 image(Musor, Mside, MusorY); 

if (DoshikR) DoshikY+=speed;
if (MusorR) MusorY+=speed;

if ((DoshikY > 418) && Ddside == !side) {
  DoshikY = -100; 
  DoshikR = false;
  score++;
  println(score);
  PrintWriter output = createWriter ("progras.mouse");
  output.print(score + ";" + Statik_skin);
  output.flush();
  output.close();
} 
if (DoshikY > 700){
  DoshikY = -100;
DoshikR = false; 
}
//**--*-*-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*
if ((MusorY > 418) && Mmside == !side) {
  MusorY = -100; 
  DoshikY = -100;
  MusorR = false; 
  DoshikR = false; 
  menu = 0;
  noLoop();
  println("game over");
}
if (MusorY > 700){
 MusorY = -100;
MusorR = false; 
}


 if (keyPressed == true) {
    if (keyCode == LEFT) {
      side = true;
    }
    if (keyCode == RIGHT) {
      side = false;
    }

 }

}


public void OtrisivkaMenu() {
image(Menu, 0, 0); 

     if (keyPressed) {
    if (key == 'n' || key == 'N') {
      menu = 1;
    }
    if (key == 'b' || key == 'B') {
      menu = 2;
    }
  } 
if (mouseButton == LEFT) {
        if (mouseX > 110 && mouseX < 280 && mouseY > 190 && mouseY < 230){
        menu = 1;
     }  
    }
    if (mouseButton == LEFT) {
        if (mouseX > 110 && mouseX < 280 && mouseY > 300 && mouseY < 340){
        menu = 2;
     }  
    }
}

public void OtrisivkaShop() {
  image(Shop, 0, 0);
  if (mousePressed == true) {
  if (mouseButton == LEFT) {
        if ((mouseX > 80 && mouseX < 120) && (mouseY > 610 && mouseY < 650) && !ShopFlag){
        skin--;
        ShopFlag = true;
      //  println(skin);
     }  
    
        if ((mouseX > 280 && mouseX < 320) && (mouseY > 610 && mouseY < 650) && !ShopFlag){
        skin++; 
        ShopFlag = true;
       // println(skin);
     }  
     if ((mouseX > 160 && mouseX < 240) && (mouseY > 610 && mouseY < 650) && !ShopFlag){
        if ((score - Price[skin] > -1) && !bought_skin[skin]) {
          Statik_skin = skin;
          Krisa = ShopKrisa;
          score-= Price[skin];
          bought_skin[skin] = true;
          switch (skin) {
            case 2: 
            speed = 3;
            break;
            case 8:
            speed = 4;
            break;
            default :
            speed = 2;
            break; 
          }
          
          PrintWriter output = createWriter ("progras.mouse");
          output.print(score + ";" + Statik_skin);
          output.flush();
          output.close();
          PrintWriter skinsss = createWriter ("skins.mouse");
          skinsss.print(bought_skin[0] + ";" + bought_skin[1] + ";" + bought_skin[2] + ";" + bought_skin[3] + ";");
          skinsss.print(bought_skin[4] + ";" + bought_skin[5] + ";" + bought_skin[6] + ";" + bought_skin[7] + ";");
          skinsss.print(bought_skin[0]);
          skinsss.flush();
          skinsss.close();
        }
        if (bought_skin[skin]){
          Statik_skin = skin;
          Krisa = ShopKrisa;
          switch (skin) {
            case 2: 
            speed = 3;
            break;
            case 8:
            speed = 4;
            break;
            default :
            speed = 2;
            break; 
          }
        }
     }
    }}else {
    
      ShopFlag = false;
    }
    if (skin < 0) skin = 0; 
    if (skin == 9) skin = 0; 
      switch (skin) {
       case 0: ShopKrisa = loadImage("Krisa.png");
       text("КРЫСА", 70, 550);
       textSize(25);
       text("Самый обычный крыс, так-бы и бегал по канализации, если бы не эти пакеты", 32, 63, 350, 350);
       break;
       case 1: ShopKrisa = loadImage("Ananim_Kris.png");
       textSize(33);
       text("КРЫСНОНИМУС", 70, 550);
if (!bought_skin[skin]) text("25 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
 textSize(25);
       text("Родом из Ананимусных лесов. Любитель тульских пряников", 32, 63, 350, 350);
       break;
       case 2: ShopKrisa = loadImage("ВДВ_Kris.png");
       textSize(33);
       text("ДИСАНТКРЫС", 70, 550);
if (!bought_skin[skin]) text("50 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(25);
       text("Любил купаться в фонтане, и его смыло в канализацию", 32, 63, 350, 350);
       break;
       case 3: ShopKrisa = loadImage("ICE_Kris.png");
       textSize(33);
       text("АЙС КРЫС", 70, 550);
if (!bought_skin[skin]) text("60 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(25);
       text("Самая сладкая крыса. Но не в прямом значении!", 32, 63, 350, 350);
       break;
       case 4: ShopKrisa = loadImage("CHOKO_Kris.png");
       textSize(33);
       text("ЧОКОКРЫС", 70, 550);
if (!bought_skin[skin]) text("75 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(25);
       text("Нет, это не грязь, это шоколад. Но облизовать я бы его не стал...", 32, 63, 350, 350);
       break;
       case 5: ShopKrisa = loadImage("SHREK_Kris.png");
       textSize(33);
       text("КРЫС-ШРЕК", 70, 550);
if (!bought_skin[skin]) text("80 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(25);
       text("Зашол в туалет, искал книгу, и случайно нажал на смыв...", 32, 63, 350, 350);
       break;
       case 6: ShopKrisa = loadImage("GOLDEN_Kris.png");
       textSize(33);
       text("ГОЛДЕНКРЫС", 70, 550);
if (!bought_skin[skin]) text("100 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(25);
       text("Самая дорогая из всех эльфийских ценностей. Смотрит на мир в розовых очках", 32, 63, 350, 350);
       break;
       case 7: ShopKrisa = loadImage("AMONGUS_Kris.png");
       textSize(33);
       text("АМОНГКРЫС", 70, 550);
if (!bought_skin[skin]) text("110 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(25);
       text("Его выкинули с коробля, и он мутировал в плотных слоях отмасферы", 32, 63, 350, 350);
       break;
       case 8: ShopKrisa = loadImage("KIBER_Kris.png");
       textSize(33);
       text("КИБЕРКРЫС", 70, 550);
if (!bought_skin[skin]) text("120 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(25);
       text("Мощнейшее изобретение во всей конализации!", 32, 63, 350, 350);
       break;
      }
    image(ShopKrisa, 150, 370); 
     if (keyPressed) {
    if (key == 'm' || key == 'M') {
      menu = 0;
    }
  } 
}
  public void settings() {  size(400, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
