BEGIN;

DROP TABLE IF EXISTS addresses CASCADE;
CREATE TABLE addresses (
    id bigserial PRIMARY KEY,
    street VARCHAR(255) NOT NULL
);

COMMIT;