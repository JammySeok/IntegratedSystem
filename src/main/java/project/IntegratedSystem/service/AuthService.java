package project.IntegratedSystem.service;

import project.IntegratedSystem.dto.user.SignupDTO;

public interface AuthService {

//    UserDTO login(LoginDTO loginDTO);
    void signup(SignupDTO login);
}
