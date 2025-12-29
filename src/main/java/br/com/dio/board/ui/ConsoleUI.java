package br.com.dio.board.ui;

import br.com.dio.board.dao.ColumnDao;
import br.com.dio.board.dao.ReportDao;
import br.com.dio.board.db.DbBootstrap;
import br.com.dio.board.domain.Board;
import br.com.dio.board.domain.BoardColumn;
import br.com.dio.board.domain.Card;
import br.com.dio.board.service.BoardService;
import br.com.dio.board.service.CardService;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUI {

    private final BoardService boardService = new BoardService();
    private final CardService cardService = new CardService();
    private final ColumnDao columnDao = new ColumnDao();
    private final ReportDao reportDao = new ReportDao();

    public void start() {
        DbBootstrap.ensureSchema();

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Criar novo board");
            System.out.println("2 - Selecionar board");
            System.out.println("3 - Excluir boards");
            System.out.println("0 - Sair");

            String op = Input.text("Escolha: ").trim();
            try {
                switch (op) {
                    case "1" -> createBoardFlow();
                    case "2" -> selectBoardFlow();
                    case "3" -> deleteBoardFlow();
                    case "0" -> System.exit(0);
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }
    }

    private void createBoardFlow() {
        String name = Input.text("Nome do board: ");

        String initial = Input.text("Nome da coluna INICIAL: ");
        int pendingCount = Input.intVal("Quantas colunas PENDENTES? (mínimo 1): ");
        if (pendingCount < 1) throw new RuntimeException("Precisa ter pelo menos 1 coluna PENDENTE.");

        List<String> pendings = new ArrayList<>();
        for (int i = 1; i <= pendingCount; i++) {
            pendings.add(Input.text("Nome da PENDENTE #" + i + ": "));
        }

        String fin = Input.text("Nome da coluna FINAL: ");
        String cancel = Input.text("Nome da coluna CANCELADOS: ");

        Board board = boardService.createBoard(name, initial, pendings, fin, cancel);
        System.out.println("Board criado com sucesso! ID: " + board.id());
    }

    private void selectBoardFlow() {
        List<Board> boards = boardService.listBoards();
        if (boards.isEmpty()) {
            System.out.println("Nenhum board encontrado.");
            return;
        }

        System.out.println("\nBoards disponíveis:");
        boards.forEach(b -> System.out.println("[" + b.id() + "] " + b.name()));

        long boardId = Input.longVal("Digite o ID do board: ");
        if (!boardService.boardExists(boardId)) {
            System.out.println("Board não existe.");
            return;
        }

        boardMenu(boardId);
    }

    private void boardMenu(long boardId) {
        while (true) {
            System.out.println("\n=== BOARD " + boardId + " ===");
            System.out.println("1 - Visualizar colunas");
            System.out.println("2 - Listar cards");
            System.out.println("3 - Criar card");
            System.out.println("4 - Mover card para próxima coluna");
            System.out.println("5 - Cancelar card");
            System.out.println("6 - Bloquear card");
            System.out.println("7 - Desbloquear card");
            System.out.println("8 - Relatório: Tempo por coluna (Opcional 2)");
            System.out.println("9 - Relatório: Bloqueios (Opcional 3)");
            System.out.println("0 - Fechar board");

            String op = Input.text("Escolha: ").trim();

            try {
                switch (op) {
                    case "1" -> showColumns(boardId);
                    case "2" -> listCards(boardId);
                    case "3" -> createCard(boardId);
                    case "4" -> moveCard();
                    case "5" -> cancelCard();
                    case "6" -> blockCard();
                    case "7" -> unblockCard();
                    case "8" -> reportDao.printTimePerColumn(boardId);
                    case "9" -> reportDao.printBlockedIntervals(boardId);
                    case "0" -> { return; }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }
    }

    private void showColumns(long boardId) {
        List<BoardColumn> cols = columnDao.findByBoard(boardId);
        System.out.println("\nColunas do board:");
        cols.forEach(c -> System.out.printf("(%d) [%d] %s - %s%n", c.position(), c.id(), c.name(), c.type()));
    }

    private void listCards(long boardId) {
        List<Card> cards = cardService.listCards(boardId);
        if (cards.isEmpty()) {
            System.out.println("Nenhum card neste board.");
            return;
        }
        System.out.println("\nCards:");
        cards.forEach(c -> System.out.printf(
                "#%d | coluna=%d | bloqueado=%s | %s%n",
                c.id(), c.columnId(), c.blocked() ? "SIM" : "NÃO", c.title()
        ));
    }

    private void createCard(long boardId) {
        String title = Input.text("Título: ");
        String desc = Input.text("Descrição: ");
        long id = cardService.createCard(boardId, title, desc);
        System.out.println("Card criado! ID: " + id);
    }

    private void moveCard() {
        long cardId = Input.longVal("ID do card: ");
        cardService.moveNext(cardId);
        System.out.println("Card movido para a próxima coluna!");
    }

    private void cancelCard() {
        long cardId = Input.longVal("ID do card: ");
        cardService.cancelCard(cardId);
        System.out.println("Card cancelado!");
    }

    private void blockCard() {
        long cardId = Input.longVal("ID do card: ");
        String reason = Input.text("Motivo do bloqueio: ");
        cardService.blockCard(cardId, reason);
        System.out.println("Card bloqueado!");
    }

    private void unblockCard() {
        long cardId = Input.longVal("ID do card: ");
        String reason = Input.text("Motivo do desbloqueio: ");
        cardService.unblockCard(cardId, reason);
        System.out.println("Card desbloqueado!");
    }

    private void deleteBoardFlow() {
        List<Board> boards = boardService.listBoards();
        if (boards.isEmpty()) {
            System.out.println("Nenhum board para excluir.");
            return;
        }
        boards.forEach(b -> System.out.println("[" + b.id() + "] " + b.name()));
        long id = Input.longVal("Digite o ID do board para excluir: ");
        boardService.deleteBoard(id);
        System.out.println("Board excluído com sucesso!");
    }
}
