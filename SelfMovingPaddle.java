import greenfoot.*;

/**
 * A Self Moving Paddle is an object that is independant from a player paddle . The paddle itself changes direction each time it reaches the edge of the map .
 * Then it chooses a random height and respawns.
 * 
 * @author Nedas Surkus
 * @version 1.1
 */
public class SelfMovingPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;
    
    GreenfootImage image = null;
    /**
     * Constructs a new self moving paddle with the given dimensions.
     */
    public SelfMovingPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 2;
        createImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        tryChangeDirection();
        setLocation(getX() + dx, getY());
    }    

    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     * Will also respawn the paddle at a random height
     */
    private void tryChangeDirection()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() + width/2 >= getWorld().getWidth() || getX() - width/2 <= 0)
        {
            //Change our 'x' direction to the inverted direction , ant respawns the paddle at random height in the map
            dx = dx * -1;
            setLocation(getX() + dx, (Greenfoot.getRandomNumber(800)-200));
            image.rotate(180);
        }
    }

    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        image = new GreenfootImage("arrow.png");
        setImage(image);
    }

}