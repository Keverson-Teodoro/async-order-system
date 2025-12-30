CREATE TABLE
    usuario (
        id integer NOT NULL,
        nome varchar NOT NULL,
        email varchar,
        telefone varchar,

        CONSTRAINT pk_usr_iduser PRIMARY KEY (id)
    );
