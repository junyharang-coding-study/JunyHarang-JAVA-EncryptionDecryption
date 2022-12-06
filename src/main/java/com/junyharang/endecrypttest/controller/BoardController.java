package com.junyharang.endecrypttest.controller;

import com.junyharang.endecrypttest.common.constant.DefaultResponse;
import com.junyharang.endecrypttest.model.dto.request.BoardRequestDTO;
import com.junyharang.endecrypttest.model.dto.response.BoardResponseDTO;
import com.junyharang.endecrypttest.model.entity.TestBoard;
import com.junyharang.endecrypttest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseEntity<DefaultResponse<Long>> insertBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        return new ResponseEntity<>(boardService.insertBoard(boardRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/api/board")
    public ResponseEntity<DefaultResponse<List<TestBoard>>> getBoardList() {
        return new ResponseEntity<>(boardService.getBoardList(), HttpStatus.OK);
    }

    @GetMapping("/api/board/{id}")
    public ResponseEntity<DefaultResponse<BoardResponseDTO>> getBoard(@PathVariable("id") Long id) {
        return new ResponseEntity<>(boardService.getBoard(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseEntity<DefaultResponse<Long>> deleteBoard(@PathVariable("id") Long id) {
        return new ResponseEntity<>(boardService.deleteBoard(id), HttpStatus.OK);
    }
}
