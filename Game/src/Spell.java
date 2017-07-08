import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Spell extends JPanel
{
	
	private Animation firelionAnimation = null;
	
	boolean ready = false;
	// This is the actual animation
	private Animation animation;
	
	private final double FPS = 60;
	private long targetTime = (long) (1000/FPS);
	
	private int xLocation = 0;
	private int yLocation = 0;
	
    public enum SpellType {FIRELION, ICESHIELD, SNAKEBITE, TORRENT, TURTLESHELL_FRONT, TURTLESHELL_SIDE};

	private SpellType spellType;
	
	
	  public Spell(SpellType spellType) {
		  this.spellType = spellType;
		  setupAnimations();		 
	  }
	  
	public void setupAnimations(){
		
		BufferedImage[][] firelion = new BufferedImage[18][1];
		int iteration = 1;
		for(int i=0; i<=3; i++){
			for(int j=0;j<=3;j++){
				firelion[iteration] = SpriteSpell.getSprite(j, i, SpellType.FIRELION);
				iteration++;
			}
		}
		
		System.out.println("Animation size: " + firelion.length);
		
		firelionAnimation = new Animation(firelion, 100, false);		
		switch (spellType) {
			case FIRELION:
				System.out.println("set animation to FL");
				animation = firelionAnimation;
				break;
		}
		
		ready=true;
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

  
  
  public void update(){
	  long start;
	  long elapsed;
	  long wait;
	  long startTime = System.currentTimeMillis() + 500;
	  while(System.currentTimeMillis() < startTime)
	  {
		  try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  System.out.println("ready!");
	  animation.start();
	  while(true)
	  {
		start = System.nanoTime();
		animation.update();
		
		
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

  public BufferedImage[] getSpellSprite(){
	  return animation.getSprite();
  }
	
}
