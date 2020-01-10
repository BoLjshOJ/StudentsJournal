insert into usr (id, username, password, is_active)
    values (999, 'admin', '123', true);

insert into user_role (user_id, roles)
    values (999, 'USER'), (999, 'ADMIN');