import greenfoot.*;

/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    public PingWorld()
    { 
        // calling the other constructor with gameStarted = false.
        this(false);       
    }
    
    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK);
            // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
            
            addObject(new Paddle(100,20), 60, WORLD_HEIGHT - 50);
            addObject(new SelfMovingPaddle((Greenfoot.getRandomNumber(70)+ 30),(Greenfoot.getRandomNumber(10) + 10)), (WORLD_WIDTH/2), (Greenfoot.getRandomNumber(WORLD_HEIGHT) - 200));
            addObject(new Counter("Game Level: " ),WORLD_WIDTH -100, WORLD_HEIGHT-650);
            addObject(new Ball(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    
}
