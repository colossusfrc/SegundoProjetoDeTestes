package frc.robot.Commands;

import java.util.function.Supplier;

import frc.robot.CommandResources.CommandRunner;
import frc.robot.SubsystemResources.MotorSubsystem;

public class MotorCommand extends CommandRunner{
    private final MotorSubsystem motor;
    private Supplier<Double> joystick;
    public MotorCommand(MotorSubsystem motor, Supplier<Double> joystick){
        this.motor = motor;this.joystick = joystick;}
    public MotorCommand(Definir builder){
        this.motor = builder.motor;this.joystick = builder.joystick;}
    public static class Definir{
        private MotorSubsystem motor;
        private Supplier<Double> joystick;
        public Definir motor(MotorSubsystem motor){
            this.motor = motor;
            return this;}
        public Definir joystick(Supplier<Double> joystick){
            this.joystick = joystick;
            return this;}
        public MotorCommand build(){
            return new MotorCommand(this);}
    }

    @Override
    public void periodic() {
        double power = joystick.get();
        motor.setPower((Math.abs(power)>motor.getMaxPower())?Math.signum(power)*motor.getMaxPower():power);
    }
    @Override
    public void start() {}
    @Override
    public void stop() {
        motor.setPower(0);
    }   
}