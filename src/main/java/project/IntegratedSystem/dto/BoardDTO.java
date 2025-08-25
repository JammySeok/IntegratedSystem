package project.IntegratedSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private Integer id;
    private String title;
    private String content;
    private String writerName;
    private String writerId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
