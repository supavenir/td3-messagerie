package fr.caensup.td3.messagerie.pojo;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import lombok.Data;
import lombok.NonNull;

@Data
public class ActiveUser {
  @NonNull
  private Authentication authentication;

  public boolean isConnected() {
    return !(authentication instanceof AnonymousAuthenticationToken);
  }

  public String getName() {
    return authentication.getName();
  }
}
