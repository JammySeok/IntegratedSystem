package project.IntegratedSystem.mapper;


import project.IntegratedSystem.dto.EmployeeDTO;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.enums.EmployeePosition;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(EmployeeEntity entity) {
        if (entity == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPosition(entity.getPosition());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCreateAt(entity.getCreateAt());

        return dto;
    }

    public static EmployeeEntity toEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPosition(dto.getPosition() != null ? dto.getPosition() : EmployeePosition.STAFF);
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        return entity;
    }
}
