package project.IntegratedSystem.service;

import project.IntegratedSystem.dto.EmployeeDTO;
import project.IntegratedSystem.dto.login.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getList();
    void getProfile();
    void save(List<UserDTO> user);
    UserDTO detail(Integer id);
    void delete(Integer id);

    void updateUser(Integer id, UserDTO user);
}
