package project.IntegratedSystem.mapper;


import project.IntegratedSystem.dto.BoardDTO;
import project.IntegratedSystem.entity.BoardEntity;
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
        if (login != null && login.getEmployee() != null) {
            dto.setPoster(login.getEmployee().getName());
        } else {
            dto.setPoster("작성자 없음");
        }

        return dto;
    }

    public static BoardEntity toEntity (BoardDTO dto) {
        if (dto == null) return null;

        BoardEntity entity = new BoardEntity();

        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setCreateAt(dto.getCreateAt());

        return entity;
    }
}
