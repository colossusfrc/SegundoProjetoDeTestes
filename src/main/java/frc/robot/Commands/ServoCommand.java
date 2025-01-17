package frc.robot.Commands;

import java.util.function.Supplier;

import frc.robot.CommandResources.CommandRunner;
import frc.robot.SubsystemResources.ServoSubsystem;

public class ServoCommand extends CommandRunner{
    private final Supplier<Double> controle1, controle0;
    private final Supplier<Boolean> isPressed;
    private final ServoSubsystem servos;
    public ServoCommand(Definir builder){
      this.servos = builder.servos;this.controle1 = builder.controle1;
      this.controle0 = builder.controle0;this.isPressed = builder.isPressed;}
    public ServoCommand(ServoSubsystem servos, Supplier<Boolean> isPressed, Supplier<Double> controle1, Supplier<Double> controle0){
        this.servos = servos;this.controle1 = controle1;
        this.controle0 = controle0;this.isPressed = isPressed;}
    public static class Definir{
      private ServoSubsystem servos;
      private Supplier<Boolean> isPressed;
      private Supplier<Double> controle1,controle0;
      public Definir servos(ServoSubsystem servos){
        this.servos = servos;
        return this;}
      public Definir toggler(Supplier<Boolean> botao){
        this.isPressed = botao;
        return this;}
      public Definir controleUm(Supplier<Double> controleUm){
        this.controle1 = controleUm;
        return this;}
      public Definir controleZero(Supplier<Double> controleZero){
        this.controle0 = controleZero;
        return this;}
      public ServoCommand construir(){
        return new ServoCommand(this);}
    }
    @Override
    public void start() {}
    @Override
    public void stop() {}
    @Override
    public void periodic() {
        servos.toggle(isPressed);
        servos.setPosition(controle1, controle0);
    }
}