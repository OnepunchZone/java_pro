create table transfers (
    id varchar(36) primary key,
    client_id varchar(10),
    target_client_id varchar(10),
    source_account varchar(12),
    target_account varchar(12),
    amount int,
    message varchar(255)
);

insert into transfers (id, client_id, target_client_id, source_account, target_account, amount, message) values
('bde76ffa-f133-4c23-9bca-03618b2a94b2', '1000000001', '1000000002', '000000000001', '000000000002', 100, 'Тестовый перевод'),
('32ebb2eb-ed35-4baa-b500-b7f6535e4c88', '1000000002', '1000000001', '000000000002', '000000000001', 50, 'Обратный тестовый перевод');

CREATE TABLE accounts (
    id varchar(36) primary key,
    account_number VARCHAR(12) NOT NULL,
    customer_id VARCHAR(10) NOT NULL,
    balance INT NOT NULL,
    is_blocked BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO accounts (id, account_number, customer_id, balance, is_blocked) VALUES
    ('11e76ffa-f133-4c23-9bca-03618b2a9411', '111111111111', 'CUSTOMER01', 5000, FALSE),
    ('22e76ffa-f133-4c23-9bca-03618b2a9422', '222222222222', 'CUSTOMER02', 2000, FALSE),
    ('33e76ffa-f133-4c23-9bca-03618b2a9433', '111111222222', 'CUSTOMER03', 1000, FALSE);
