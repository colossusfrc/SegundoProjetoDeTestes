package frc.robot.CommandResources;

public abstract class LoopingRunnable implements Runnable{
    @Override
    public void run() {
        try{
            tryTime();
        }catch(Throwable t){

        }
    }
    public abstract void tryTime();
}
