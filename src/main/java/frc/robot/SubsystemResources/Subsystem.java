package frc.robot.SubsystemResources;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Subsystem extends SubsystemBase{
    protected Integer k;
    public Subsystem(){
        this.k = this.hashCode();
        Thread thread = new Thread(()->{
            while(true){subsystemPeriodic();
            }
        });
        thread.start();
    }
    public void subsystemPeriodic(){}
}
