package com.junyharang.endecrypttest.model.dto.request;

import com.junyharang.endecrypttest.model.entity.TestBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequestDTO {
    private String title;
    @Lob
    @Column(length = 65535, nullable = false)
    private String content;

    @Builder
    public TestBoard toEntity(String title, String content) {
        return TestBoard.builder()
                .title(title)
                .content(content)
                .build();
    }
}
