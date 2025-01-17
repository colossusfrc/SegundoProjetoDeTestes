package frc.robot.SubsystemResources;

import java.util.function.Supplier;

import frc.robot.Commands.description;
public class ServoSubsystem {
    private InnerRobot definedFunction;
    private boolean toggler = true, debouncer = false;
    private final EnhancedServo servoBase, servoY;
    public ServoSubsystem(){
        this.servoBase = new EnhancedServo.Definir()
                              .identificador(1)
                              .limite(135)
                              .joysticks(1, 2)
                              .construir();
        this.servoY = new EnhancedServo.Definir()
                              .identificador(0)
                              .limite(135)
                              .joysticks(3, 4)
                              .construir();}
    public void setBaseAngle(double angle){
        servoBase.setAngle(angle);
    }
    public void setYAngle(double angle){
        servoY.setAngle(angle);
    }
    public double[] getBaseAngle(){
        return new double[]{servoBase.minAngle, servoBase.middleANgle, servoBase.maxAngle};
    }
    public double[] getYAngle(){
        return new double[]{servoY.minAngle, servoY.middleANgle, servoY.maxAngle};
    }
    @SafeVarargs
    public final void setPosition(Supplier<Double>... controles){
        servoBase.setPosition(servoPower(controles[0].get(), true, servoBase));
        servoY.setPosition(servoPower(controles[1].get(), true, servoY));
    }
    public void toggle(Supplier<Boolean> isPressed){
        boolean pressed = isPressed.get();
                if(pressed&&!debouncer){
                  toggler = !toggler;
                }
                debouncer = pressed;
      }
   /* Motivos para usar Lambda:
   * 1. você pode criar quantas interfaces quiser
   * 2. cada funcao pode ser modificada em condicoes abstratas
   * 3. a JVM nao consome memória para todas as possibilidades, apenas sobrepõe a mesma função
   * 4. leitura mais fluida e dinâmica, 
   * sem compromenter outras implementações da mesma função em outros lugares do programa
   */
    @description(identifier = "Alternativa com lambda e interfaces para o controle do servo")
  private double servoPower(double controle, boolean isInverted, EnhancedServo servo){
    double p = (isInverted)?-controle:controle;
    definedFunction = (p>0)?potencia->(potencia/5)+.47:(p<0)?potencia->(potencia/5)+.47:potencia->.47;
    return toggler?definedFunction.getPower(p):servo.getPosition();
  }
}
//interface funcional para o servo
@FunctionalInterface
interface InnerRobot {
  double getPower(double power);
}

