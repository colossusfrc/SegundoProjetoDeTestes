package frc.robot.CommandResources;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;

public class Looper extends Commands{
    public final double periodo;

    private boolean running;

    private final Notifier notifier;
    private final List<Commands> commandLoops;
    private final Object targetTask = new Object();
    private double timeStamp = 0;
    protected double dt = 0;
    private final LoopingRunnable loopingRunnable = new LoopingRunnable() {
        @Override
        public void tryTime() {
            synchronized(targetTask){
                double lastTime = Timer.getFPGATimestamp();
                commandLoops.forEach(Commands::periodic);
                dt = lastTime - timeStamp;
                timeStamp = lastTime;
            }
        };
    };
    public Looper(double clock){
        notifier = new Notifier(loopingRunnable);
        running = false;
        commandLoops = new ArrayList<>();
        periodo = clock;
    }
    public synchronized void registrer(Commands commands){
        synchronized (targetTask){
            commandLoops.add(commands);
        }
    }

    public synchronized void start(){
       if(!running){
        System.out.println("começa loop");
        synchronized(targetTask){
            timeStamp = Timer.getFPGATimestamp();
            commandLoops.forEach(Commands::start);
            running = true;
        }
        notifier.startPeriodic(periodo);
       }
    }

    public synchronized void stop(){
       if(running){
        System.out.println("para loop");
        notifier.stop();
        synchronized(targetTask){
            running = false;
            commandLoops.forEach(Commands::finish);
        }
        notifier.startPeriodic(periodo);
       }
    }

    @Override
    public void run() {}

    @Override
    public void periodic() {}
    @Override
    public boolean finish() {return false;}
    
}
