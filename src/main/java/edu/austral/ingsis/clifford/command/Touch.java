package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.VirtualFileSystem;
import edu.austral.ingsis.clifford.node.File;

public class Touch implements Command {
  private final VirtualFileSystem fileSystem;
  private final String name;

  public Touch(VirtualFileSystem fileSystem, String name) {
    this.fileSystem = fileSystem;
    this.name = name;
  }

  @Override
  public String execute() {
    new File(name, fileSystem.getCurrentDirectory());
    return "'" + name + "' file created";
  }
}
