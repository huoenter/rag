public class CommandPattern {}

interface Command {
  public void execute();
  default public Command andThen(Command c) {
    return () -> { execute(); c.execute(); };
  }
}

class TV {
  public void on() { System.out.println("TV on"); }
  public void off() { System.out.println("TV off"); }
}

class SimpleRemote {
  private Command c;
  public void setCommand(Command cc) { c = cc; }
  public void buttonPressed() { c.execute(); }
}
