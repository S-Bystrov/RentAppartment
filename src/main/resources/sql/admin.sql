insert into user (id_user, username, password, avatarName, card, activate) value (1, 'admin', '$2y$08$CH.qkt1IMqa3yMVwUglANesqBSes6Mh3XFlanyn6ZHPIcq5uXXAZW', 'standardAvatar.jpg', '1234123412341234', true);
insert into user_role (id_user, roles) value (1, 'ADMIN');
insert into user_role( id_user, roles) value (1, 'USER');
