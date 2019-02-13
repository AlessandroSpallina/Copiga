CREATE TABLE categoriacliente
(
  codice int4 NOT NULL,
  nome varchar(15) NOT NULL,
  descrizione varchar(50),
  ordini int2 NOT NULL,
  fattorecliente float8 NOT NULL,
  CONSTRAINT categoriacliente_pkey PRIMARY KEY (codice),
  CONSTRAINT categoriacliente_ordini_key UNIQUE (ordini)
) 
WITHOUT OIDS;
COMMENT ON TABLE categoriacliente IS 'Categoria dei clienti';

CREATE TABLE cliente
(
  codice int4 NOT NULL,
  nome varchar(22) NOT NULL,
  cognome varchar NOT NULL,
  indirizzo varchar(50),
  ordini int2 NOT NULL,
  categoria int4 NOT NULL,
  CONSTRAINT cliente_pkey PRIMARY KEY (codice),
  CONSTRAINT cliente_categoria_fkey FOREIGN KEY (categoria) REFERENCES categoriacliente (codice) ON UPDATE RESTRICT ON DELETE RESTRICT
) 
WITHOUT OIDS;

CREATE TABLE descrizionecomponente
(
  codice int4 NOT NULL,
  nome varchar(25) NOT NULL,
  prezzoaggiuntivo float8 NOT NULL,
  prezzoriduttivo float8 NOT NULL,
  CONSTRAINT descrizionecomponente_pkey PRIMARY KEY (codice)
) 
WITHOUT OIDS;
COMMENT ON TABLE descrizionecomponente IS 'Descrizione della componente';

CREATE TABLE tipocolazione
(
  codice int4 NOT NULL,
  nome varchar(25) NOT NULL,
  prezzo float8,
  CONSTRAINT tipocolazione_pkey PRIMARY KEY (codice)
) 
WITHOUT OIDS;
COMMENT ON TABLE tipocolazione IS 'Tipo di colazione';

CREATE TABLE composizione
(
  tipocolazione int4 NOT NULL,
  componente int4 NOT NULL,
  quantita int2 DEFAULT 1,
  CONSTRAINT composizione_pkey PRIMARY KEY (tipocolazione, componente),
  CONSTRAINT composizione_componente_fkey FOREIGN KEY (componente) REFERENCES descrizionecomponente (codice) ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT composizione_tipocolazione_fkey FOREIGN KEY (tipocolazione) REFERENCES tipocolazione (codice) ON UPDATE RESTRICT ON DELETE RESTRICT
) 
WITHOUT OIDS;
COMMENT ON TABLE composizione IS 'Composizione dei tipi di colazione';

CREATE TABLE modoservizio
(
  codice int4 NOT NULL,
  nome varchar(15) NOT NULL,
  descrizione varchar(50),
  fattore float8 NOT NULL,
  CONSTRAINT modoservizio_pkey PRIMARY KEY (codice)
) 
WITHOUT OIDS;

CREATE TABLE ordine
(
  codice int4 NOT NULL,
  prezzo float8 NOT NULL,
  data date NOT NULL,
  consegnato bool NOT NULL DEFAULT FALSE, 
  cliente int4,
  CONSTRAINT ordine_pkey PRIMARY KEY (codice),
  CONSTRAINT ordine_cliente_fkey FOREIGN KEY (cliente) REFERENCES cliente (codice) ON UPDATE RESTRICT ON DELETE RESTRICT
) 
WITHOUT OIDS;

CREATE TABLE tabellaid
(
  high int4 NOT NULL,
  maxlow int4,
  CONSTRAINT tabellaid_pkey PRIMARY KEY (high)
) 
WITHOUT OIDS;

CREATE TABLE colazioneordinata
(
  codice int4 NOT NULL,
  tipocolazione int4 NOT NULL,
  modoservizio int4 NOT NULL,
  ordine int4 NOT NULL,
  CONSTRAINT colazioneordinata_pkey PRIMARY KEY (codice),
  CONSTRAINT colazioneordinata_tipocolazione_fkey FOREIGN KEY (tipocolazione) REFERENCES tipocolazione (codice) ON UPDATE RESTRICT ON DELETE CASCADE,
  CONSTRAINT colazioneordinata_modoservizio_fkey FOREIGN KEY (modoservizio) REFERENCES modoservizio (codice) ON UPDATE RESTRICT ON DELETE CASCADE,
  CONSTRAINT colazioneordinata_ordine_fkey FOREIGN KEY (ordine) REFERENCES ordine (codice) ON UPDATE RESTRICT ON DELETE CASCADE
) 
WITHOUT OIDS;

CREATE TABLE variazione
(
  colazioneordinata int4 NOT NULL,
  componente int4 NOT NULL,
  quantita int2 NOT NULL,
  CONSTRAINT variazione_pkey PRIMARY KEY (colazioneordinata,componente),
  CONSTRAINT variazione_colazioneordinata_fkey FOREIGN KEY (colazioneordinata) REFERENCES colazioneordinata (codice) ON UPDATE RESTRICT ON DELETE CASCADE,
  CONSTRAINT variazione_componente_fkey FOREIGN KEY (componente) REFERENCES descrizionecomponente (codice) ON UPDATE RESTRICT ON DELETE CASCADE
) 
WITHOUT OIDS;
