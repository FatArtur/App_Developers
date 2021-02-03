create table Developers(
 id serial PRIMARY KEY,
 name varchar(45) NOT NULL UNIQUE,
 account_id bigint NOT NULL UNIQUE,
 FOREIGN KEY (account_id) REFERENCES Accounts (id)
);

insert into developers(name, account_id)
values('Ivanov', 1);

insert into developers(name, account_id)
values('Petrov', 3);

insert into developers(name, account_id)
values('Sidorov', 2);

insert into developers(name, account_id)
values('Bebur', 5);

insert into developers(name, account_id)
values('Manko', 4);
