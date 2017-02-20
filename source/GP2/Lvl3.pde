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
    dj4.setAnchor2(-25, -12.5);
    dj5 = new FDistanceJoint(wheel2, body);
    dj5.setAnchor2(25, -12.5);

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

  void drawLvl3()
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
      if (key == 'p')
      {
        pause = true;
      }
    }
    tx = constrain(int(body.getX()) - 600, 0, 2400 - width);
  }
}