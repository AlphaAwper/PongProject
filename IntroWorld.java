import greenfoot.*;

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        setBackground("background.jpg");
        GreenfootImage background = getBackground();
        background.setColor(Color.WHITE);
        background.drawString("Brave knight , the year is 1453 since the birth of the son of god." , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -600);
        background.drawString("This is our last stand , the last stand of Constantinople." , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -550);
        background.drawString("As you were dodging arrows left and right" , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -500);
        background.drawString("a powerful wizard launched a fireball directly  at your oil reserves." , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -450);
        background.drawString("If the fireball hits , the walls that keep the citizens safe will be destroyed." , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -400);
        background.drawString("Use left and right buttons to control your sword." , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -350);
        background.drawString("Prevent the fireball from hitting and we might just win. God speed brother." , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -300);
        background.drawString("Press Enter to start" , WORLD_WIDTH / 2 - 200, WORLD_HEIGHT -250);
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
