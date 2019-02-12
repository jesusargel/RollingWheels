import fisica.*;


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


void setup()
{
  size(1200, 700);
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

void draw()
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

void keyPressed()
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
