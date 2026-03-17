CREATE TABLE usuarios
(
    id               BINARY(16)   NOT NULL,
    descripcion      VARCHAR(255) NULL,
    nombres          VARCHAR(255) NULL,
    apellidos        VARCHAR(255) NULL,
    username         VARCHAR(255) NULL,
    password         VARCHAR(255) NULL,
    email            VARCHAR(255) NULL,
    fecha_nacimiento datetime     NULL,
    sexo             CHAR         NOT NULL,
    dni              VARCHAR(255) NULL,
    num_comentarios  INT          NULL,
    CONSTRAINT pk_usuarios PRIMARY KEY (id)
);