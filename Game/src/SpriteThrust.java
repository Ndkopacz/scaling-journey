import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteThrust {

    private static BufferedImage spriteSheet_body;
    private static BufferedImage spriteSheet_torso;
    private static BufferedImage spriteSheet_pants;  
    private static BufferedImage spriteSheet_belt;
    private static BufferedImage spriteSheet_shoes;
    private static BufferedImage spriteSheet_hood;
    private static BufferedImage spriteSheet_gloves;
    private static BufferedImage spriteSheet_weapon;
    private static BufferedImage spriteSheet_shadow;
    private static BufferedImage spriteSheet_shield;
    private static final int TILE_SIZE = 64;
    private static final int TILE_SIZE_LARGE = 192;


    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File("assets/spritesheets/thrust/" + file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage[] getSprite(int xGrid, int yGrid) {
        if (spriteSheet_shadow == null) {
        	spriteSheet_shadow = loadSprite("shadow.png");
        }
        if (spriteSheet_body == null) {
        	spriteSheet_body = loadSprite("BODY_male.png");
        }
        if (spriteSheet_torso == null) {
        	spriteSheet_torso = loadSprite("TORSO_chain_armor_torso.png");
        }
        if (spriteSheet_pants == null) {
        	spriteSheet_pants = loadSprite("LEGS_plate_armor_pants.png");
        }
        if (spriteSheet_belt == null) {
        	spriteSheet_belt = loadSprite("BELT_leather.png");
        }
        if (spriteSheet_shoes == null) {
        	spriteSheet_shoes = loadSprite("FEET_shoes_brown.png");
        }
        if (spriteSheet_hood == null) {
        	spriteSheet_hood = loadSprite("HEAD_robe_hood.png");
        }
        if (spriteSheet_gloves == null) {
        	spriteSheet_gloves = loadSprite("HANDS_plate_armor_gloves.png");
        }
        if (spriteSheet_shield == null) {
        	spriteSheet_shield = loadSprite("WEAPON_shield_cutout_chain_armor_helmet.png");
        }
        if (spriteSheet_weapon == null) {
//        	spriteSheet_weapon = loadSprite("glowsword_red_male.png");
        	spriteSheet_weapon = loadSprite("WEAPON_long_spear.png");


        }
        
        BufferedImage[] sprites = new BufferedImage[10];
        sprites[0] = spriteSheet_shadow;
        sprites[1] = spriteSheet_body.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        sprites[2] = spriteSheet_shoes.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        sprites[3] = spriteSheet_pants.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        sprites[4] = spriteSheet_torso.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        sprites[5] = spriteSheet_belt.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        sprites[6] = spriteSheet_hood.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        sprites[7] = spriteSheet_gloves.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        sprites[8] = spriteSheet_weapon.getSubimage(xGrid * TILE_SIZE_LARGE, yGrid * TILE_SIZE_LARGE, TILE_SIZE_LARGE, TILE_SIZE_LARGE);
        sprites[9] = spriteSheet_shield.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        return sprites;
    }

}