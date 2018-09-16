import greenfoot.*;

/**
 * A Ball is a thing that bounces of walls and paddles.
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends Actor
{
    private static final int BALL_SIZE = 16;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;

    private int speed;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private int delay;
    private int bounceCount = 0 ;
    private int delayFalseChange = 0 ;
    private boolean isFalsePaddle = false;
    GreenfootSound themeSong = new GreenfootSound("themeSong.mp3");
    GreenfootSound sound = null;

    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        createImage();
        init();
        themeSong.play();
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage("ballSprite.gif");
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkRestart();
            if(isTouching(Paddle.class) || isTouching(SelfMovingPaddle.class))
            {
                if (isTouching(SelfMovingPaddle.class)) isFalsePaddle = true;
                checkBounceOffPaddle();
            }
        }
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }
    /**
     * Returns true if the ball is touching one of the paddles side walls.
     */
    private boolean isTouchingPaddleSide()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= 75 - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling of the paddle.
     */
    private boolean isTouchingPaddleTop()
    {
        return (getY() >= 45 - BALL_SIZE/2);
    }
    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }
    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
                GreenfootSound sound = new GreenfootSound("metalClang.wav");
                sound.play();
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling())
        {
            if (! hasBouncedVertically)
            {
                revertVertically();
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }
    /* Checks if it has hitted the paddle. If it has , it bounces horizontally back , if it hit the side , it bouces sideways*/
    private void checkBounceOffPaddle()
    {
        if (isTouchingPaddleTop())
        {
            if (! hasBouncedVertically)
            {
                revertVertically();
                if(!isFalsePaddle) checkSpeed();
                isFalsePaddle = false;
            }
        }
        else if (isTouchingPaddleSide())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
                isFalsePaddle = false;
            }
        }else{
            hasBouncedVertically = false;
            hasBouncedHorizontally = false;
            isFalsePaddle = false;
        }
    }
    
    /**
     * Checks if the ball hit the bottom of the world , and if it did , plays an explosion sound and ends the game
     * 
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            Greenfoot.setWorld(new EndWorld());
            GreenfootSound sound = new GreenfootSound("explosion.wav");
            sound.play();
            themeSong.stop();
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;       
    }

    /**
     * Checks what kind of sound should be playing and then bounces ball back from horizontal surface.
     * However it will only bounce the ball from the bottom surfice if the paddle is a self moving paddle.
     * 
     */
    private void revertVertically()
    {
       int currentRotation = getRotation();
       if(isFalsePaddle)
       {
           sound = new GreenfootSound("woodHit.wav"); 
       }
       else
       {
           sound = new GreenfootSound("swordClash.wav");
       }
            
       if(isFalsePaddle && currentRotation <= 180)
       {
           hasBouncedVertically = true;
       }
       else
       {
           int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
           setRotation((360 - getRotation()+ randomness + 360) % 360);
           hasBouncedVertically = true;
           sound.play();
       }
    }

    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }
    
    /**
     * After each succesfull wall hit , it adds to the bounce count , if the bounce count is 10 , it changes the level count to +1
     */
    private void checkSpeed(){
        bounceCount++;
        if(bounceCount % 10 == 0)
        { 
            speed++;
            getWorld().getObjects(Counter.class).get(0).add(1);
        }
    }
}
