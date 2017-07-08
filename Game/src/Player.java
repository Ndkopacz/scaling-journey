import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Player extends JPanel
{
	private Animation walkLeft = null;
	private Animation walkRight = null;
	private Animation walkUp = null;
	private Animation walkDown = null;
	private Animation standLeft = null;
	private Animation standRight = null;
	private Animation standUp = null;
	private Animation standDown = null;
	
	private Animation slashLeft = null;
	private Animation slashRight = null;
	private Animation slashUp = null;
	private Animation slashDown = null;
	
	private Animation thrustLeft = null;
	private Animation thrustRight = null;
	private Animation thrustUp = null;
	private Animation thrustDown = null;
	
	private Animation castLeft = null;
	private Animation castRight = null;
	private Animation castUp = null;
	private Animation castDown = null;
	
	private String[][] mapLayout = null;
	private int tileSize = 32;
	
	
	private int direction = 2;
	
	boolean ready = false;
	// This is the actual animation
	private Animation animation;
	
	private final double FPS = 60;
	private long targetTime = (long) (1000/FPS);
	
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private KeyListener kl = null;
	private int walkSpeed = 2;
	
	private int xLocation = 0;
	private int yLocation = 0;
	
	private Spell spell = null;
	
	public void setupAnimations(){
		// Images for each animation
		BufferedImage[][] walkingLeft = new BufferedImage[8][9];
		BufferedImage[][] walkingRight = new BufferedImage[8][9];
		BufferedImage[][] walkingUp = new BufferedImage[8][9];
		BufferedImage[][] walkingDown = new BufferedImage[8][9];

		for(int i=1;i<=8;i++){
			walkingLeft[i-1] = SpriteWalk.getSprite(i, 1);
		}
		for(int i=1;i<=8;i++){
			walkingRight[i-1] = SpriteWalk.getSprite(i, 3);
		}
		for(int i=1;i<=8;i++){
			walkingUp[i-1] = SpriteWalk.getSprite(i, 0);
		}
		for(int i=1;i<=8;i++){
			walkingDown[i-1] = SpriteWalk.getSprite(i, 2);
		}
		
		
		
		BufferedImage[][] slashingLeft = new BufferedImage[7][9];
		for(int i=0;i<=5;i++){
			slashingLeft[i] = SpriteSlash.getSprite(i, 1);
		}
		slashingLeft[6] = SpriteWalk.getSprite(0, 1);
		
		BufferedImage[][] slashingRight = new BufferedImage[7][9];
		for(int i=0;i<=5;i++){
			slashingRight[i] = SpriteSlash.getSprite(i, 3);
		}
		slashingRight[6] = SpriteWalk.getSprite(0, 3);

		BufferedImage[][] slashingUp = new BufferedImage[7][9];
		for(int i=0;i<=5;i++){
			slashingUp[i] = SpriteSlash.getSprite(i, 0);
		}
		slashingUp[6] = SpriteWalk.getSprite(0, 0);

		BufferedImage[][] slashingDown = new BufferedImage[7][9];
		for(int i=0;i<=5;i++){
			slashingDown[i] = SpriteSlash.getSprite(i, 2);
		}
		slashingDown[6] = SpriteWalk.getSprite(0, 2);	
		
/////	
		BufferedImage[][] thrustingLeft = new BufferedImage[7][9];
		for(int i=0;i<=5;i++){
			thrustingLeft[i] = SpriteThrust.getSprite(i, 1);
		}
		thrustingLeft[6] = SpriteWalk.getSprite(0, 1);
		
		BufferedImage[][] thrustingRight = new BufferedImage[7][9];
		for(int i=0;i<=5;i++){
			thrustingRight[i] = SpriteThrust.getSprite(i, 3);
		}
		thrustingRight[6] = SpriteWalk.getSprite(0, 3);

		BufferedImage[][] thrustingUp = new BufferedImage[7][9];
		for(int i=0;i<=5;i++){
			thrustingUp[i] = SpriteThrust.getSprite(i, 0);
		}
		thrustingUp[6] = SpriteWalk.getSprite(0, 0);

		BufferedImage[][] thrustingDown = new BufferedImage[7][9];
		for(int i=0;i<=5;i++){
			thrustingDown[i] = SpriteThrust.getSprite(i, 2);
		}
		thrustingDown[6] = SpriteWalk.getSprite(0, 2);
		
		
//////
		
		BufferedImage[][] standingLeft = {SpriteWalk.getSprite(0, 1)};
		BufferedImage[][] standingRight = {SpriteWalk.getSprite(0, 3)};
		BufferedImage[][] standingUp = {SpriteWalk.getSprite(0, 0)};
		BufferedImage[][] standingDown = {SpriteWalk.getSprite(0, 2)};
		
			
		BufferedImage[][] castingLeft = {SpriteSpellcast.getSprite(5, 1),SpriteSpellcast.getSprite(4, 1),SpriteSpellcast.getSprite(3, 1),SpriteSpellcast.getSprite(2, 1)};
		BufferedImage[][] castingRight = {SpriteSpellcast.getSprite(5, 3),SpriteSpellcast.getSprite(4, 3),SpriteSpellcast.getSprite(3, 3),SpriteSpellcast.getSprite(2, 3)};
		BufferedImage[][] castingUp = {SpriteSpellcast.getSprite(5, 0),SpriteSpellcast.getSprite(4, 0),SpriteSpellcast.getSprite(3, 0),SpriteSpellcast.getSprite(2, 0)};
		BufferedImage[][] castingDown = {SpriteSpellcast.getSprite(5, 2),SpriteSpellcast.getSprite(4, 2),SpriteSpellcast.getSprite(3, 2),SpriteSpellcast.getSprite(2, 2)};

		
		// These are animation states
		walkLeft = new Animation(walkingLeft, 100, true);
	    walkRight = new Animation(walkingRight, 100, true);
		walkUp = new Animation(walkingUp, 100, true);
		walkDown = new Animation(walkingDown, 100, true);
		standLeft = new Animation(standingLeft, 100, true);
		standRight = new Animation(standingRight, 100, true);
		standUp = new Animation(standingUp, 100, true);
		standDown = new Animation(standingDown, 100, true);	
		
		slashLeft = new Animation(slashingLeft, 100, false);	
		slashRight = new Animation(slashingRight, 100, false);
		slashUp = new Animation(slashingUp, 100, false);	
		slashDown = new Animation(slashingDown, 100, false);
		
		thrustLeft = new Animation(thrustingLeft, 100, false);	
		thrustRight = new Animation(thrustingRight, 100, false);
		thrustUp = new Animation(thrustingUp, 100, false);	
		thrustDown = new Animation(thrustingDown, 100, false);
		
		castLeft = new Animation(castingLeft, 100, false);	
		castRight = new Animation(castingRight, 100, false);
		castUp = new Animation(castingUp, 100, false);	
		castDown = new Animation(castingDown, 100, false);
		
		ready=true;
		animation = standDown;
		animation.start();
	}

	

	
	public int getXLocation(){
		return xLocation;
	}
	
	public int getYLocation(){
		return yLocation;
	}
	
	public void setXLocation (int xLocation){
		this.xLocation = xLocation;
	}
	public void setYLocation (int yLocation){
		this.yLocation = yLocation;
	}
	
  public Player() {
	  setupAnimations();
	  System.out.println("Creating key listener");
	  
	  kl = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
//			System.out.println("Key typed event " + e.getKeyChar());
			switch (e.getKeyChar()) {
			case ' ':
				if (direction == 1){
					animation = slashLeft;
					animation.reset();
					animation.start();
				} else if (direction == 3){
					animation = slashRight;
					animation.reset();
					animation.start();
				} else if (direction == 0){
					animation = slashUp;
					animation.reset();
					animation.start();
				} else if (direction == 2){
					animation = slashDown;
					animation.reset();
					animation.start();
				}
				break;
			case '1':
				if (direction == 1){
					animation = thrustLeft;
					animation.reset();
					animation.start();
				} else if (direction == 3){
					animation = thrustRight;
					animation.reset();
					animation.start();
				} else if (direction == 0){
					animation = thrustUp;
					animation.reset();
					animation.start();
				} else if (direction == 2){
					animation = thrustDown;
					animation.reset();
					animation.start();
				}
				break;
			case 'c':
				spell = null;
				spell = new Spell(Spell.SpellType.FIRELION);
		        Runnable r = new Runnable() {
		            public void run() {
		          	 spell.update();
		            }
		            };
		          new Thread(r).start(); 
				if (direction == 1){
					animation = castLeft;
					animation.reset();
					animation.start();
				} else if (direction == 3){
					animation = castRight;
					animation.reset();
					animation.start();
				} else if (direction == 0){
					animation = castUp;
					animation.reset();
					animation.start();
				} else if (direction == 2){
					animation = castDown;
					animation.reset();
					animation.start();
				}
				break;
			case 'v':
				spell = null;
				spell = new Spell(Spell.SpellType.TORRENT);
		        r = new Runnable() {
		            public void run() {
		          	 spell.update();
		            }
		            };
		          new Thread(r).start(); 
				if (direction == 1){
					animation = castLeft;
					animation.reset();
					animation.start();
				} else if (direction == 3){
					animation = castRight;
					animation.reset();
					animation.start();
				} else if (direction == 0){
					animation = castUp;
					animation.reset();
					animation.start();
				} else if (direction == 2){
					animation = castDown;
					animation.reset();
					animation.start();
				}
				break;
			case 'b':
				spell = null;
				spell = new Spell(Spell.SpellType.SNAKEBITE);
		        r = new Runnable() {
		            public void run() {
		          	 spell.update();
		            }
		            };
		          new Thread(r).start(); 
				if (direction == 1){
					animation = castLeft;
					animation.reset();
					animation.start();
				} else if (direction == 3){
					animation = castRight;
					animation.reset();
					animation.start();
				} else if (direction == 0){
					animation = castUp;
					animation.reset();
					animation.start();
				} else if (direction == 2){
					animation = castDown;
					animation.reset();
					animation.start();
				}
				break;
			}

		}
		
		@Override
		public void keyReleased(KeyEvent e) {

		    if (e.getKeyCode() == 38 && upPressed){
		    	animation = standUp;
			    animation.stop();
			    animation.reset();
			    upPressed = false;
		    }
		    else if (e.getKeyCode() == 37 &&  leftPressed){
		    	animation = standLeft;
		    	leftPressed = false;
			    animation.stop();
			    animation.reset();
		    }
		    else if (e.getKeyCode() == 40 && downPressed){
		    	animation = standDown;
			    animation.stop();
			    animation.reset();
			    downPressed = false;
		    }
		    else if (e.getKeyCode() == 39 && rightPressed){
		    	animation = standRight;
			    animation.stop();
			    animation.reset();
			    rightPressed = false;
		    }
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()){
				case 39:
					if(!leftPressed && !upPressed && !downPressed)
					{
						animation = walkRight;
						animation.start();
						rightPressed = true;
						direction = 3;
					}
					break;
				case 37:
					if(!upPressed && !downPressed && !rightPressed)
					{
						animation = walkLeft;
						animation.start();
						leftPressed = true;
						direction = 1;
					}
					break;
				case 38:
					if(!leftPressed && !downPressed && !rightPressed){
						animation = walkUp;
						animation.start();
						upPressed = true;
						direction = 0;
					}
					break;
				case 40:
					if(!leftPressed && !upPressed && !rightPressed){
						animation = walkDown;
						animation.start();
						downPressed = true;
						direction = 2;
					}
					break;

			}							
		}
	};
	  
  }
  
  public Spell getSpell(){
	  return this.spell;
  }
  
  
  public void updateLocation(){
	  //Determine the direction we are going, call checkOutOfBounds passing in our speed to see if we are allowed to move there
	  if (leftPressed && checkOutOfBounds(0,-walkSpeed)){
		  yLocation = yLocation - walkSpeed;
	  }
	  //was 50
	  if (rightPressed && checkOutOfBounds(0,walkSpeed+(int)(0.64*tileSize))){
		  yLocation = yLocation + walkSpeed;
	  }
	  if (upPressed && checkOutOfBounds(-walkSpeed,0)){
		  xLocation = xLocation - walkSpeed;
	  }
	  //was 19
	  if (downPressed && checkOutOfBounds(walkSpeed+(int)(0.24*tileSize),0)){		  
		  xLocation = xLocation + walkSpeed;
	  }
  }
  
  //Using the speed in the x and y directions (dx and dy), check to see if the spot we are about to move to is valid.
  public boolean checkOutOfBounds(int dx, int dy){
	  //First figure out what tile we are about to move to.  The top left tile is 0,0.
	  
	  int xTile = Math.floorDiv(xLocation+dx,tileSize); //Top edge of character shadow
	  int xTile2 = xTile; //Bottom edge of character shadow (see note about xTile2)
	  
	  //Right edge of character shadow
	  int yTile = Math.floorDiv(yLocation+dy,tileSize);	  //Right edge of character shadow 
	  int yTile2 = yTile; //Left edge of character shadow (see note about yTile2 below)
	  
	  
	  //This was added to fix a bug where as long as the "left" part of your shadow was on solid land, you could float over an invalid tile
	  //while moving vertically. Only set the yTile2 if we are moving vertically.
	  if(dx != 0)
	  {
		  yTile2 = Math.floorDiv(yLocation+dy+(int)(0.64*tileSize),tileSize);
	  }
	  
	  //This was added to fix a bug where as long as the "top" part of your shadow was on solid land, you could float over an invalid tile
	  //while moving horizontally. Only set the xTile2 if we are moving horizontally.
	  if(dy != 0)
	  {
		  xTile2 = Math.floorDiv(xLocation+dx+(int)(0.24*tileSize),tileSize);
	  }
	  
	  //Important check #1: make sure we aren't trying to walk off the boundaries of the map
	  if(yTile < 0 || xTile < 0 || yTile >= mapLayout[0].length || xTile >= mapLayout.length-1 ){
		  return false;
	  } 
	  
	  //Grab the tile label from the map
	  String tile = mapLayout[xTile][yTile];
	  String tile2 = mapLayout[xTile][yTile2];
	  String tile3 = mapLayout[xTile2][yTile];

	  //Important check #2: make sure we aren't about to move to an invalid tile
	  if(tile.equals("000") || tile.equals("1BB") || tile2.equals("000") || tile2.equals("1BB")|| tile3.equals("000") || tile3.equals("1BB")){
		  return false;
	  }

	  //If we passed all of the tests, we are allowed to move to the spot
	  return true;
  }
  
  public KeyListener getKeyListener(){
	  return kl;
  }
  
  public void update(){
	  long start;
	  long elapsed;
	  long wait;
	  while(!ready)
	  {
		  try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  while(true)
	  {
		start = System.nanoTime();
		animation.update();
		updateLocation();
		
		elapsed = System.nanoTime() - start;
		wait = targetTime - elapsed / 1000000;
		
	    try {
	    	if(wait > 0)
	    		Thread.sleep(wait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
  }
  
  
  public BufferedImage[] getPlayerSprite(){
	  return animation.getSprite();
  }
	
  //Map calls this to give the player access to the map
  public void setMapLayout(String[][] mapLayout){
	  this.mapLayout = mapLayout;
	  
  }
  public void setTileSize(int tileSize){
	  this.tileSize = tileSize;
  }



}
