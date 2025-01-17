package frc.robot.Commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.CommandResources.CommandRunner;
import frc.robot.SubsystemResources.ServoSubsystem;

public class AutonomousCommand extends CommandRunner{
    private enum States{first, second, third}
    private double dt = 2;
    private States ZS = States.first;
    private States YS = States.first;
    private Joystick controle;
    private ServoSubsystem servos;
    private Timer timer;
    public AutonomousCommand(Definir builder){
      timer = new Timer();
      this.servos = builder.servos;
      this.controle = builder.controle;}
    public AutonomousCommand(ServoSubsystem servos){
        timer = new Timer();
        this.servos = servos;}
    public static class Definir{
      private ServoSubsystem servos;
      private Joystick controle;
      public Definir servos(ServoSubsystem servos){
        this.servos = servos;
        return this;}
      public Definir controle(Joystick controle){
        this.controle = controle;
        return this;}
      public AutonomousCommand construir(){
        return new AutonomousCommand(this);}
    }
    @Override
    public void start() {
        
    }
    @Override
    public void stop() {
        
    }
    @Override
    public void periodic() {
    timer.start();
    double time = timer.get();
    SmartDashboard.putNumber("Time: ", timer.get()-time);
    SmartDashboard.putBoolean("State: ", finish());
    if(timer.get()<.25){
      ZS = States.first;
      YS = States.first;
    }
    switch (YS) {
      case first:
        servos.setYAngle(servos.getYAngle()[1]);
       if(timer.get()>dt){
        YS = States.second;
       }
        break;
      case second:
        servos.setYAngle(servos.getYAngle()[2]);
       if(timer.get()>2*dt){
        YS = States.third;
       }
        break;
      case third:
        servos.setYAngle(servos.getYAngle()[2]);
       if(timer.get()>3*dt){
        YS = States.first;
        timer.reset();
       }
        break;
      default:
        break;
    }
    
    switch (ZS) {
      case first:
      servos.setBaseAngle(servos.getBaseAngle()[1]);
      if(timer.get()>dt){
      ZS = States.second;
      }
        break;
      case second:
      servos.setBaseAngle(servos.getBaseAngle()[0]);
      if(timer.get()>2*dt){
      ZS = States.third;
      }
      break;
      case third:
      servos.setBaseAngle(servos.getBaseAngle()[2]);
      if(timer.get()>3*dt){
        ZS = States.first;
        timer.reset();
      }
      break;
      default:
        break;
    }
  }
  @Override
  public boolean finish() {
   return controle.getRawButton(2);
  }
}