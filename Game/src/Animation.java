import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Animation {

    private long nextFrameTime;                 // frame delay 1-12 (You will have to play around with this)
    private int frameDelay;
    public int currentFrame;               // animations current frame
    private int animationDirection;         // animation direction (i.e counting forward or backward)
    public int totalFrames;                // total amount of frames for your animation
    private boolean stopped;                // has animations stopped
    private boolean shouldLoop;

    private List<Frame> frames = new ArrayList<Frame>();    // Arraylist of frames 

    public Animation(BufferedImage[][] frames, int frameDelay, boolean shouldLoop) {
        this.frameDelay = frameDelay;
        this.shouldLoop = shouldLoop;
        this.stopped = true;
        nextFrameTime = 0;

        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }

        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = this.frames.size();

    }

    public void start() {
        if (!stopped) {
            return;
        }

        if (frames.size() == 0) {
            return;
        }

        stopped = false;
    }

    public void stop() {
        if (frames.size() == 0) {
            return;
        }

        stopped = true;
    }

    public void restart() {
        if (frames.size() == 0) {
            return;
        }

        stopped = false;
        currentFrame = 0;
    }

    public void reset() {
        this.stopped = true;
        this.currentFrame = 0;
    }

    private void addFrame(BufferedImage[] frame, int duration) {
        if (duration <= 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }

        frames.add(new Frame(frame, duration));
        currentFrame = 0;
    }

    public BufferedImage[] getSprite() {
//    	System.out.println("Got frame: " + currentFrame);
    	if(currentFrame < frames.size())
    	{
    		return frames.get(currentFrame).getFrame();
    	}
    	else
    	{
    		return frames.get(frames.size()).getFrame();
    		
    	}
    }

    public void update() {
        if (!stopped) {
//        	System.out.println("update called " + currentFrame + " " + totalFrames);

            if (System.currentTimeMillis() - nextFrameTime > 0) {
            	nextFrameTime = System.currentTimeMillis() + frameDelay;

            	if(currentFrame == (totalFrames - 1))
            	{
            		if(shouldLoop){
            			currentFrame = 0;
            		}
            	}
                else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            	else
            	{
            		currentFrame += animationDirection;
            	}
               

            }
        }

    }

}