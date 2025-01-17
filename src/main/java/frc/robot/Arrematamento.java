package frc.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.CommandResources.CommandRunner;
import frc.robot.Commands.AutonomousCommand;
import frc.robot.Commands.MotorCommand;
import frc.robot.Commands.ServoCommand;
import frc.robot.SubsystemResources.Camera;
import frc.robot.SubsystemResources.MotorSubsystem;
import frc.robot.SubsystemResources.ServoSubsystem;
import frc.robot.SubsystemResources.VelocityCalculator;

public class Arrematamento {
    public MotorSubsystem subsistemaMotor;
    public Camera camera;
    private MotorCommand motorCommand;
    private Joystick controle1;
    private AutonomousCommand auto;
    private ServoCommand servoCommand;
    private ServoSubsystem servos;
    public VelocityCalculator velocityCalculator;
    public Arrematamento(){
        this.camera = new Camera();
        this.controle1 = new Joystick(0);
        this.subsistemaMotor = new MotorSubsystem();
        this.servos = new ServoSubsystem();
        this.motorCommand = new MotorCommand.Definir()
                              .joystick(()->controle1.getRawAxis(2))
                              .motor(subsistemaMotor)
                              .build();
        this.auto = new AutonomousCommand.Definir()
                              .servos(servos)
                              .controle(controle1)
                              .construir();
        this.servoCommand = new ServoCommand.Definir()
                              .servos(servos)
                              .toggler(()->controle1.getRawButton(5))
                              .controleZero(()->controle1.getRawAxis(1))
                              .controleUm(()->controle1.getRawAxis(0))
                              .construir();
        this.velocityCalculator = new VelocityCalculator(()->subsistemaMotor.getDistance());
    }
    public AutonomousCommand getAutonomousCommand(){
        return auto;
    }
    public List<CommandRunner> testCommands(){
        List<CommandRunner> tests = new ArrayList<>();
        tests.addAll(Stream.of(servoCommand, motorCommand).collect(Collectors.toList()));
        return tests;
    }
}
