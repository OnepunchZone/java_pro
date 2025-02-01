INSERT INTO clients (name, address_id) VALUES
('Саня', (SELECT id FROM addresses WHERE street = 'г.Сайлент-хилл, ул.Вязова, д.1 кв.13')),
('Ваня', (SELECT id FROM addresses WHERE street = 'г.Спрингфилд, ул.Симпсона, д.7 кв.--')),
('Таня', (SELECT id FROM addresses WHERE street = 'г.Ракун-сити, ул.Зомбакова, д.33 кв.33'));