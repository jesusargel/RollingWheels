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
    dj4.setAnchor2(-25, -12.5);
    dj5 = new FDistanceJoint(wheel2, body);
    dj5.setAnchor2(25, -12.5);



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

  void drawLvl2()
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
      if (wheel1.getVelocityY() <= 0.5 && wheel1.getVelocityY() >= -0.5)
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
    tx = constrain(int(body.getX()) - 600, 0, 2400 - width);
  }
}
