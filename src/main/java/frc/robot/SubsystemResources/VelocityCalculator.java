package frc.robot.SubsystemResources;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.CommandResources.Commands;
import frc.robot.CommandResources.Looper;

public class VelocityCalculator extends Commands{
    private final Timer time = new Timer();
    private final Looper looper = new Looper(0.01);
    private double lastPosition, lastTime, currentVelocity;
    private final Supplier<Double> positions;
    public double getVelocity(){return currentVelocity;}
    public VelocityCalculator(Supplier<Double> positions){
        time.reset();
        time.start();

        lastTime = time.get();
        looper.registrer(this);
        this.positions = positions;
    }
    @Override
    public void run() {}

    @Override
    public void periodic() {
        double currentPosition = positions.get();
        double currentTime = time.get();
        double ds = (currentPosition - lastPosition);
        double dt = (currentTime - lastTime);
        
        currentVelocity = ds/dt;

        lastPosition = currentPosition;
        lastTime = currentTime;
    }

    @Override
    public void start() {}

    @Override
    public void stop() {}

    @Override
    public boolean finish() {return false;}
    
}
