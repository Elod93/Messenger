package org.messenger.Service;

import org.messenger.model.Authority;
import org.messenger.model.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    EntityManager em;
    @Override
    public UserDetails loadUserByUsername(String name) {
       try {
           return (em.createQuery("SELECT u FROM SecurityUser u WHERE u.name= :name",SecurityUser.class).setParameter("name",name).getSingleResult());
       }catch (Exception e){
           e.getStackTrace();
       }
       return null;
    }
    @Transactional
    public void createUser(SecurityUser securityUser){
        em.persist(securityUser);

    }
/*
    void updateUser(UserDetails var1);

    void deleteUser(String var1);

    void changePassword(String var1, String var2);

 */

    public boolean userExists(String name) {
        long usersWithUsernameCount = em.createQuery("select count(u) from SecurityUser u where u.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return usersWithUsernameCount > 0;
    }
    public Authority getUserAuthority(){
     return em.createQuery("select a FROM Authority a WHERE a.roleName='ADMIN'",Authority.class).getSingleResult();
    }

}
