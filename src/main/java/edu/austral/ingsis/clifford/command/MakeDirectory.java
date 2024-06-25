package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.VirtualFileSystem;
import edu.austral.ingsis.clifford.node.Directory;

public class MakeDirectory implements Command {
  private final VirtualFileSystem fileSystem;
  private final String name;

  public MakeDirectory(VirtualFileSystem fileSystem, String name) {
    this.fileSystem = fileSystem;
    this.name = name;
  }

  @Override
  public String execute() {
    new Directory(name, fileSystem.getCurrentDirectory());
    return "'" + name + "' directory created";
  }
}
