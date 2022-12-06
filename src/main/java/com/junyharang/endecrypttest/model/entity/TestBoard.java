package com.junyharang.endecrypttest.model.entity;

import com.junyharang.endecrypttest.model.dto.request.BoardUpdateRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public TestBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * 게시글 수정 Method
     * @param boardUpdateRequestDTO Client 입력 값 제목 및 내용을 담은 DTO
     * @return Test TestEntity
     */

    public TestBoard update(BoardUpdateRequestDTO boardUpdateRequestDTO) {
        this.title = boardUpdateRequestDTO.getTitle();
        this.content = boardUpdateRequestDTO.getContent();

        return this;
    }
}
