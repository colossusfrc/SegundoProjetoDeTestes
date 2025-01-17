// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.CommandResources.CommandRunner;
public class Robot extends TimedRobot{
  private Arrematamento arrematamento;
  public Robot(){
    this.arrematamento = new Arrematamento();
  }
  @Override 
  public void robotInit() {}

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Velocidade: ", arrematamento.velocityCalculator.getVelocity());
  }
  @Override
  public void disabledPeriodic() {

  }
  @Override
  public void autonomousInit() {
    
  }

  @Override
  public void autonomousPeriodic() {
    arrematamento.getAutonomousCommand().begin();
  }
  @Override
  public void autonomousExit() {
  }
  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {
    arrematamento.testCommands().forEach(CommandRunner::begin);
  }
  @Override
  public void teleopExit() {
      
  }
  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {
  }
}