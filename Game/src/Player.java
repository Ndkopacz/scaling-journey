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
	
	private Animation castLeft = null;
	private Animation castRight = null;
	private Animation castUp = null;
	private Animation castDown = null;
	
	
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
	  if (leftPressed ){
		  yLocation = yLocation - 1;
	  }
	  if (rightPressed ){
		  yLocation = yLocation + 1;
	  }
	  if (upPressed ){
		  xLocation = xLocation - 1;
	  }
	  if (downPressed ){
		  xLocation = xLocation + 1;
	  }
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
  
//  public static void main(String[] args) {
//    JFrame frame = new JFrame();
//    Player player = new Player();
//
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.setSize(200, 200);
//    frame.getContentPane().add(player);
//    frame.setVisible(true);
//
//        
//    Runnable r = new Runnable() {
//        public void run() {
//        	player.update();
//        }
//    };
//    new Thread(r).start();    
//  }
  
  public BufferedImage[] getPlayerSprite(){
	  return animation.getSprite();
  }
	
//  @Override
//  public void paintComponent(Graphics g) {
//      super.paintComponent(g);      
//	  g.drawImage(animation.getSprite(), 0, 0, null);	  
//	  if(System.currentTimeMillis() - nextRefresh > 0)
//	  {
//		nextRefresh += refreshRate_s; 
//		this.repaint();
//		this.setFocusable(true);
//		this.requestFocusInWindow();
//	  }
//  }



}
