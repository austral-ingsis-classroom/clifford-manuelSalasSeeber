package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.VirtualFileSystem;
import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.Node;
import java.util.List;

public class Remove implements Command {
  private final VirtualFileSystem fileSystem;
  private final String name;
  private final boolean recursive;

  public Remove(VirtualFileSystem fileSystem, String name, boolean recursive) {
    this.fileSystem = fileSystem;
    this.name = name;
    this.recursive = recursive;
  }

  @Override
  public String execute() {
    List<Node> children = fileSystem.getCurrentDirectory().getChildren();
    for (Node child : children) {
      if (child.getName().equals(name)) {
        if (child instanceof Directory) {
          if (!recursive) {
            return "cannot remove '" + name + "', is a directory";
          } else {
            fileSystem.getCurrentDirectory().removeChild(child);
            return "'" + name + "' removed";
          }
        } else {
          fileSystem.getCurrentDirectory().removeChild(child);
          return "'" + name + "' removed";
        }
      }
    }
    return "file or directory not found";
  }
}
