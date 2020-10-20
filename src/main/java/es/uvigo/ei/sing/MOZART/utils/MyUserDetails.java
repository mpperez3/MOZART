//package es.uvigo.ei.sing.chemSmile.utils;
//
//import es.uvigo.sing.guidelines.entities.User;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
//@Getter
//@Setter
//public class MyUserDetails extends org.springframework.security.core.userdetails.User {
//
//
//    User user;
//
//    public MyUserDetails(String username, String password, User user, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//        this.user = user;
//    }
//
//    public MyUserDetails(String username, String password, User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//        this.user = user;
//    }
//
//
//    public boolean isAdmin() {
//        return this.user.getRole().getAuthority().equalsIgnoreCase("ROLE_ADMIN");
//
//    }
//}
