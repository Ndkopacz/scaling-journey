import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSpell {

    private static BufferedImage spriteSheet_spell;

    private static int tileSize = 64;
    

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File("assets/spritesheets/magic/" + file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage[] getSprite(int xGrid, int yGrid, Spell.SpellType spell) {
    	switch(spell){
    	case FIRELION:
    		spriteSheet_spell = loadSprite("magic_firelion_sheet.png");
    		break;
    	case ICESHIELD:
    		spriteSheet_spell = loadSprite("magic_iceshield_sheet.png");
    		break;
    	case SNAKEBITE:
    		spriteSheet_spell = loadSprite("magic_snakebite_sheet.png");
    		break;
    	case TORRENT:
    		spriteSheet_spell = loadSprite("magic_torrentacle.png");
    		break;
    	case TURTLESHELL_FRONT:
    		spriteSheet_spell = loadSprite("turtleshell_front.png");
    		break;
    	case TURTLESHELL_SIDE:
    		spriteSheet_spell = loadSprite("turtleshell_side.png");
    		break;
    	}
    	tileSize = spriteSheet_spell.getHeight()/4;


        BufferedImage[] sprites = new BufferedImage[1];
        System.out.println(xGrid + " " + yGrid);
        sprites[0] = spriteSheet_spell.getSubimage(xGrid * tileSize, yGrid * tileSize, tileSize, tileSize);

        return sprites;
    }

}