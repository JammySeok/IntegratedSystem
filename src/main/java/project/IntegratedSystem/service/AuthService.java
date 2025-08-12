package project.IntegratedSystem.service;

import project.IntegratedSystem.dto.LoginDTO;
import project.IntegratedSystem.dto.SignupDTO;
import project.IntegratedSystem.dto.UserDTO;

import java.util.List;

public interface AuthService {

    UserDTO login(LoginDTO loginDTO);
    void signup(SignupDTO login);
}
