package frc.robot.SubsystemResources;

import edu.wpi.first.wpilibj.Servo;

public class EnhancedServo extends Servo{
    //introducao aos subsistemas
    private final double limit;
    public final double maxAngle, minAngle, middleANgle;
    public final int[] referredJoystick;
    public EnhancedServo(int channel, double limit, int... referredJoystick) {
        super(channel);
        this.limit = limit;
        this.maxAngle = limit/2;
        this.minAngle = -limit/2;
        this.middleANgle = 0;
        this.referredJoystick = referredJoystick;
    }
    public EnhancedServo(Definir builder){
        super(builder.chanel);
        this.limit = builder.limit;
        this.maxAngle = builder.limit/2;
        this.minAngle = -builder.limit/2;
        this.middleANgle = 0;
        this.referredJoystick = builder.referredJoystick;
    }
    public static class Definir{
        private double limit;
        private int chanel;
        private int[] referredJoystick;
        public Definir limite(double limit){
            this.limit = limit;
            return this;
        }
        public Definir identificador(int chanel){
            this.chanel = chanel;
            return this;
        }
        public Definir joysticks(int... referredJoystick){
            this.referredJoystick = referredJoystick;
            return this;
        }
        public EnhancedServo construir(){
            return new EnhancedServo(this);
        }
    }
    public double angulo(){
        double angulo = get() - 0.47;
        angulo*=limit;
        return angulo;
      }
    @Override
    public void setAngle(double position){
        double posicao = position/limit;
        posicao += 0.47;
        setPosition(posicao);
      }
}
