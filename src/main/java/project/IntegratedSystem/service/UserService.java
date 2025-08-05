package project.IntegratedSystem.service;

import project.IntegratedSystem.dto.LoginDTO;

import java.util.List;

public interface UserService {

    List<LoginDTO> getList();
    void getProfile();
    void update();
    void delete();
}
