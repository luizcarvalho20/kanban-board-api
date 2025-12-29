package br.com.dio.board;

import br.com.dio.board.db.ConnectionFactory;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection con = ConnectionFactory.getConnection()) {
            System.out.println("Conectado no MySQL!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
