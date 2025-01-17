package frc.robot.CommandResources;

@description(identifier = "Corpo de administraçao dos comandos")
public class CommandRunner extends Commands{
    private enum State{
        in, periodic, stop
    }
    private State state = State.in;
    public CommandRunner(){
    }
    @Override
    public void run() {
        switch (state) {
        case in:
        start();
        state = State.periodic;
            break;
        case periodic:
        periodic();
        if(finish())state = State.stop;
            break;
        case stop:
        stop();
            break;
        default:
            break;
       }
    } 
    public void begin(){
        Thread thread = new Thread(this);
        thread.start();
      }
    @Override
    public void periodic() {}
    @Override
    public void start() {}
    @Override
    public void stop() {}
    @Override
    public boolean finish() {return false;}
}

