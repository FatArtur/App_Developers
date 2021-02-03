create table developer_skills(
 id serial PRIMARY KEY,
 developer_id bigint NOT NULL REFERENCES Developers (id),
 skill_id bigint NOT NULL REFERENCES Skills (id)
);

insert into developer_skills(developer_id, skill_id)
values(1, 1);
insert into developer_skills(developer_id, skill_id)
values(1, 2);
insert into developer_skills(developer_id, skill_id)
values(1, 3);
insert into developer_skills(developer_id, skill_id)
values(1, 5);

insert into developer_skills(developer_id, skill_id)
values(2, 2);
insert into developer_skills(developer_id, skill_id)
values(2, 6);
insert into developer_skills(developer_id, skill_id)
values(2, 3);
insert into developer_skills(developer_id, skill_id)
values(2, 5);

insert into developer_skills(developer_id, skill_id)
values(3, 3);
insert into developer_skills(developer_id, skill_id)
values(3, 2);
insert into developer_skills(developer_id, skill_id)
values(3, 4);
insert into developer_skills(developer_id, skill_id)
values(3, 6);
insert into developer_skills(developer_id, skill_id)
values(3, 1);

insert into developer_skills(developer_id, skill_id)
values(4, 1);
insert into developer_skills(developer_id, skill_id)
values(4, 3);

insert into developer_skills(developer_id, skill_id)
values(5, 2);
insert into developer_skills(developer_id, skill_id)
values(5, 3);
insert into developer_skills(developer_id, skill_id)
values(5, 6);
insert into developer_skills(developer_id, skill_id)
values(5, 1);
