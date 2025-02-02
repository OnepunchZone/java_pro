BEGIN;

DROP TABLE IF EXISTS clients CASCADE;
CREATE TABLE clients (
    id bigserial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE
);

COMMIT;