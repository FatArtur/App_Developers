create table Accounts(
 id serial PRIMARY KEY,
 name varchar(45) NOT NULL,
 AccountStatus varchar(45) NOT NULL
);

insert into accounts(name, AccountStatus)
values('Account_1', 'ACTIVE');

insert into accounts(name, AccountStatus)
values('Account_2', 'BANNED');

insert into accounts(name, AccountStatus)
values('Account_3', 'ACTIVE');

insert into accounts(name, AccountStatus)
values('Account_4', 'ACTIVE');

insert into accounts(name, AccountStatus)
values('Account_5', 'ACTIVE');

insert into accounts(name, AccountStatus)
values('Account_6', 'ACTIVE');
