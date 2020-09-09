package org.messenger.Service;

import org.messenger.model.Authority;
import org.messenger.model.GoogleUserInfo;
import org.messenger.model.SecurityUser;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;


@Service
public class CustomOidcUserService extends OidcUserService  {


    @PersistenceContext
    EntityManager em;
    @Transactional
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    public OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());


        if (em.createQuery("SELECT u from SecurityUser u WHERE u.email=:mail",SecurityUser.class).setParameter("mail",googleUserInfo.getEmail()).getResultList()!=null) {
            SecurityUser securityUser = new SecurityUser();
            securityUser.setEmail(googleUserInfo.getEmail());
            securityUser.setName(googleUserInfo.getName());
            securityUser.setLoginDate(LocalDateTime.now());
            securityUser.setRole(em.createQuery("select a FROM Authority a WHERE a.roleName='ADMIN'",Authority.class).getSingleResult().getRoleName());

            em.persist(securityUser);
        }

        return oidcUser;
    }
}
