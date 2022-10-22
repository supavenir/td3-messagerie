package fr.caensup.td3.messagerie.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestMessage {
  private String type;
  private String content;
}
