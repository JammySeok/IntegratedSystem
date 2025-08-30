package project.IntegratedSystem.mapper;

import project.IntegratedSystem.dto.user.SignupDTO;
import project.IntegratedSystem.dto.user.UserDTO;
import project.IntegratedSystem.entity.UserEntity;
import project.IntegratedSystem.enums.UserRole;

public class UserMapper {

    public static UserDTO toDTO(UserEntity entity) {
        if(entity == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setPassword(entity.getPassword());
        dto.setUserid(entity.getUserid());
        dto.setRole(entity.getRole());
        dto.setCreateAt(entity.getCreateAt());

        return dto;
    }

    public static UserEntity toEntity(UserDTO dto) {
        if (dto == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setPassword(dto.getPassword());
        entity.setUserid(dto.getUserid());
        entity.setRole(dto.getRole() != null ? dto.getRole() : UserRole.GUEST);
        entity.setCreateAt(dto.getCreateAt());
        return entity;
    }

    // SignupDTO -> LoginEntity
    public static UserEntity toEntity(SignupDTO dto) {
        if (dto == null) return null;

        UserEntity entity = new UserEntity();
        entity.setUserid(dto.getUserid());
        entity.setPassword(dto.getPassword());
        entity.setRole(UserRole.GUEST);
        return entity;
    }
}
