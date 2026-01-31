ALTER TABLE usuarios
ADD COLUMN role_id BIGINT;

ALTER TABLE usuarios
ADD CONSTRAINT fk_usuarios_role
FOREIGN KEY (role_id)
REFERENCES roles(id);

UPDATE usuarios
SET role_id = (SELECT id FROM roles WHERE nome = 'USER');

ALTER TABLE usuarios
ALTER COLUMN role_id SET NOT NULL;