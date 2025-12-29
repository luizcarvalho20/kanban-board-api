package br.com.dio.board.domain;

public record BoardColumn(Long id, Long boardId, String name, int position, ColumnType type) {}
