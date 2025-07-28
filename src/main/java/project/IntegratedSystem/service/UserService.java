package project.IntegratedSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.controller.UserController;
import project.IntegratedSystem.entity.UserEntity;
import project.IntegratedSystem.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;

    public List<UserEntity> getList() {
        return userRepository.findAll();
    }

    @Transactional
    public void addList(String id, String password, String name) {
        if (userRepository.findByUserid(id) != null) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }

        UserEntity user = new UserEntity();
        user.setUserid(id);
        user.setPassword(password);
        user.setName(name);
        user.setPosition(1);
        user.setRole("user");

        userRepository.save(user);
    }

    public boolean checkLogin(String id, String password) {
        UserEntity user = userRepository.findByUserid(id);

        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

}
