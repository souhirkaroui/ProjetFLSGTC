package com.nejib.authentifcation_verif_email.Services.IServices;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserServices {
    UserDetailsService userDetailsService();
      void verifyUser(User user);
}
