package com.nejib.authentifcation_verif_email.Repository;


import com.nejib.authentifcation_verif_email.Entites.Role;
import com.nejib.authentifcation_verif_email.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
    Optional<User> findByPasswordResetToken(String passwordResetToken);


}
