package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.VirtualFileSystem;
import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.Node;

public class ChangeDirectory implements Command {
  private final VirtualFileSystem fileSystem;
  private final String directoryName;

  public ChangeDirectory(VirtualFileSystem fileSystem, String directoryName) {
    this.fileSystem = fileSystem;
    this.directoryName = directoryName;
  }

  @Override
  public String execute() {
    Directory currentDirectory = fileSystem.getCurrentDirectory();

    if (directoryName.equals("/")) {
      fileSystem.setCurrentDirectory(fileSystem.getRoot());
      return "moved to directory '/'";
    }

    String[] path = directoryName.split("/");

    if (path[0].equals("..")) {
      if (currentDirectory.getParent() != null) {
        fileSystem.setCurrentDirectory(currentDirectory.getParent());
        if (currentDirectory.getParent() == fileSystem.getRoot()) {
          return "moved to directory '/'";
        }
        return "Moved to directory '" + currentDirectory.getParent().getName() + "'";
      } else {
        return "moved to directory '/'";
      }
    } else if (path[0].equals(".")) {
      return "Moved to directory: '" + currentDirectory.getName() + "'";
    } else {
      for (String name : path) {
        Directory newDirectory = getChildDirectory(currentDirectory, name);
        if (newDirectory == null) {
          return "'" + directoryName + "' directory does not exist";
        }
        fileSystem.setCurrentDirectory(newDirectory);
        currentDirectory = newDirectory;
      }
      return "moved to directory '" + currentDirectory.getName() + "'";
    }
  }

  private Directory getChildDirectory(Directory parent, String name) {
    for (Node child : parent.getChildren()) {
      if (child instanceof Directory && child.getName().equals(name)) {
        return (Directory) child;
      }
    }
    return null;
  }
}
