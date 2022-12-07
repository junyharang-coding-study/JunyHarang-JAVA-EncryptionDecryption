package com.junyharang.endecrypttest.common.mapper;

import com.junyharang.endecrypttest.model.dto.request.BoardRequestDTO;
import com.junyharang.endecrypttest.model.entity.TestBoard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestBoardMapper extends GenericMapper<BoardRequestDTO, TestBoard>{}
