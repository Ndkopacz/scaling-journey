import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Map extends JPanel {

    
    private static final Color BACKGROUND = new Color(33,30,39);

	private int tileHeight = 32;
    private int tileWidth = 32;
    private static final int PREFERRED_GRID_SIZE_PIXELS = 32;
    private int playerSize = 48;
    private int xOffset = 0;
    private int yOffset = 0;
    private int playerYOffset = 0;
    private int playerXOffset = 0;
    
    private ArrayList<BufferedImage> gameTiles;

    private int numRows;
    private int numCols;

    

    // In reality you will probably want a class here to represent a map tile,
    // which will include things like dimensions, color, properties in the
    // game world.  Keeping simple just to illustrate.
    private final String[][] mapLayout;
    private int[][] mapGrid;
    
    private int[] topTiles = {2,3,4};
    private int[] bottomTiles = {22,23,24};
    private int[] leftTiles = {6,11,16};
    private int[] rightTiles = {10,15,20};
    private int[] topLeftTiles = {1};
    private int[] topRightTiles = {5};
    private int[] bottomLeftTiles = {21};
    private int[] bottomRightTiles= {25};
    private int[] centerTiles = {7,8,9,12,13,14,17,18,19};
    private int[] bottomBottomTiles = {26};
    private int[] bridgeHorizontalTiles = {27};
    
    private Player player;
   
    public Map(Player player){
    	this.player = player;
        
        Runnable r = new Runnable() {
          public void run() {
        	 System.out.println("Starting player update.");
          	player.update();
          }
          };
        new Thread(r).start();  
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		
		addKeyListener(player.getKeyListener());
    	
    	
        gameTiles = new ArrayList<BufferedImage>();
        gameTiles.add(null);
 		try {			
	        for(int i=1;i<=27;i++){
	        	gameTiles.add(ImageIO.read(new File("assets/dungeon_tiles/dungeon_tiles_large_tileset_main-"+i+".png")));
	        }	
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		

    	mapLayout = new String[][]{
    		{"1TL", "1T0", "1T0", "1T0", "1T0", "1T0", "1T0", "1TR", "000", "1TL", "1T0", "1T0", "1T0", "1T0", "1T0", "1T0", "1T0", "1TR"},
    		{"1L0", "10C", "10C", "10C", "10C", "10C", "10C", "1R0", "000", "1L0", "10C", "10C", "10C", "10C", "10C", "10C", "10C", "1R0"},
    		{"1L0", "10C", "10C", "10C", "10C", "10C", "10C", "1R0", "BHM", "1L0", "10C", "10C", "10C", "10C", "10C", "10C", "10C", "1R0"},
    		{"1L0", "10C", "10C", "10C", "10C", "10C", "10C", "1R0", "000", "1BL", "10C", "10C", "10C", "10C", "10C", "10C", "10C", "1R0"},
    		{"1L0", "10C", "10C", "1B0", "10C", "10C", "10C", "1R0", "000", "1BB", "1L0", "10C", "10C", "10C", "10C", "10C", "10C", "1R0"},
    		{"1L0", "10C", "1R0", "1BB", "1L0", "10C", "10C", "10C", "1T0", "1T0", "10C", "10C", "10C", "10C", "10C", "10C", "10C", "1R0"},
    		{"1L0", "10C", "10C", "1T0", "10C", "10C", "10C", "10C", "1B0", "1B0", "10C", "10C", "10C", "10C", "10C", "10C", "10C", "1R0"},
    		{"1L0", "10C", "10C", "10C", "10C", "10C", "10C", "1R0", "1BB", "1BB", "1L0", "10C", "10C", "10C", "10C", "10C", "10C", "1R0"},
    		{"1BL", "1B0", "10C", "10C", "10C", "10C", "10C", "1R0", "000", "000", "1L0", "10C", "10C", "10C", "10C", "10C", "10C", "1R0"},
    		{"1BB", "1BB", "1L0", "10C", "10C", "10C", "10C", "1R0", "000", "000", "1L0", "10C", "10C", "10C", "10C", "10C", "10C", "1R0"},
    		{"000", "000", "1BL", "1B0", "1B0", "1B0", "1B0", "1BR", "000", "000", "1BL", "1B0", "1B0", "1B0", "1B0", "1B0", "1B0", "1BR"},
    		{"000", "000", "1BB", "1BB", "1BB", "1BB", "1BB", "1BB", "000", "000", "1BB", "1BB", "1BB", "1BB", "1BB", "1BB", "1BB", "1BB"}
    	};
  
    	
    	
    	numRows = mapLayout.length;
    	numCols = mapLayout[0].length;
    	
    	mapGrid = new int[numRows][numCols];
    	
    	for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                
                String tileLable = mapLayout[i][j];
                Random rand = new Random();
                
                switch (tileLable){
	                case "000": 
	                	mapGrid[i][j] = 0;
	                	break;
	                case "10C":
	                	mapGrid[i][j] = centerTiles[rand.nextInt(centerTiles.length)];
	                	break;
	                case "1T0":
	                	mapGrid[i][j] = topTiles[rand.nextInt(topTiles.length)];
	                	break;
	                case "1B0":
	                	mapGrid[i][j] = bottomTiles[rand.nextInt(bottomTiles.length)];
	                	break;
	                case "1L0":
	                	mapGrid[i][j] = leftTiles[rand.nextInt(leftTiles.length)];
	                	break;
	                case "1R0":
	                	mapGrid[i][j] = rightTiles[rand.nextInt(rightTiles.length)];
	                	break;
	                case "1TL":
	                	mapGrid[i][j] = topLeftTiles[rand.nextInt(topLeftTiles.length)];
	                	break;
	                case "1TR":
	                	mapGrid[i][j] = topRightTiles[rand.nextInt(topRightTiles.length)];
	                	break;
	                case "1BL":
	                	mapGrid[i][j] = bottomLeftTiles[rand.nextInt(bottomLeftTiles.length)];
	                	break;
	                case "1BR":
	                	mapGrid[i][j] = bottomRightTiles[rand.nextInt(bottomRightTiles.length)];
	                	break;
	                case "1BB":
	                	mapGrid[i][j] = bottomBottomTiles[rand.nextInt(bottomBottomTiles.length)];
	                	break;
	                case "BHM":   //TODO: bridge extends to both paths
	                	mapGrid[i][j] = bridgeHorizontalTiles[rand.nextInt(bridgeHorizontalTiles.length)];
	                	break;
                }
            }
        }
    	
      	
//        int preferredWidth = (numCols+2) * PREFERRED_GRID_SIZE_PIXELS;
//        int preferredHeight = (numRows+2) * PREFERRED_GRID_SIZE_PIXELS;
//        setPreferredSize(new Dimension(preferredWidth, preferredHeight));

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        int numBlocksHorizontal = mapLayout[0].length + 2;
        int numBlocksVertical = mapLayout.length + 2;
        tileWidth = Math.floorDiv(width, numBlocksHorizontal);
        tileHeight = Math.floorDiv(height, numBlocksVertical);
        tileWidth = Math.min(tileWidth, tileHeight);
        tileHeight = tileWidth;        
        playerSize = (int)(tileHeight*1.5);
        
        //These values are used in the paintComponent function to align the player and map to the screen
        yOffset = (width-(tileWidth*mapLayout[0].length))/2;
        xOffset = (height-(tileHeight*mapLayout.length))/2;
        playerYOffset = (int) (yOffset - 0.25*playerSize);
        playerXOffset = (int) (xOffset - 0.83*playerSize);
        
        
        player.setMapLayout(mapLayout);
        player.setTileSize(tileWidth);
        
        
    }
    

    @Override
    public void paintComponent(Graphics g) {
        // Important to call super class method
        super.paintComponent(g);
        // Clear the board
        g.clearRect(0, 0, getWidth(), getHeight());
        
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        
        // Draw the grid
        //setBackground(BACKGROUND);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
            	
                int x = (i) * tileWidth;
                int y = (j) * tileHeight;           
                int tileLable = mapGrid[i][j];
                if(tileLable != 27)
                {
                	g.drawImage(gameTiles.get(tileLable), y+yOffset, x+xOffset, tileWidth, tileHeight, null);
                }
                

            }
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int x = (i) * tileWidth;
                int y = (j) * tileHeight;           
                int tileLable = mapGrid[i][j];
                if(tileLable == 27)
                {
                	int bridgeOffsetPerSide = tileWidth/16*2;
                	g.drawImage(gameTiles.get(tileLable), y-bridgeOffsetPerSide+yOffset, x+bridgeOffsetPerSide/4+xOffset, tileWidth+2*bridgeOffsetPerSide, tileHeight, null);
                }         
            }
        }
        try{
	        for (int i = 0; i < player.getPlayerSprite().length ; i++){
	        	if(player.getPlayerSprite()[i].getHeight() < 192){
	        		g.drawImage(player.getPlayerSprite()[i], player.getYLocation()+playerYOffset, player.getXLocation()+playerXOffset, playerSize, playerSize,  null);
	        	} else {
	            	g.drawImage(player.getPlayerSprite()[i], player.getYLocation()-playerSize+playerYOffset, player.getXLocation()-playerSize+playerXOffset, playerSize*3, playerSize*3,  null);
	        	}
	        }
        } catch(NullPointerException e)
        {
        	
        }
        
        if(player.getSpell() != null && player.getSpell().getSpellSprite()[0] != null){
        	
        	g.drawImage(player.getSpell().getSpellSprite()[0], player.getYLocation()+playerSize/2+playerYOffset, player.getXLocation()+playerXOffset, (int)(Math.round(playerSize*1.75)), playerSize,  null);	
        }
        
    	this.repaint();
  
        
    }
    
    

    public static void main(String[] args) {
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Game");
                Player player = new Player(); 
                player.setXLocation(0);
                player.setYLocation(0);
                
                Map map = new Map(player);
                
                frame.add(map);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 

                frame.pack();
                frame.setVisible(true); 
                


            }
        });
        
        

    }
}