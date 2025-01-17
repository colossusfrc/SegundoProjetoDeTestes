package frc.robot.CommandResources;

@description(identifier = "Abstração das etapas (estados finitos de maquina)")
//ao inves de implementarmos duas interfaces, apenas extendemos a classe abstrata Commands.
//Serve o mesmo propósito que a interface anteriormente tinha: permitir a utilização de
//métodos indefinidos pelas classes filhas.
//Bônus: não precisamos implementar todos os métodos, pois eles já possuem alguma deifnição desde a classe Commands.
public abstract class Commands implements Runnable{
    public abstract void periodic();
    public abstract void start();
    public abstract void stop();
    public abstract boolean finish();
}
//essa abstração é necessária, porque as classes que 
//herdem os comandos podem utilizar essas funç~eos nas definições posteriores.
