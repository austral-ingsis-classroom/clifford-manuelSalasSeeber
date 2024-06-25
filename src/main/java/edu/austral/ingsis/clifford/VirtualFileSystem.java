package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.*;
import edu.austral.ingsis.clifford.node.Directory;

public class VirtualFileSystem {
  private final Directory root;
  private Directory currentDirectory;

  public VirtualFileSystem() {
    this.root = new Directory("");
    this.currentDirectory = root;
  }

  public Directory getRoot() {
    return root;
  }

  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  public void setCurrentDirectory(Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  public String execute(String command) {
    String[] commandSegments = command.split(" ");
    String commandType = commandSegments[0];

    switch (commandType) {
      case "touch" -> {
        String fileName = commandSegments[1];
        return new Touch(this, fileName).execute();
      }
      case "ls" -> {
        String order = commandSegments.length > 1 ? commandSegments[1].split("=")[1] : null;
        return new ListChildren(this, order).execute();
      }
      case "cd" -> {
        String directoryName = commandSegments[1];
        return new ChangeDirectory(this, directoryName).execute();
      }
      case "pwd" -> {
        return new ListPath(this).execute();
      }
      case "mkdir" -> {
        String directoryName = commandSegments[1];
        return new MakeDirectory(this, directoryName).execute();
      }
      case "rm" -> {
        String name;
        boolean recursive = false;
        if (commandSegments.length > 2) {
          name = commandSegments[2];
          recursive = commandSegments[1].equals("--recursive");
        } else {
          name = commandSegments[1];
        }
        return new Remove(this, name, recursive).execute();
      }
      default -> {
        return "Command: " + commandType + "not found";
      }
    }
  }
}
