package br.com.dio.board.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest {

    @Test
    @DisplayName("Deve instanciar o serviço de Board corretamente")
    void shouldInstantiateBoardService() {
        BoardService service = new BoardService();
        assertNotNull(service, "O serviço não deveria ser nulo");
    }
}
