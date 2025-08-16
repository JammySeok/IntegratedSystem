package project.IntegratedSystem.service;

import project.IntegratedSystem.dto.login.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getList();
    void getProfile();
    void update();
    void delete(Integer id);
}
