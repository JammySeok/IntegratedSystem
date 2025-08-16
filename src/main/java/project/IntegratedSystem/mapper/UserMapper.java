package project.IntegratedSystem.mapper;

import project.IntegratedSystem.dto.login.SignupDTO;
import project.IntegratedSystem.dto.login.UserDTO;
import project.IntegratedSystem.entity.LoginEntity;

public class UserMapper {

    public static UserDTO toDTO(LoginEntity entity) {
        if(entity == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setPassword(entity.getPassword());
        dto.setUserid(entity.getUserid());
        dto.setRole(entity.getRole());
        dto.setCreateAt(entity.getCreateAt());

        return dto;
    }

    public static LoginEntity toEntity(UserDTO dto) {
        if (dto == null) return null;

        LoginEntity entity = new LoginEntity();
        entity.setId(dto.getId());
        entity.setPassword(dto.getPassword());
        entity.setUserid(dto.getUserid());
        entity.setRole(dto.getRole() != null ? dto.getRole() : 2);
        entity.setCreateAt(dto.getCreateAt());
        return entity;
    }

    // SignupDTO -> LoginEntity
    public static LoginEntity toEntity(SignupDTO dto) {
        if (dto == null) return null;

        LoginEntity entity = new LoginEntity();
        entity.setUserid(dto.getUserid());
        entity.setPassword(dto.getPassword());
        entity.setRole(2);
        return entity;
    }
}
