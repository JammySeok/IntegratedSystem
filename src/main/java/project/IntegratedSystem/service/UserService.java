package project.IntegratedSystem.service;

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
}
