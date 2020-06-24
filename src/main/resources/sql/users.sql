insert into user (id_user, username, password, email, avatarName, card, activate) value (2, 'qwerty', '$2y$08$eO8CxXXET0CNRsxIb5Fi0OJWdz5XdCEtxo0UPT0TH2jUWbD2uFIeS', '1@dds.com', 'standardAvatar.jpg', '1234123412341234', true);
insert into user_role (id_user, roles) value (2, 'USER');
insert into address (id_address, id_country, city, street, house, flat) value (1, 2, 'minsk', 'pobedy', 12, 4);
insert into advertisement (id_adv, date, description, price, id_address, id_user, rating) value (1, '2020-06-02', 'eqweqweqwe', 11, 1, 2, 0.0);
insert into image (id_images, path, id_adv) value (1, 'standardImage.jpg', 1);
insert into reservation (id_rsrvt, arrivalDate, departureDate, totalCost, id_adv, id_user) value (1, '2020-06-20', '2020-06-22', 12, 1, 1);