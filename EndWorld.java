import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    
    /**
     * Constructor for objects of class EndWorld.
     * 
     */
    public EndWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        setBackground("background3.png");
        GreenfootImage background = getBackground();
        background.setColor(Color.WHITE);
        GreenfootSound sound = new GreenfootSound("themeSong.mp3");
        sound.stop();
        background.drawString("You heroically tried to fight fate and lost." , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -200);
        background.drawString("To try again press enter." , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -150);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
}
