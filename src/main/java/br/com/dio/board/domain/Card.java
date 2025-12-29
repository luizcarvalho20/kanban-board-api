package br.com.dio.board.domain;

import java.time.LocalDateTime;

public record Card(Long id, Long boardId, Long columnId, String title, String description,
                   boolean blocked, LocalDateTime createdAt) {}
