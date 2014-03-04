package com.zephyrusapps.edu.emat.service.auth.user;

import com.google.appengine.api.datastore.*;
import com.zephyrusapps.edu.emat.service.auth.AppRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class GaeUserRepository implements UserRepository {

    private static Logger LOG = LoggerFactory.getLogger(GaeUserRepository.class);

    private static final String USER_TYPE = "EmatUser";
    private static final String USER_FIRSTNAME = "firstName";
    private static final String USER_LASTNAME = "lastName";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ENABLED = "enabled";
    private static final String USER_AUTHORITIES = "authorities";
    private static final String USER_SIGNIN_PROVIDER = "signInProvider";


    @Override
    public EmatUser findByEmail(String email) {
        Key key = KeyFactory.createKey(USER_TYPE, email);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        try {
            Entity user = datastore.get(key);

            String authorities = (String)user.getProperty(USER_AUTHORITIES);
            String firstName = (String)user.getProperty(USER_FIRSTNAME);
            String lastName = (String)user.getProperty(USER_LASTNAME);
            String password = (String)user.getProperty(USER_PASSWORD);
            String signInProvider = (String)user.getProperty(USER_SIGNIN_PROVIDER);
            boolean enabled = (Boolean)user.getProperty(USER_ENABLED);

            EmatUser ematUser = new EmatUser.Builder(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .password(password)
                    .signInProvider(signInProvider != null ? SocialMediaService.valueOf(signInProvider) : null)
                    .role(EmatRole.valueOf(authorities))
                    .enabled(enabled)
                    .build();

            return ematUser;

        } catch (EntityNotFoundException e) {
            LOG.debug(email + " not found in datastore");
            return null;
        }
    }

    @Override
    public EmatUser save(EmatUser newUser) {
        Key key = KeyFactory.createKey(USER_TYPE, newUser.getEmail());
        Entity user = new Entity(key);

        user.setProperty(USER_EMAIL, newUser.getEmail());
        user.setProperty(USER_FIRSTNAME, newUser.getFirstName());
        user.setProperty(USER_LASTNAME, newUser.getLastName());
        user.setProperty(USER_PASSWORD, newUser.getPassword());

        user.setUnindexedProperty(USER_SIGNIN_PROVIDER, newUser.getSignInProvider());
        user.setUnindexedProperty(USER_AUTHORITIES, newUser.getRole().toString());
        user.setUnindexedProperty(USER_ENABLED, newUser.isEnabled());

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(user);

        return newUser;
    }
}
