insert into users (id, email, username, password, enabled, account_locked, account_expired, credentials_expired) VALUES ('1', 'admin@gmail.com', 'admin', '$2a$10$7X5kgMO5uAqeWBoQmKuDZeGNBwZV6mq8966G13AdmTlob2gjcuFVa', true, false, false, false);
insert into role (id, role_name) values ('1', 'ROLE_ADMIN'), ('2', 'ROLE_USER');
insert into user_roles(user_id, role_id) values ('1','1');
insert into privilege(id, name) values (1, 'can_read'), (2, 'can_update'), (3, 'can_write'), (4, 'can_delete');
insert into roles_privileges(role_id, privilege_id) values (1, 1), (1,2), (1,3), (1,4), (2,1), (2,2), (2, 3), (2, 4);
insert into recipes(id, description, name, user_id) values ('1', 'Opis przepisu', 'Przepis1', 1);
insert into steps(id, description, name, temperature, time, recipe_id) values ('1', 'Opis', 'Krok 1', 0.4, '05:30:30', 1);
