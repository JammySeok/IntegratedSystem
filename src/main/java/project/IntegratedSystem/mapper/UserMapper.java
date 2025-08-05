package project.IntegratedSystem.mapper;

import project.IntegratedSystem.dto.LoginDTO;
import project.IntegratedSystem.entity.LoginEntity;

public class UserMapper {

    public static LoginDTO toDTO(LoginEntity entity) {
        if(entity == null) return null;

        LoginDTO dto = new LoginDTO();
        dto.setId(entity.getId());
        dto.setPassword(entity.getPassword());
        dto.setUsername(entity.getUsername());
        dto.setRole(entity.getRole());
        dto.setCreateAt(entity.getCreateAt());

        return dto;
    }

    public static LoginEntity toEntity(LoginDTO dto) {
        if (dto == null) return null;

        LoginEntity entity = new LoginEntity();
        entity.setId(dto.getId());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        entity.setRole(dto.getRole());
        entity.setCreateAt(dto.getCreateAt());
        return entity;
    }
}
