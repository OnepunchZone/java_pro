INSERT INTO phones (number, client_id) VALUES
('343-34-4545-88', (SELECT id FROM clients WHERE name = 'Ваня')),
('666-13-1313-13', (SELECT id FROM clients WHERE name = 'Таня')),
('111-22-7777-44', (SELECT id FROM clients WHERE name = 'Саня'));
