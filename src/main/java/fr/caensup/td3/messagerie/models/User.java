package fr.caensup.td3.messagerie.models;

import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor()
@RequiredArgsConstructor()
@Entity
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(length = 40, nullable = false)
  @NonNull
  private String firstname;

  @Column(length = 40, nullable = false)
  @NonNull
  private String lastname;

  @Column(length = 255, nullable = false)
  @NonNull
  private String email;

  @Column(nullable = false, length = 255)
  @NonNull
  private String password;

  @Column(nullable = true)
  private boolean suspended = false;

  @JsonIgnore
  @ManyToOne
  private Organization organization;

  @JsonIgnore
  @ManyToMany(mappedBy = "users")
  private List<Group> groupes;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
