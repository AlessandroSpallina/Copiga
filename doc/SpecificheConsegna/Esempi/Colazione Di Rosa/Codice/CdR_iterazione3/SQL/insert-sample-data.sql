/* identificatori */
insert into tabellaid (high,maxlow) values (1000,50);

/* categorie cliente */
insert into categoriacliente (codice,nome,descrizione,ordini,fattorecliente) values (101,'base','cliente occasionale',5,1);
insert into categoriacliente (codice,nome,descrizione,ordini,fattorecliente) values (102,'affezionato','cliente affezionato',20,0.8);

/* clienti */
insert into cliente (codice,nome,cognome,indirizzo,ordini,categoria) values (201,'Mario','Rossi','Via Roma 1',4,101);
insert into cliente (codice,nome,cognome,indirizzo,ordini,categoria) values (202,'Craig','Larman','Expert Street 1',7,102);

/* descrizioni componenti */
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (300,'cornetto semplice',0.90,0.90);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (301,'cornetto alla crema',1,1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (302,'cornetto al cioccolato',1,1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (303,'cornetto alla marmellata',1,1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (304,'cornetto al miele',1,1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (310,'ciambella',1,1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (311,'bomba alla crema',1.1,1.1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (312,'bomba al cioccolato',1.1,1.1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (320,'caffe',1.1,1.2);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (321,'cappuccino',1.2,1.3);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (322,'caffe macchiato',1.1,1.1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (323,'latte freddo',1,1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (324,'latte caldo',1.1,1.1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (325,'succo di frutta',1,1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (326,'caffe francese',1.3,1.2);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (327,'succo arancia',1.1,1.1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (330,'cereali',1,1);
insert into descrizionecomponente (codice,nome,prezzoaggiuntivo,prezzoriduttivo) values (331,'burro e marmellata',1.5,1.3);

/* modi servizio */
insert into modoservizio (codice,nome,descrizione,fattore) values (401,'normale','carta e plastica',1);
insert into modoservizio (codice,nome,descrizione,fattore) values (402,'super','acciaio e vetro',1.3);
insert into modoservizio (codice,nome,descrizione,fattore) values (403,'lusso','argento e porcellana',1.6);

/* tipi colazione */
insert into tipocolazione (codice,nome,prezzo) values (501,'colazione italiana',5);
insert into tipocolazione (codice,nome,prezzo) values (502,'colazione francese',8);

/* composizione */
insert into composizione (tipocolazione,componente,quantita) values (501,321,1);
insert into composizione (tipocolazione,componente,quantita) values (501,301,1);
insert into composizione (tipocolazione,componente,quantita) values (501,310,1);

insert into composizione (tipocolazione,componente,quantita) values (502,326,1);
insert into composizione (tipocolazione,componente,quantita) values (502,327,1);
insert into composizione (tipocolazione,componente,quantita) values (502,300,2);
insert into composizione (tipocolazione,componente,quantita) values (502,331,1);

/* ordini */
insert into ordine (codice,prezzo,data,consegnato,cliente) values (601,10,'12/11/2005',FALSE,201);
insert into ordine (codice,prezzo,data,consegnato,cliente) values (602,25.8,'6/11/2005',FALSE,202);
insert into ordine (codice,prezzo,data,consegnato,cliente) values (603,8,'12/2/2005',TRUE,201);

/* colazioni ordinate */
insert into colazioneordinata (codice,tipocolazione,modoservizio,ordine) values (701,501,401,601);
insert into colazioneordinata (codice,tipocolazione,modoservizio,ordine) values (702,501,401,601);

insert into colazioneordinata (codice,tipocolazione,modoservizio,ordine) values (703,501,402,602);
insert into colazioneordinata (codice,tipocolazione,modoservizio,ordine) values (704,501,402,602);
insert into colazioneordinata (codice,tipocolazione,modoservizio,ordine) values (705,502,403,602);

insert into colazioneordinata (codice,tipocolazione,modoservizio,ordine) values (706,501,401,603);
