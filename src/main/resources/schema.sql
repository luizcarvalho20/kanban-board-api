CREATE TABLE IF NOT EXISTS boards (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS columns (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL,
    column_order INT NOT NULL,
    column_type VARCHAR(20) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT fk_columns_board
    FOREIGN KEY (board_id) REFERENCES boards(id)
    );

CREATE TABLE IF NOT EXISTS cards (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    blocked BOOLEAN NOT NULL DEFAULT FALSE,
    column_id BIGINT NOT NULL,
    CONSTRAINT fk_cards_column
    FOREIGN KEY (column_id) REFERENCES columns(id)
    );

CREATE TABLE IF NOT EXISTS card_block_history (
                                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                  card_id BIGINT NOT NULL,
                                                  blocked_at TIMESTAMP NOT NULL,
                                                  unblocked_at TIMESTAMP,
                                                  block_reason VARCHAR(255) NOT NULL,
    unblock_reason VARCHAR(255),
    CONSTRAINT fk_block_card
    FOREIGN KEY (card_id) REFERENCES cards(id)
    );
