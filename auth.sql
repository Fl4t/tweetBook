CREATE TABLE IF NOT EXISTS Personnes (
  id_personne integer not null,
  nom text not null,
  prenom text not null,
  date_naissance date not null,
  email text not null,
  visibilite text not null,
  constraint pk_personne PRIMARY KEY(id_personne),
  constraint const_visibilite CHECK (visibilite IN ('aucune', 'amis', 'tous'))
);

CREATE TABLE IF NOT EXISTS Amis (
  id_personne1 integer not null,
  id_personne2 integer not null,
  date_ajout timestamp not null,
  constraint pk_amis PRIMARY KEY(id_personne1, id_personne2),
  constraint fk_amis_personne1 FOREIGN KEY (id_personne1) REFERENCES Personnes (id_personne),
  constraint fk_amis_personne2 FOREIGN KEY (id_personne2) REFERENCES Personnes (id_personne)
);

CREATE TABLE IF NOT EXISTS Actualitees (
  id_actualite integer not null,
  type_actu text not null,
  contenu text not null,
  date_ajout timestamp not null,
  id_personne integer not null,
  constraint pk_actualitees PRIMARY KEY(id_actualite),
  constraint fk_actualitees_personne FOREIGN KEY (id_personne) REFERENCES Personnes (id_personne),
  constraint const_type_actu check (type_actu IN ('aime', 'amis', 'message'))
);

CREATE TABLE IF NOT EXISTS Aimes (
  notation integer not null,
  id_actualite integer not null,
  id_personne integer not null,
  constraint fk_actualitees_id FOREIGN KEY (id_actualite) REFERENCES Actualitees (id_actualite),
  constraint fk_personnes_id FOREIGN KEY (id_personne) REFERENCES Personnes (id_personne),
  constraint const_notation check (notation IN ('1', '-1')),
  constraint pk_aimes PRIMARY KEY(id_actualite, id_personne)
);

create table if not exists Authentification (
  id_authentification integer not null,
  login text not null,
  password text not null,
  role text not null,
  constraint pk_authentification primary key(id_authentification)
);

insert into Personnes values(1, 'stechele', 'julien', '29/01/1989', 'mail_bidon@gmail.com', 'tous');
insert into Personnes values(2, 'ruchon', 'thomas', '29/05/1989', 'mail_bidon@gmail.com', 'tous');
insert into Personnes values(3, 'serir', 'jean-françois', '29/01/1990', 'mail_bidon@gmail.com', 'aucune');
insert into Personnes values(4, 'carton', 'alexandre', '29/03/1992', 'mail_bidon@gmail.com', 'tous');
insert into Personnes values(5, 'vanthom', 'arthur', '09/01/1992', 'mail_bidon@gmail.com', 'amis');

insert into Amis values('1', '2', now());
insert into Amis values('2', '1', now());

insert into Amis values('1', '3', now());
insert into Amis values('3', '1', now());

insert into Amis values('1', '4', now());
insert into Amis values('4', '1', now());

insert into Amis values('1', '1', '2003-02-04 23:46:52');
insert into Amis values('2', '2', '2003-02-04 23:46:52');
insert into Amis values('3', '3', '2003-02-04 23:46:52');
insert into Amis values('4', '4', '2003-02-04 23:46:52');
insert into Amis values('5', '5', '2003-02-04 23:46:52');

insert into Actualitees values(1, 'message', 'stechelj1', '2013-02-04 00:00:00', '1');
insert into Actualitees values(2, 'message', 'ruchont1',  '2013-02-04 00:00:00', '2');
insert into Actualitees values(3, 'message', 'serirj1',   '2013-02-04 00:00:00', '3');
insert into Actualitees values(4, 'message', 'cartona1',  '2013-02-04 00:00:00', '4');

insert into Actualitees values(5, 'message', 'stechelj2', '2013-02-03 00:00:00', '1');
insert into Actualitees values(6, 'message', 'ruchont2',  '2013-02-03 00:00:00', '2');
insert into Actualitees values(7, 'message', 'serirj2',   '2013-02-03 00:00:00', '3');
insert into Actualitees values(8, 'message', 'cartona2',  '2013-02-03 00:00:00', '4');

insert into Actualitees values(9, 'message', 'stechelj3', '2013-02-02 00:00:00', '1');
insert into Actualitees values(10, 'message', 'ruchont3',  '2013-02-02 00:00:00', '2');
insert into Actualitees values(11, 'message', 'serirj3',   '2013-02-02 00:00:00', '3');
insert into Actualitees values(12, 'message', 'cartona3',  '2013-02-02 00:00:00', '4');

insert into Actualitees values(13, 'message', 'stechelj4', '2013-02-01 00:00:00', '1');
insert into Actualitees values(14, 'message', 'ruchont4',  '2013-02-01 00:00:00', '2');
insert into Actualitees values(15, 'message', 'serirj4',   '2013-02-01 00:00:00', '3');
insert into Actualitees values(16, 'message', 'cartona4',  '2013-02-01 00:00:00', '4');

insert into Actualitees values(17, 'message', 'stechelj5', '2013-01-29 00:00:00', '1');
insert into Actualitees values(18, 'message', 'ruchont5',  '2013-01-29 00:00:00', '2');
insert into Actualitees values(19, 'message', 'serirj5',   '2013-01-29 00:00:00', '3');
insert into Actualitees values(20, 'message', 'cartona5',  '2013-01-29 00:00:00', '4');

insert into Actualitees values(21, 'message', 'vanthoma1', '2013-02-04 00:00:00', '5');
insert into Actualitees values(22, 'message', 'vanthoma2', '2013-02-04 00:00:00', '5');
insert into Actualitees values(23, 'message', 'vanthoma3', '2013-02-04 00:00:00', '5');
insert into Actualitees values(24, 'message', 'vanthoma4', '2013-02-04 00:00:00', '5');
insert into Actualitees values(25, 'message', 'vanthoma5', '2013-02-04 00:00:00', '5');

insert into Authentification values(1, 'stechelj', 'stechelj', 'role1');
insert into Authentification values(2, 'ruchont', 'ruchont', 'role1');
insert into Authentification values(3, 'serirj', 'serirj', 'role1');
insert into Authentification values(4, 'cartona', 'cartona', 'role1');
insert into Authentification values(5, 'vanthoma', 'vanthoma', 'role1');
