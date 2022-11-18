package fr.caensup.td3.messagerie.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UIMessage {
  private String title;
  private String content;
  private String type;
  private String icon;

  public UIMessage(String title, String content) {
    this(title, content, "info", "info circle");
  }

  public UIMessage(String title, String content, String type) {
    this(title, content, type, "info circle");
  }

  public String getIcon() {
    return "ui " + icon + " icon";
  }

  public String getType() {
    return "ui " + type + " icon message";
  }
}
