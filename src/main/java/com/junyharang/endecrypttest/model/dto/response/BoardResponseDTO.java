package com.junyharang.endecrypttest.model.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardResponseDTO {

    private Long id;
    private LocalDateTime createDateTime;
    private LocalDateTime modifiedDateTime;
    private String title;
    private String content;

    @Builder
    public BoardResponseDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
