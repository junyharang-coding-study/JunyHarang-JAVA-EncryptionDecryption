package com.junyharang.endecrypttest.service.Impl;

import com.junyharang.endecrypttest.common.constant.DefaultResponse;
import com.junyharang.endecrypttest.common.constant.DefaultResponseMessage;
import com.junyharang.endecrypttest.common.mapper.TestBoardMapper;
import com.junyharang.endecrypttest.common.util.endecryption.DataEnDecryption;
import com.junyharang.endecrypttest.model.dto.request.BoardRequestDTO;
import com.junyharang.endecrypttest.model.dto.request.BoardUpdateRequestDTO;
import com.junyharang.endecrypttest.model.dto.response.BoardResponseDTO;
import com.junyharang.endecrypttest.model.entity.TestBoard;
import com.junyharang.endecrypttest.repository.BoardRepository;
import com.junyharang.endecrypttest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;
    private final TestBoardMapper testBoardMapper;

    @Override
    @Transactional
    public DefaultResponse<Long> insertBoard(BoardRequestDTO boardRequestDTO) {

        if (boardRequestDTO == null) {
            return DefaultResponse.response(DefaultResponseMessage.BAD_REQUEST,
                    DefaultResponseMessage.KOREAN_400,
                    DefaultResponseMessage.ENGLISH_400);
        }

        String cipherKey = DataEnDecryption.base64Encoder(boardRequestDTO.getTitle());

        boardRequestDTO.setContent(DataEnDecryption.dataEnDecrypt(cipherKey, boardRequestDTO.getContent(), 1));

        TestBoard testBoard = testBoardMapper.toEntity(boardRequestDTO);

        Long saveId = boardRepository.save(testBoard).getId();

        return DefaultResponse.response(DefaultResponseMessage.CREATE_SUCCESS,
                DefaultResponseMessage.KOREAN_201,
                DefaultResponseMessage.ENGLISH_201,
                saveId);
    }

    @Override
    @Transactional(readOnly = true)
    public DefaultResponse<List<TestBoard>> getBoardList() {
        return DefaultResponse.response(
                DefaultResponseMessage.OK,
                DefaultResponseMessage.KOREAN_200,
                DefaultResponseMessage.ENGLISH_200,
                boardRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public DefaultResponse<BoardResponseDTO> getBoard(Long id) {

        Optional<TestBoard> boardById = findValueByID(id);

        if (boardById.isEmpty()) {
            return DefaultResponse.response(
                    DefaultResponseMessage.NOT_FOUND,
                    DefaultResponseMessage.KOREAN_404,
                    DefaultResponseMessage.ENGLISH_404);
        }

        String cipherKey = DataEnDecryption.base64Encoder(boardById.get().getTitle());

        return DefaultResponse.response(
                DefaultResponseMessage.OK,
                DefaultResponseMessage.KOREAN_200,
                DefaultResponseMessage.ENGLISH_200,

                BoardResponseDTO.builder()
                        .id(boardById.get().getId())
                        .title(boardById.get().getTitle())
                        .content(DataEnDecryption.dataEnDecrypt(cipherKey, boardById.get().getContent(), 2))
                        .build());
    }

    @Override
    @Transactional
    public DefaultResponse<Long> updateBoard(Long id, BoardUpdateRequestDTO boardUpdateRequestDTO) {
        Optional<TestBoard> boardById = findValueByID(id);

        if (boardById.isEmpty()) {
            return DefaultResponse.response(
                    DefaultResponseMessage.NOT_FOUND,
                    DefaultResponseMessage.KOREAN_404,
                    DefaultResponseMessage.ENGLISH_404);
        }

        TestBoard updateTestBoard = boardById.get().update(boardUpdateRequestDTO);

        return DefaultResponse.response(
                DefaultResponseMessage.CREATE_SUCCESS,
                DefaultResponseMessage.KOREAN_201,
                DefaultResponseMessage.ENGLISH_201,
                boardRepository.save(updateTestBoard).getId());

    }

    @Override
    @Transactional
    public DefaultResponse<Long> deleteBoard(Long id) {

        Optional<TestBoard> boardById = findValueByID(id);

        if (boardById.isEmpty()) {
            return DefaultResponse.response(
                    DefaultResponseMessage.NOT_FOUND,
                    DefaultResponseMessage.KOREAN_404,
                    DefaultResponseMessage.ENGLISH_404);
        }

        boardRepository.delete(boardById.get());

        return DefaultResponse.response(
                DefaultResponseMessage.OK,
                DefaultResponseMessage.KOREAN_200,
                DefaultResponseMessage.ENGLISH_200,
                id);
    }

    @Transactional(readOnly = true)
    public Optional<TestBoard> findValueByID(Long id) {
        return boardRepository.findById(id);
    }
}
