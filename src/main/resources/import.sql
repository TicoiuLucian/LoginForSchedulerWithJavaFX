INSERT INTO role(role_id, name) VALUES (999, 'ROLE_USER')
INSERT INTO role(role_id, name) VALUES (998, 'ROLE_ADMIN')

--password1
INSERT INTO my_user(user_id, enabled, email, full_name, user_name, password) VALUES (1, 1, 'admin_email', 'admin_lastname', 'admin_name', '$2a$04$aAiATQxdXgbVhzF3Eujl1umIGVzZUTYN4m6phRLUQdu.M.1ZS/Sye')
INSERT INTO my_user(user_id, enabled, email, full_name, user_name, password) VALUES (2, 1, 'user_email@email.ro', 'user_lastName', 'user_name', '$2a$04$6BqxSLynCIDsRQdXPbO65e7ifpTaLY.IAu.ZFYIOkF63ABCGxp6lW')


INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES (1, 998)
INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES (1, 999)
INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES (2, 998)

INSERT INTO message(uuid, content, sender, creation_time) VALUES ('123e4567-e89b-42d3-a456-556642440000','Message Content 0','Sender 0',NOW())
INSERT INTO message(uuid, content, sender, creation_time) VALUES ('123e4567-e89b-42d3-a456-556642440001','Message Content 1','Sender 1',NOW())
INSERT INTO message(uuid, content, sender, creation_time) VALUES ('123e4567-e89b-42d3-a456-556642440002','Message Content 2','Sender 2',NOW())
INSERT INTO message(uuid, content, sender, creation_time) VALUES ('123e4567-e89b-42d3-a456-556642440003','Message Content 3','Sender 3',NOW())
INSERT INTO message(uuid, content, sender, creation_time) VALUES ('123e4567-e89b-42d3-a456-556642440004','test message content for admin','admin_name',NOW())