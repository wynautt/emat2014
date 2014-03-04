package com.zephyrusapps.edu.emat.service.auth.user;

public interface UserRepository  {
    public EmatUser findByEmail(String email);
    public EmatUser save(EmatUser registered);
}
