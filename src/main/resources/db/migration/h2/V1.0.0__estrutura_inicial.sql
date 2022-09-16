CREATE TABLE pessoa (
	id bigint NOT NULL PRIMARY KEY,
	nome VARCHAR (100) NOT NULL,
	identificador VARCHAR (14) NOT NULL,
	tipo_identificador VARCHAR (4) NOT NULL
);

CREATE UNIQUE INDEX unique_index_identificador ON pessoa (identificador);