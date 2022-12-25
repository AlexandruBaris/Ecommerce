INSERT INTO users(first_name,last_name,username,password,enabled)
VALUES ('Alex','Baris','alexandru@gmail.com','$2a$04$569/uGkh.Y/fT5YvQ5iZ0.5jhnL3WhCbBwWMPA/AyfBG7DKTCKhAi','true'),
('Alex','Baris','abarisenschi@gmail.com','$2a$04$569/uGkh.Y/fT5YvQ5iZ0.5jhnL3WhCbBwWMPA/AyfBG7DKTCKhAi','true');

INSERT INTO users_roles(user_id,role_id)
VALUES(1,4),(2,1);

--password = Password1.
--username = alexandru@gmail.com