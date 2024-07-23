package com.nejib.authentifcation_verif_email.Services.IServices;

 ;
import com.nejib.authentifcation_verif_email.Entites.AuthenticationResponse;
import com.nejib.authentifcation_verif_email.Entites.RefreshTokenRequest;
 import com.nejib.authentifcation_verif_email.Entites.User;

 import java.util.HashMap;

public interface IAuthenticationServices {
User RegisterUser(User user);
    AuthenticationResponse login(String email, String password);
    AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
    HashMap<String,String> forgetPassword(String email);
    HashMap<String,String> resetPassword(String passwordResetToken, String newPassword);

}
