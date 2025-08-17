package project.IntegratedSystem.service;

import project.IntegratedSystem.dto.login.LoginDTO;
import project.IntegratedSystem.dto.login.SignupDTO;
import project.IntegratedSystem.dto.login.UserDTO;

public interface AuthService {

//    UserDTO login(LoginDTO loginDTO);
    void signup(SignupDTO login);
}
