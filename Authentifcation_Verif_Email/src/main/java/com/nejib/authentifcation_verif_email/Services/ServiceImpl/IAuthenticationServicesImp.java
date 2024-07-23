package com.nejib.authentifcation_verif_email.Services.ServiceImpl;


import com.nejib.authentifcation_verif_email.Entites.AuthenticationResponse;
import com.nejib.authentifcation_verif_email.Entites.RefreshTokenRequest;
import com.nejib.authentifcation_verif_email.Entites.Role;
import com.nejib.authentifcation_verif_email.Entites.User;
import com.nejib.authentifcation_verif_email.Repository.IUserRepository;

import com.nejib.authentifcation_verif_email.Services.IServices.IAuthenticationServices;
import com.nejib.authentifcation_verif_email.Services.IServices.IJWTServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
@Slf4j
@RequiredArgsConstructor
public class IAuthenticationServicesImp implements IAuthenticationServices {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IJWTServices jwtServices;


    @Override
    public User RegisterUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        // Vérification si l'utilisateur est vérifié
        if (!user.isVerified()) {
            throw new RuntimeException("User is not verified");
        }
        var jwt = jwtServices.generateToken(user);
        var refreshToken = jwtServices.generateRefreshToken(new HashMap<>(), user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        authenticationResponse.setAccessToken(jwt);
        authenticationResponse.setRefreshToken(refreshToken);

        if (user.getRole() == Role.USER) {

            User userDetails = convertToUserDto(user);
            authenticationResponse.setUserDetails(userDetails);

        }
        return authenticationResponse;
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
        String userEmail = jwtServices.extractUsername(refreshToken.getRefreshToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        if(jwtServices.isTokenValid(refreshToken.getRefreshToken(), user)) {
            var jwt = jwtServices.generateToken(user);

            AuthenticationResponse authenticationResponse = new AuthenticationResponse();

            authenticationResponse.setAccessToken(jwt);
            authenticationResponse.setRefreshToken(refreshToken.getRefreshToken());
            return authenticationResponse;
        }
        return null;
    }

    @Override
    public HashMap<String, String> forgetPassword(String email) {
        return null;
    }

    @Override
    public HashMap<String, String> resetPassword(String passwordResetToken, String newPassword) {
        return null;
    }




    private User convertToUserDto(User user) {
        User dto = new User();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
       dto.setImage(user.getImage());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setNumeroTelephone(user.getNumeroTelephone());
         dto.setDateNaissance(user.getDateNaissance());
    return dto;
    }



}
