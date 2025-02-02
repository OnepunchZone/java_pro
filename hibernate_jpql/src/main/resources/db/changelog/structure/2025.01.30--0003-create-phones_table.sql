BEGIN;

DROP TABLE IF EXISTS phones CASCADE;
CREATE TABLE phones (
    id bigserial PRIMARY KEY,
    number VARCHAR(255) NOT NULL,
    client_id BIGINT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

COMMIT;