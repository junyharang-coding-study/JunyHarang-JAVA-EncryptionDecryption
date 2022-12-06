package com.junyharang.endecrypttest.service;

import com.junyharang.endecrypttest.common.constant.DefaultResponse;
import com.junyharang.endecrypttest.model.dto.request.BoardRequestDTO;
import com.junyharang.endecrypttest.model.dto.request.BoardUpdateRequestDTO;
import com.junyharang.endecrypttest.model.dto.response.BoardResponseDTO;
import com.junyharang.endecrypttest.model.entity.TestBoard;

import java.util.List;

public interface BoardService {
    DefaultResponse<List<TestBoard>> getBoardList();

    DefaultResponse<BoardResponseDTO> getBoard(Long id);

    DefaultResponse<Long> insertBoard(BoardRequestDTO boardRequestDTO);

    DefaultResponse<Long> updateBoard(Long id, BoardUpdateRequestDTO boardUpdateRequestDTO);

    DefaultResponse<Long> deleteBoard(Long id);
}
