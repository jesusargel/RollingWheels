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
    dj4.setAnchor2(-25, -12.5);
    dj5 = new FDistanceJoint(wheel2, body);
    dj5.setAnchor2(25, -12.5);

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

  void drawLvl1()
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
