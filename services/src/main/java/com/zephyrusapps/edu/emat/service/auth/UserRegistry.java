package com.zephyrusapps.edu.emat.service.auth;

public interface UserRegistry {
    GaeUser findUser(String userId);
    void registerUser(GaeUser newUser);
    void removeUser(String userId);
}
