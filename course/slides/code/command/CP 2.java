public class CP {}

interface Command {
  public void execute();
  default public Command andThen(Command other) {
    return () -> { execute(); other.execute(); };
  }
}

class TVOnCommand implements Command {
  TV tv;
  public TVOnCommand(TV t) { tv = t; }
  public void execute() { tv.on(); }
}

class MacroCommand implements Command {
  Command[] commands;
  public MacroCommand(Command[] cs) { commands = cs; }
  public void execute() {
    for (int i = 0; i < commands.length; i++) {
      commands[i].execute();
    }
  }
}

class TV {
  public void on() { System.out.println("TV on"); }
  public void off() { System.out.println("TV off"); }
}
