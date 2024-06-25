package edu.austral.ingsis.clifford.node;

import java.util.ArrayList;
import java.util.List;

public class Directory implements Node {
  private final String name;
  private final Directory parent;
  private final List<Node> children;

  public Directory(String name, Directory parent) {
    this.name = name;
    parent.addChild(this);
    this.parent = parent;
    this.children = new ArrayList<>();
  }

  public Directory(String name) {
    this.name = name;
    this.parent = null;
    this.children = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Directory getParent() {
    return parent;
  }

  public List<Node> getChildren() {
    return children;
  }

  public void addChild(Node child) {
    children.add(child);
  }

  public void removeChild(Node child) {
    children.remove(child);
  }
}
