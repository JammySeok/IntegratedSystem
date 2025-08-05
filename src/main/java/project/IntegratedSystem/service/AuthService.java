package project.IntegratedSystem.service;

import project.IntegratedSystem.dto.LoginDTO;

import java.util.List;

public interface AuthService {
    LoginDTO login(LoginDTO loginDTO);
    void signup();
}
