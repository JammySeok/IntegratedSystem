package project.IntegratedSystem.mapper;


import project.IntegratedSystem.dto.BoardDTO;
import project.IntegratedSystem.entity.BoardEntity;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.entity.LoginEntity;

public class BoardMapper {

    public static BoardDTO toDTO (BoardEntity entity) {
        if (entity == null) return null;

        BoardDTO dto = new BoardDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCreateAt(entity.getCreateAt());
        dto.setUpdateAt(entity.getUpdateAt());

        LoginEntity login = entity.getLogin();
        EmployeeEntity employee = login.getEmployee();

        dto.setWriterId(login.getUserid());
        dto.setWriterName(employee.getName());

        return dto;
    }

    public static BoardEntity toEntity (BoardDTO dto) {
        if (dto == null) return null;

        BoardEntity entity = new BoardEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());

        return entity;
    }
}
