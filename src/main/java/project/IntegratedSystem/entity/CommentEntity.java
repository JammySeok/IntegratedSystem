package project.IntegratedSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id", nullable = false)
    private BoardEntity board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="login_id", nullable = false)
    private LoginEntity login;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
}