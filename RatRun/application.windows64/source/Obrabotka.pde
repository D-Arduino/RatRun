void OtrisivkaRun() {

  
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
   switch (int(random(0, 2))) {
     case 1: Dside = 267; Ddside = true;
     break;
     case 0: Dside = 32; Ddside = false;
     break;
   }
   
  }
  
    if ((millis() - Timer2 >= random(8000, 11000) && MusorR == false) && DoshikY > 282) {  // ищем разницу (500 мс)
    Timer2 = millis();              // сброс таймера
   MusorR = true;
   switch (int(random(0, 2))) {
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


void OtrisivkaMenu() {
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

void OtrisivkaShop() {
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
       case 0: ShopKrisa = loadImage("АМУСИС.png");
       text("АМУСИС", 70, 550);
       textSize(27);
       text("Самый обычный обьект. Так бы и летал здесь если б знал зачем...", 32, 63, 350, 350);
       break;
       case 1: ShopKrisa = loadImage("ГРАНДУС.png");
       textSize(33);
       text("ГРАНДУС", 70, 550);
if (!bought_skin[skin]) text("25 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
 textSize(27);
       text("Родом из Ананимусных лесов. Любитель тульских пряников", 32, 63, 350, 350);
       break;
       case 2: ShopKrisa = loadImage("ВДВ_Kris.png");
       textSize(33);
       text("ДИСАНТКРЫС", 70, 550);
if (!bought_skin[skin]) text("50 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(27);
       text("Любил купаться в фонтане, и его смыло в канализацию", 32, 63, 350, 350);
       break;
       case 3: ShopKrisa = loadImage("ICE_Kris.png");
       textSize(33);
       text("АЙС КРЫС", 70, 550);
if (!bought_skin[skin]) text("60 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(27);
       text("Самая сладкая крыса. Но не в прямом значении!", 32, 63, 350, 350);
       break;
       case 4: ShopKrisa = loadImage("CHOKO_Kris.png");
       textSize(33);
       text("ЧОКОКРЫС", 70, 550);
if (!bought_skin[skin]) text("75 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(27);
       text("Нет, это не грязь, это шоколад. Но облизовать я бы его не стал...", 32, 63, 350, 350);
       break;
       case 5: ShopKrisa = loadImage("SHREK_Kris.png");
       textSize(33);
       text("КРЫС-ШРЕК", 70, 550);
if (!bought_skin[skin]) text("80 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(27);
       text("Зашол в туалет, искал книгу, и случайно нажал на смыв...", 32, 63, 350, 350);
       break;
       case 6: ShopKrisa = loadImage("GOLDEN_Kris.png");
       textSize(33);
       text("ГОЛДЕНКРЫС", 70, 550);
if (!bought_skin[skin]) text("100 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(27);
       text("Самая дорогая из всех эльфийских ценностей. Смотрит на мир в розовых очках", 32, 63, 350, 350);
       break;
       case 7: ShopKrisa = loadImage("AMONGUS_Kris.png");
       textSize(33);
       text("АМОНГКРЫС", 70, 550);
if (!bought_skin[skin]) text("110 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(27);
       text("Его выкинули с коробля, и он мутировал в плотных слоях отмасферы", 32, 63, 350, 350);
       break;
       case 8: ShopKrisa = loadImage("KIBER_Kris.png");
       textSize(33);
       text("КИБЕРКРЫС", 70, 550);
if (!bought_skin[skin]) text("120 Дошиков", 70, 590);
 if (bought_skin[skin] && Statik_skin != skin) text("Куплено", 70, 590);
if (Statik_skin == skin) text("Выбрано!", 70, 590);
textSize(27);
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
