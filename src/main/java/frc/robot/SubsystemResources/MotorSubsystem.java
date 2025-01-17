package frc.robot.SubsystemResources;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder.Type;

public class MotorSubsystem extends Subsystem{
    public CANSparkMax motor;
    private double maxPower;
    private RelativeEncoder encoder;
    public MotorSubsystem(){
        super();
        this.motor = new CANSparkMax(2, MotorType.kBrushed);
        motor.restoreFactoryDefaults();
        this.maxPower = .4;
        this.encoder = motor.getEncoder(Type.kQuadrature, 28);
    }
    public void setPower(double potencia){motor.set(potencia);}
    public double getMaxPower(){return maxPower;}
    public double getDistance(){return encoder.getPosition();}
}
