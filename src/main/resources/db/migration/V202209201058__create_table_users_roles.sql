CREATE TABLE users_roles (
  user_id    INT NOT NULL,
  role_id    INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY(user_id) REFERENCES users(user_id) ON UPDATE CASCADE,
  FOREIGN KEY(role_id) REFERENCES roles(role_id) ON UPDATE CASCADE
);