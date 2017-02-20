import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import fisica.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class GP2 extends PApplet {




Lvl1 lvl1;
Lvl2 lvl2;
Lvl3 lvl3;
FWorld introd;
int lvl = 1;
boolean intro = true;
boolean pause = false;
PImage bg;
PImage flag;
int mx;
int my;
float j = 1;
int opt = 0;
int [] hs = new int[8];
int [] sc = new int[3];
int valuet = 60;
int timer = valuet;
int time;
int wait = 1000;
int score = 0;
int cumulative = 0;
boolean lost = false;
boolean won = false;


public void setup()
{
  
  Fisica.init(this);
  lvl1 = new Lvl1();
  lvl2 = new Lvl2();
  lvl3 = new Lvl3();
  introd = new FWorld();
  rectMode(CENTER);
  textAlign(CENTER);
  bg = loadImage("wave.jpg");
  flag = loadImage("flag.png");
  time = millis();
  strokeWeight(3);
  for (int i = 0; i < hs.length; i++)
  {
    hs[i] = 0;
  }
}

public void draw()
{
  if (opt == 0)
  {
  introd.step();
  introd.draw();
  for (int i = 0; i < hs.length; i++)
  {
    if (cumulative > hs[i])
    {
      int j = 7 - i;
      while (j > i)
      {
        hs[j] = hs[j-1];
        j--;
      }
      hs[i] = cumulative;
      cumulative = 0;
    }
  }
  lvl = 1;
  score = 0;
  lost = false;
  won = false;
  lvl1 = new Lvl1();
  lvl2 = new Lvl2();
  lvl3 = new Lvl3();
  background(bg);
  fill(255, 255, 0);
  textSize(60);
  text("Rolling wheels", width/2, 200);
  textSize(30);
  text("J&J Games", width/2, 300);
  textSize(26);
  fill(106, 72, 3, 200);
  rect(width/2, 400, 200, 80);
  rect(width/2, 500, 200, 80);
  rect(width/2, 600, 200, 80);
  fill(255);
  text("PLAY", width/2, 400);
  text("HIGH SCORES", width/2, 500);
  text("INSTRUCTIONS", width/2, 600);
  mx = mouseX;
  my = mouseY;
  fill(0, 125);
  ellipse(mx, my, 20, 20);
  if (mousePressed)
  {
    if (mx >= width/2 - 100 && mx <= width/2 + 100 && my >= 360 && my <= 440)
    {
      opt = 1;
    }
    if (mx >= width/2 - 100 && mx <= width/2 + 100 && my >= 460 && my <= 540)
    {
      opt = 2;
    }
    if (mx >= width/2 - 100 && mx <= width/2 + 100 && my >= 560 && my <= 640)
    {
      opt = 3;
    }
  }
  }

  if (opt == 1)
  {
  if (lost == false)
  {
    if (millis() - time >= wait)
    {
      timer -= 1;
      time = millis();
      println(timer);
    }
    if (timer == 0)
    {
      lost = true;
      timer = valuet;
    }
    if (lvl == 1)
    {
      lvl1.drawLvl1();
      image(flag, 680, 40);
      if (lvl1.body.getX()>= 680 && lvl1.body.getX() <= 740 && lvl1.body.getY() >= 30 && lvl1.body.getY() <= 120)
      {
        score += timer*50;
        timer = valuet;
        sc[lvl-1] = score;
        cumulative += score;
        score = 0;
        lvl = 2;
      }
    }
    if (lvl == 2)
    {
      lvl2.drawLvl2();
      image(flag, 2310, 500);
      if (lvl2.body.getX()>= 2280 && lvl2.body.getX() <=2400 && lvl2.body.getY() >= 550 && lvl2.body.getY() <= 700)
      {
        score += timer*50; 
        timer = valuet;
        sc[lvl-1] = score;
        cumulative += score;
        score = 0;
        lvl = 3;
      }
    }
    if (lvl == 3)
    {
      lvl3.drawLvl3();
      image(flag, 1610, 65);
      if (lvl3.body.getX()>= 1600 && lvl3.body.getX() <=1660 && lvl3.body.getY() >= 50 && lvl3.body.getY() <= 150)
      {
        score += timer*50;
        timer = valuet;
        sc[lvl-1] = score;
        cumulative += score;
        score = 0;
        won = true;
        lost = true;
      }
    }
    fill(106, 72, 3, 200);
    rect(100, 100, 100, 35);
    fill(255);
    textSize(20);
    text("BACK", 100, 105);
    if (mousePressed)
    {
      if (mouseX >= 50 && mouseX <= 150 && mouseY >= 83 && mouseY <= 117)
      {
        opt = 0;
      }
    }
  } else if (lost)
  {

    background(bg);
    mx = mouseX;
    my = mouseY;

    fill(255, 255, 0);
    textSize(50);
    if (won)
    {
      text("Great Job", width/2, 100);
    } else
    {
      text("Time is over", width/2, 100);
    }
    for (int i = 0; i < sc.length; i ++)
    {
      text(sc[i], width/2 + 200, i*100+300);
    }
    text("+", width/2 + 50, 500);
    stroke(255, 255, 0);
    strokeWeight(3);
    line(width/2 + 40, 530, width/2 + 230, 530);
    stroke(0);
    text("Your score was: " + cumulative, width/2, 600);
    fill(106, 72, 3, 200);
    rect(width/2, 650, 200, 70);
    fill(255);
    textSize(30);
    text("BACK", width/2, 660);
    fill(0, 125);
    ellipse(mx, my, 20, 20);
    if (mousePressed)
    {
      if (mx >= width/2 - 100 && mx <= width/2 + 100 && my >= 615 && my <= 685)
      {
        opt = 0;
      }
    }
  }
  }

  if (opt == 2)
  {
  background(bg);
  mx = mouseX;
  my = mouseY;

  strokeWeight(3);
  fill(106, 72, 3, 200);
  rect(width/2, height/2 + 50, 600, 400);


  fill(255, 255, 0);
  textSize(50);
  text("High Scores", width/2, 100);

  for (int i = 250; i < 600; i+=50)
  { 
    line(300, i, 900, i);
  }
  for (int i = 1; i < 9; i+=1)
  {
    textSize(30);
    fill(255);
    text(i + ".", 330, i* 50 +190);
  }
  for (int i = 0; i < hs.length; i++)
  {
    textSize(30);
    fill(255);
    text(hs[i], 630, (i+1)* 50 +190);
  }

  line(350, 200, 350, 600);


  fill(106, 72, 3, 200);
  rect(width/2, 650, 200, 70);
  fill(255);
  textSize(30);
  text("BACK", width/2, 660);
  fill(0, 125);
  ellipse(mx, my, 20, 20);
  if (mousePressed)
  {
    if (mx >= width/2 - 100 && mx <= width/2 + 100 && my >= 615 && my <= 685)
    {
      opt = 0;
    }
  }
  }

  if (opt == 3)
  {
  background(bg);
  mx = mouseX;
  my = mouseY;

  fill(255, 255, 0);
  textSize(50);
  text("Instructions", width/2, 100);

  strokeWeight(3);
  fill(106, 72, 3, 200);
  rect(width/2, height/2 + 50, 600, 400);

  for (int i = 3; i < 6; i++)
  { 
    line(300, i*100, 900, i*100);
  }

  line(400, 200, 400, 600);

  line(600, 200, 600, 600);

  line(700, 200, 700, 500);


  fill(255);

  textSize(50);

  text("A", 350, 270);
  text("D", 350, 370);
  text("J", 350, 470);
  text("K", 350, 570);
  text("Spc", 650, 270);
  text("P", 650, 370);
  text("R", 650, 470);
  textSize(20);
  text("Move right", 500, 260);
  text("Move left", 500, 360);
  text("Tilt left", 500, 460);
  text("Tilt right", 500, 560);
  text("Jump", 800, 260);
  text("Pause", 800, 360);
  text("Reset", 800, 460);
  fill(106, 72, 3, 200);
  rect(width/2, 650, 200, 70);
  fill(255);
  textSize(30);
  text("BACK", width/2, 660);
  fill(0, 125);
  ellipse(mx, my, 20, 20);
  if (mousePressed)
  {
    if (mx >= width/2 - 100 && mx <= width/2 + 100 && my >= 615 && my <= 685)
    {
      opt = 0;
    }
  }
  }
}

public void keyPressed()
{
  if (key == 'p')
  {
    pause = !pause;
  } else
  {
    pause = false;
  }
  if (pause)
  {
    fill(0, 100, 255, 200);
    rect(width/2, height/2, 3600, 700);
    noLoop();
  } else if (!pause)
  {
    loop();
    pause = false;
  }


  if (key == 'r')
  {
    timer = valuet;
    if (lvl == 1)
    {
      lvl1 = new Lvl1();
      lvl1.drawLvl1();
    }
    if (lvl == 2)
    {
      lvl2 = new Lvl2();
      lvl2.drawLvl2();
    }
    if (lvl == 3)
    {
      lvl3 = new Lvl3();
      lvl3.drawLvl3();
    }
  }
}
class Lvl1
{

  float x;
  float dx;
  float y;
  float tx;
  FWorld world;
  FCircle wheel1;
  FCircle wheel2;
  FBox body;
  FBox floor;
  FDistanceJoint dj1;
  FDistanceJoint dj2;
  FDistanceJoint dj3;
  FDistanceJoint dj4;
  FDistanceJoint dj5;
  boolean jumping = false;
  PImage car;
  PImage wheels;
  PImage car1;
  PImage bg;

  //jump variables for functions
  FPoly plat1;
  FPoly plat2;
  FPoly plat3;
  FPoly plat4;
  FPoly plat5;
  FPoly plat6; 
  FPoly plat7;
  FPoly plat8;
  FPoly plat9;
  FPoly plat10;
  FPoly plat11;
  FPoly plat12;

  //end of jump variables

  Lvl1()
  {
    x = width/2;
    y = 505;
    tx = 0;
    dx = 0;
    car = loadImage("cart.png");
    car1 = loadImage("cart1.png");
    wheels = loadImage("wheel.png");
    bg = loadImage("walmart.jpg");
    world = new FWorld(0, 0, 2400, 700);
    wheel1 = new FCircle(25);
    wheel2 = new FCircle(25);
    body = new FBox(50, 25);
    floor = new FBox(width*2, 10);
    floor.setFill(106, 72, 3);
    floor.setPosition(width, height-5);
    wheel1.setPosition(width/2 -45, 525);
    wheel2.setPosition(width/2 +45, 525);
    body.setPosition(x, y);
    wheel1.setFriction(20);
    wheel2.setFriction(20);
    floor.setStatic(true);
    body.setFill(255, 0, 0);
    wheel1.setFill(0);
    wheel2.setFill(0);
    body.setGrabbable(false);
    body.attachImage(car);
    wheel1.setGrabbable(false);
    wheel2.setGrabbable(false);
    wheel1.attachImage(wheels);
    wheel2.attachImage(wheels);
    dj1 = new FDistanceJoint(wheel1, body);
    dj1.calculateLength();
    dj2 = new FDistanceJoint(wheel2, body);
    dj2.calculateLength();
    dj3 = new FDistanceJoint(wheel1, wheel2);
    dj3.calculateLength();
    dj4 = new FDistanceJoint(wheel1, body);
    dj4.setAnchor2(-25, -12.5f);
    dj5 = new FDistanceJoint(wheel2, body);
    dj5.setAnchor2(25, -12.5f);

    //platforms/jumps

    plat1 = new FPoly();
    plat1.vertex(900, 690);
    plat1.vertex(1050, 690);
    plat1.vertex(1040, 620);
    plat1.setStatic(true);
    plat1.setFill(160, 139, 100);
    plat1.setGrabbable(false);
    world.add(plat1);

    plat2= new FPoly();
    plat2.vertex(1450, 690);
    plat2.vertex(1450, 620);
    plat2.vertex(1600, 690);
    plat2.setStatic(true);
    plat2.setFill(160, 139, 100);
    plat2.setGrabbable(false);
    world.add(plat2);

    plat3=  new FPoly();
    plat3.vertex(2200, 690);
    plat3.vertex(2350, 690);
    plat3.vertex(2280, 580);
    plat3.setGrabbable(false);
    plat3.setFill(160, 139, 100);



    plat3.setStatic(true);
    world.add(plat3);

    plat4=new FPoly();
    plat4.vertex(2280, 580);
    plat4.vertex(2280, 500);
    plat4.vertex(2310, 560);
    plat4.setGrabbable(false);
    plat4.setFill(160, 139, 100);


    plat4.setStatic(true);
    world.add(plat4);


    plat5=new FPoly();
    plat5.vertex(2280, 500);
    plat5.vertex(2250, 450);
    plat5.vertex(2380, 430);
    plat5.setStatic(true);
    plat5.setFill(160, 139, 100);
    plat5.setGrabbable(false);
    world.add(plat5);

    plat6=new FPoly();
    plat6.vertex(2000, 350);
    plat6.vertex(2000, 360);
    plat6.vertex(1200, 360);
    plat6.vertex(1200, 350);
    plat6.setGrabbable(false);
    plat6.setFill(160, 139, 100); 

    plat6.setStatic(true);
    world.add(plat6);


    plat7=new FPoly();
    plat7.vertex(1200, 350);
    plat7.vertex(1150, 310);
    plat7.vertex(1150, 350);
    plat7.setStatic(true);
    plat7.setFill(160, 139, 100);
    plat7.setGrabbable(false);
    world.add(plat7);


    plat8=new FPoly();
    plat8.vertex(800, 500);
    plat8.vertex(800, 510);
    plat8.vertex(280, 510);
    plat8.vertex(280, 500);
    plat8.setStatic(true);
    plat8.setFill(160, 139, 100);
    plat8.setGrabbable(false);
    world.add(plat8);

    plat9=new FPoly();
    plat9.vertex(280, 500);
    plat9.vertex(100, 410);
    plat9.vertex(100, 500);
    plat9.setStatic(true);
    plat9.setFill(160, 139, 100);
    plat9.setGrabbable(false);
    world.add(plat9);

    plat10=new FPoly();
    plat10.vertex(100, 410);
    plat10.vertex(100, 360);
    plat10.vertex(30, 380);
    plat10.setStatic(true);
    plat10.setFill(160, 139, 100);
    plat10.setGrabbable(false);
    world.add(plat10);

    plat11= new FPoly();
    plat11.vertex(220, 200);
    plat11.vertex(220, 210);
    plat11.vertex(500, 210);
    plat11.vertex(500, 200);
    plat11.setStatic(true);
    plat11.setFill(160, 139, 100);
    plat11.setGrabbable(false);
    world.add(plat11);

    plat12=new FPoly();
    plat12.vertex(500, 200);
    plat12.vertex(510, 190);
    plat12.vertex(510, 200);
    plat12.setStatic(true);
    plat12.setFill(160, 139, 100);
    plat12.setGrabbable(false);
    world.add(plat12);
    //end of platforms


    world.setEdges(0, 0, 2400, 700);
    world.add(floor);
    world.add(wheel1);
    world.add(wheel2);
    world.add(body);
    world.add(dj1);
    world.add(dj2);
    world.add(dj3);
    world.add(dj4);
    world.add(dj5);
  }

  public void drawLvl1()
  {
    translate(-tx, 0);
    background(bg);
    world.step();
    world.draw();

    if (keyPressed)
    {
      if (key == 'a')
      {
        wheel1.addTorque(-50);
        wheel2.addTorque(-50);
        body.attachImage(car1);
        tx -= 5;
      }
      if (key == 'd')
      {
        wheel1.addTorque(50);
        wheel2.addTorque(50);
        body.attachImage(car);
        tx += 5;
      }
      if (key == ' ' && jumping == false)
      {
        body.addImpulse(0, -500);
        jumping = true;
      }
      if (wheel1.getVelocityY() <= 0.5f && wheel1.getVelocityY() >= -0.5f)
      {
        jumping = false;
      }
      if (key == 'j')
      {
        body.addTorque(-100);
        wheel1.addForce(0, 300);
        wheel2.addForce(0, -300);
      }
      if (key == 'k')
      {
        body.addTorque(100);
        wheel1.addForce(0, -300);
        wheel2.addForce(0, 300);
      }
    }
    tx = constrain(PApplet.parseInt(body.getX()) - 600, 0, 2400 - width);
  }
}
class Lvl2
{

  float x;
  float dx;
  float y;
  float tx;
  FWorld world;
  FCircle wheel1;
  FCircle wheel2;
  FBox body;
  FBox floor;
  FDistanceJoint dj1;
  FDistanceJoint dj2;
  FDistanceJoint dj3;
  FDistanceJoint dj4;
  FDistanceJoint dj5;
  boolean jumping = false;
  PImage car;
  PImage car1;
  PImage wheels;
  PImage bg;

  FPoly p1;
  FPoly p2;
  FPoly p3;
  FPoly p4;
  FPoly p5;
  FPoly p6; 
  FPoly p7;
  FPoly p8;
  FPoly p9;
  FPoly p10;
  FPoly p11; 

  FCircle c1;





  Lvl2()
  {
    x = width/2;
    y = 505;
    tx = 0;
    dx = 0;
    car = loadImage("banana.png");
    car1 = loadImage("banana1.png");
    wheels = loadImage("wheel.png");
    bg = loadImage("dk.jpg");
    world = new FWorld(0, 0, 2400, 700);
    wheel1 = new FCircle(25);
    wheel2 = new FCircle(25);
    body = new FBox(50, 25);
    floor = new FBox(width*2, 10);
    floor.setFill(106, 72, 3);
    floor.setPosition(width, height-5);
    wheel1.setPosition(width/2 -45, 525);
    wheel2.setPosition(width/2 +45, 525);
    body.setPosition(x, y);
    wheel1.setFriction(20);
    wheel2.setFriction(20);
    floor.setStatic(true);
    body.setFill(255, 0, 0);
    wheel1.setFill(0);
    wheel2.setFill(0);
    body.setGrabbable(false);
    body.attachImage(car1);
    wheel1.setGrabbable(false);
    wheel2.setGrabbable(false);
    wheel1.attachImage(wheels);
    wheel2.attachImage(wheels);
    dj1 = new FDistanceJoint(wheel1, body);
    dj1.calculateLength();
    dj2 = new FDistanceJoint(wheel2, body);
    dj2.calculateLength();
    dj3 = new FDistanceJoint(wheel1, wheel2);
    dj3.calculateLength();
    dj4 = new FDistanceJoint(wheel1, body);
    dj4.setAnchor2(-25, -12.5f);
    dj5 = new FDistanceJoint(wheel2, body);
    dj5.setAnchor2(25, -12.5f);



    p1=new FPoly();
    p1.vertex(180, 690);
    p1.vertex(100, 610);
    p1.vertex(100, 690);
    p1.setStatic(true);
    p1.setFill(229, 114, 19);
    p1.setGrabbable(false);
    world.add(p1);

    p2=new FPoly();
    p2.vertex(100, 610);
    p2.vertex(100, 560);
    p2.vertex(30, 580);
    p2.setStatic(true);
    p2.setFill(229, 114, 19);
    p1.setGrabbable(false);
    world.add(p2);


    p3= new FPoly();
    p3.vertex(100, 560);
    p3.vertex(110, 530);
    p3.vertex(90, 550);
    p3.setStatic(true);
    p3.setFill(229, 114, 19);
    p1.setGrabbable(false);
    world.add(p3);


    p4=new FPoly();
    p4.vertex(300, 400);
    p4.vertex(300, 410);
    p4.vertex(700, 410);
    p4.vertex(700, 400);
    p4.setStatic(true);
    p4.setFriction(1000);
    p4.setDensity(2);
    p4.setFill(229, 114, 19);
    p4.setGrabbable(false);
    world.add(p4);


    c1= new FCircle(50);
    c1.setPosition(1000, 350);
    c1.setStatic(true);
    c1.setFill(229, 114, 19);
    c1.setGrabbable(false);
    world.add(c1);

    p5=new FPoly();
    p5.vertex(660, 310);
    p5.vertex(660, 320);
    p5.vertex(1300, 320);
    p5.vertex(1300, 310);
    p5.setFill(229, 114, 19);
    p5.setGrabbable(false);
    world.add(p5);

    p6=new FPoly();

    p6.vertex(150, 200);
    p6.vertex(150, 210);
    p6.vertex(650, 210);
    p6.vertex(650, 200);
    p6.setStatic(true);
    p6.setFill(229, 114, 19);
    p6.setGrabbable(false);
    world.add(p6);


    p7=new FPoly();
    p7.vertex(650, 200);
    p7.vertex(690, 180);
    p7.vertex(690, 200);
    p7.setStatic(true);
    p7.setFill(229, 114, 19);
    p7.setGrabbable(false);
    world.add(p7);


    p8=new FPoly();

    p8.vertex(1200, 190);
    p8.vertex(1200, 200);
    p8.vertex(1700, 200);
    p8.vertex(1700, 190);
    p8.setStatic(true);
    p8.setFill(229, 114, 19);
    p8.setGrabbable(false);
    world.add(p8);

    p9=new FPoly();
    p9.vertex(1700, 190);
    p9.vertex(1730, 180);
    p9.vertex(1730, 190);
    p9.setStatic(true);
    p9.setFill(229, 114, 19);
    p9.setGrabbable(false);
    world.add(p9);


    p10=new FPoly();

    p10.vertex(2080, 210);
    p10.vertex(2400, 0);
    p10.vertex(2400, 400);
    p10.setStatic(true);
    p10.setFill(229, 114, 19);
    p10.setGrabbable(false);
    world.add(p10);

    p11=new FPoly();
    p11.vertex(2000, 380);
    p11.vertex(2400, 650);
    p11.vertex(2100, 650);
    p11.setStatic(true);
    p11.setFill(229, 114, 19);
    p11.setGrabbable(false);
    world.add(p11);








    world.setEdges(0, 0, 2400, 700);
    world.add(floor);
    world.add(wheel1);
    world.add(wheel2);
    world.add(body);
    world.add(dj1);
    world.add(dj2);
    world.add(dj3);
    world.add(dj4);
    world.add(dj5);
  }

  public void drawLvl2()
  {
    translate(-tx, 0);
    background(bg);
    world.step();
    world.draw();
    if (keyPressed)
    {
      if (key == 'a')
      {
        wheel1.addTorque(-50);
        wheel2.addTorque(-50);
        body.attachImage(car);
        tx -= 5;
      }
      if (key == 'd')
      {
        wheel1.addTorque(50);
        wheel2.addTorque(50);
        body.attachImage(car1);
        tx += 5;
      }
      if (key == ' ' && jumping == false)
      {
        body.addImpulse(0, -700);
        jumping = true;
      }
      if (wheel1.getVelocityY() <= 0.5f && wheel1.getVelocityY() >= -0.5f)
      {
        jumping = false;
      }
      if (key == 'j')
      {
        body.addTorque(-100);
        wheel1.addForce(0, 300);
        wheel2.addForce(0, -300);
      }
      if (key == 'k')
      {
        body.addTorque(100);
        wheel1.addForce(0, -300);
        wheel2.addForce(0, 300);
      }
    }
    tx = constrain(PApplet.parseInt(body.getX()) - 600, 0, 2400 - width);
  }
}
class Lvl3
{

  float x;
  float dx;
  float y;
  float tx;
  FWorld world;
  FCircle wheel1;
  FCircle wheel2;
  FBox body;
  FBox floor;
  FDistanceJoint dj1;
  FDistanceJoint dj2;
  FDistanceJoint dj3;
  FDistanceJoint dj4;
  FDistanceJoint dj5;
  boolean jumping = false;
  PImage car;
  PImage car1;
  PImage wheels;
  PImage bg;
  FBox plat1;
  FBox plat2;
  FBox plat3;
  FBox plat4;
  FBox plat5;
  FBox plat6;
  FBox plat7;
  FBox plat8;
  FBox plat9;
  FPoly ramp1;
  FPoly ramp2;
  FPoly ramp3;
  FPoly ramp4;
  FDistanceJoint dj6;
  FDistanceJoint dj7;
  

  Lvl3()
  {
    x = width/2;
    y = 505;
    tx = 0;
    dx = 0;
    car = loadImage("burger.png");
    car1 = loadImage("burger1.png");
    wheels = loadImage("wheel.png");
    bg = loadImage("bb.jpeg");
    world = new FWorld(0, 0, 2400, 700);
    wheel1 = new FCircle(25);
    wheel2 = new FCircle(25);
    body = new FBox(50, 25);
    floor = new FBox(width*2, 10);
    floor.setFill(106, 72, 3);
    floor.setPosition(width, height-5);
    wheel1.setPosition(width/2 -45, 525);
    wheel2.setPosition(width/2 +45, 525);
    body.setPosition(x, y);
    wheel1.setFriction(20);
    wheel2.setFriction(20);
    floor.setStatic(true);
    body.setFill(255, 0, 0);
    wheel1.setFill(0);
    wheel2.setFill(0);
    body.setGrabbable(false);
    body.attachImage(car);
    wheel1.setGrabbable(false);
    wheel2.setGrabbable(false);
    wheel1.attachImage(wheels);
    wheel2.attachImage(wheels);
    dj1 = new FDistanceJoint(wheel1, body);
    dj1.calculateLength();
    dj2 = new FDistanceJoint(wheel2, body);
    dj2.calculateLength();
    dj3 = new FDistanceJoint(wheel1, wheel2);
    dj3.calculateLength();
    dj4 = new FDistanceJoint(wheel1, body);
    dj4.setAnchor2(-25, -12.5f);
    dj5 = new FDistanceJoint(wheel2, body);
    dj5.setAnchor2(25, -12.5f);

    world.setEdges(0, 0, 2400, 700);
    world.add(floor);
    world.add(wheel1);
    world.add(wheel2);
    world.add(body);
    world.add(dj1);
    world.add(dj2);
    world.add(dj3);
    world.add(dj4);
    world.add(dj5);
    
    plat1 = new FBox(990,10);
    plat1.setGrabbable(false);
    plat1.setStatic(true);
    plat1.setPosition(1900,550);
    plat1.setFill(229,215,162);
    world.add(plat1);
    
    ramp1 = new FPoly();
    ramp1.vertex(800,690);
    ramp1.vertex(1000,690);
    ramp1.vertex(1000,590);
    ramp1.setGrabbable(false);
    ramp1.setStatic(true);
    ramp1.setFill(229,215,162);
    world.add(ramp1);
    
    ramp2 = new FPoly();
    ramp2.vertex(2300,545);
    ramp2.vertex(2490,545);
    ramp2.vertex(2490,310);
    ramp2.vertex(2300,310);
    ramp2.vertex(2360,350);
    ramp2.vertex(2380,400);
    ramp2.vertex(2370,500);
    ramp2.setGrabbable(false);
    ramp2.setStatic(true);
    ramp2.setFill(229,215,162);
    world.add(ramp2);
    
    plat2 = new FBox(400,10);
    plat2.setGrabbable(false);
    plat2.setStatic(true);
    plat2.setPosition(1750,370);
    plat2.setFill(229,215,162);
    world.add(plat2);
    
    plat3 = new FBox(300,10);
    plat3.setGrabbable(false);
    plat3.setStatic(true);
    plat3.setPosition(300,330);
    plat3.setFill(229,215,162);
    world.add(plat3);
    
    plat4 = new FBox(200,10);
    plat4.setGrabbable(false);
    plat4.setStatic(true);
    plat4.setPosition(860,150);
    plat4.setFill(229,215,162);
    world.add(plat4);
    
    plat5 = new FBox(200,10);
    plat5.setGrabbable(false);
    plat5.setStatic(true);
    plat5.setPosition(1350,150);
    plat5.setFill(229,215,162);
    world.add(plat5);
    
    plat6 = new FBox(150,10);
    plat6.setGrabbable(false);
    plat6.setStatic(true);
    plat6.setPosition(1630,150);
    plat6.setFill(229,215,162);
    world.add(plat6);
    
    plat7 = new FBox(380,10);
    plat7.setGrabbable(false);
    plat7.setPosition(1400,340);
    plat7.setFill(229,215,162);
    world.add(plat7);
    
    dj6 = new FDistanceJoint(plat7,plat5);
    dj6.setAnchor1(-30,0);
    dj6.setAnchor2(20,0);
    world.add(dj6);
    
    
    plat8 = new FBox(400,10);
    plat8.setGrabbable(false);
    plat8.setPosition(860,380);
    plat8.setFill(229,215,162);
    world.add(plat8);
    
    dj7 = new FDistanceJoint(plat8,plat4);
    dj7.calculateLength();
    world.add(dj7);
    
    ramp3 = new FPoly();
    ramp3.vertex(150,325);
    ramp3.vertex(60,325);
    ramp3.vertex(60,125);
    ramp3.vertex(150,125);
    ramp3.vertex(110,160);
    ramp3.vertex(90,225);
    ramp3.vertex(110,280);
    ramp3.setStatic(true);
    ramp3.setGrabbable(false);
    ramp3.setFill(229,215,162);
    world.add(ramp3);
    
    plat9 = new FBox(200,10);
    plat9.setGrabbable(false);
    plat9.setStatic(true);
    plat9.setPosition(560,150);
    plat9.setFill(229,215,162);
    world.add(plat9);
    
    ramp4 = new FPoly();
    ramp4.vertex(960,145);
    ramp4.vertex(980,145);
    ramp4.vertex(980,135);
    ramp4.setGrabbable(false);
    ramp4.setStatic(true);
    ramp4.setFill(229,215,162);
    world.add(ramp4);
    
    
    
    
  }

  public void drawLvl3()
  {
    translate(-tx, 0);
    background(bg);
    world.step();
    world.draw();
    if (keyPressed)
    {
      if (key == 'a')
      {
        wheel1.addTorque(-50);
        wheel2.addTorque(-50);
        body.attachImage(car1);
        tx -= 5;
      }
      if (key == 'd')
      {
        wheel1.addTorque(50);
        wheel2.addTorque(50);
        body.attachImage(car);
        tx += 5;
      }
      if (key == ' ' && jumping == false)
      {
        body.addImpulse(0, -700);
        jumping = true;
      }
      if (wheel1.getVelocityY() <= 0.5f && wheel1.getVelocityY() >= -0.5f)
      {
        jumping = false;
      }
      if (key == 'j')
      {
        body.addTorque(-100);
        wheel1.addForce(0, 300);
        wheel2.addForce(0, -300);
      }
      if (key == 'k')
      {
        body.addTorque(100);
        wheel1.addForce(0, -300);
        wheel2.addForce(0, 300);
      }
      if (key == 'p')
      {
        pause = true;
      }
    }
    tx = constrain(PApplet.parseInt(body.getX()) - 600, 0, 2400 - width);
  }
}
  public void settings() {  size(1200, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "GP2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
