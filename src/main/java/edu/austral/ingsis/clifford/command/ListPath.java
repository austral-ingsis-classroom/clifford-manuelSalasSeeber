package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.VirtualFileSystem;
import edu.austral.ingsis.clifford.node.Node;

public class ListPath implements Command {
  private final VirtualFileSystem fileSystem;

  public ListPath(VirtualFileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute() {
    StringBuilder path = new StringBuilder();
    Node current = fileSystem.getCurrentDirectory();
    while (current != null) {
      path.insert(0, current.getName());
      if (current.getParent() != null) {
        path.insert(0, "/");
      }
      current = current.getParent();
    }

    return path.toString();
  }
}
