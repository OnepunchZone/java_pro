create table cats (
    id bigserial primary key,
    name varchar(255),
    color varchar(255)
);

insert into cats (name, color) values
    ('Ричард','чёрный'),
    ('Мира','табби-ред'),
    ('Вениамин','серый'),
    ('Хина','белый');