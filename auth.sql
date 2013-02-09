CREATE TABLE IF NOT EXISTS Personnes (
  id_personne integer not null,
  nom text not null,
  prenom text not null,
  date_naissance date not null,
  email text not null,
  visibilite text not null,
  PRIMARY KEY(id_personne),
  CONSTRAINT const_visibilite CHECK (visibilite IN ('aucune', 'amis', 'tous'))
);

CREATE TABLE IF NOT EXISTS Amis (
  id_personne1 integer not null,
  id_personne2 integer not null,
  date_ajout datetime not null,
  PRIMARY KEY(id_personne1, id_personne2),
  constraint fk_amis_personne1 FOREIGN KEY (id_personne1) REFERENCES Personnes (id_personne),
  constraint fk_amis_personne2 FOREIGN KEY (id_personne2) REFERENCES Personnes (id_personne)
);

CREATE TABLE IF NOT EXISTS Actualitees (
  id_actualite integer not null,
  type_actu text not null,
  contenu text not null,
  date_ajout datetime not null,
  id_personne integer not null,
  PRIMARY KEY(id_actualite),
  constraint fk_actualitees_personne FOREIGN KEY (id_personne) REFERENCES Personnes (id_personne),
  constraint const_type_actu check (type_actu IN ('aime', 'amis', 'message'))
);

CREATE TABLE IF NOT EXISTS Aimes (
  notation integer not null,
  id_actualite integer not null,
  id_personne integer not null,
  constraint fk_actualitees_id FOREIGN KEY (id_actualite) REFERENCES Actualitees (id_actualite),
  constraint fk_personnes_id FOREIGN KEY (id_personne) REFERENCES Personnes (id_personne),
  constraint const_notation check (notation IN ('1', '-1'))
  PRIMARY KEY(id_actualite, id_personne)
);

create table if not exists Authentification (
  id_authentification integer not null,
  login text not null,
  password text not null,
  role text not null,
  primary key(id_authentification)
);

insert into Personnes values(null, 'stechele', 'julien', '29/01/1989', 'mail_bidon@gmail.com', 'tous');
insert into Personnes values(null, 'ruchon', 'thomas', '29/05/1989', 'mail_bidon@gmail.com', 'tous');
insert into Personnes values(null, 'serir', 'jean-françois', '29/01/1990', 'mail_bidon@gmail.com', 'aucune');
insert into Personnes values(null, 'carton', 'alexandre', '29/03/1992', 'mail_bidon@gmail.com', 'tous');
insert into Personnes values(null, 'vanthom', 'arthur', '09/01/1992', 'mail_bidon@gmail.com', 'amis');

insert into Amis values('1', '2', datetime('now'));
insert into Amis values('2', '1', datetime('now'));

insert into Amis values('1', '3', datetime('now'));
insert into Amis values('3', '1', datetime('now'));

insert into Amis values('1', '4', datetime('now'));
insert into Amis values('4', '1', datetime('now'));

insert into Amis values('1', '1', '2003-02-04 23:46:52');
insert into Amis values('2', '2', '2003-02-04 23:46:52');
insert into Amis values('3', '3', '2003-02-04 23:46:52');
insert into Amis values('4', '4', '2003-02-04 23:46:52');
insert into Amis values('5', '5', '2003-02-04 23:46:52');

insert into Actualitees values(null, 'message', 'stechelj1', datetime('now', '+1 day', '-1 hour'), '1');
insert into Actualitees values(null, 'message', 'ruchont1',  datetime('now', '+1 day', '-2 hour'), '2');
insert into Actualitees values(null, 'message', 'serirj1',   datetime('now', '+1 day', '-3 hour'), '3');
insert into Actualitees values(null, 'message', 'cartona1',  datetime('now', '+1 day', '-4 hour'), '4');

insert into Actualitees values(null, 'message', 'stechelj2', datetime('now', '+2 day', '-1 hour'), '1');
insert into Actualitees values(null, 'message', 'ruchont2',  datetime('now', '+2 day', '-2 hour'), '2');
insert into Actualitees values(null, 'message', 'serirj2',   datetime('now', '+2 day', '-3 hour'), '3');
insert into Actualitees values(null, 'message', 'cartona2',  datetime('now', '+2 day', '-4 hour'), '4');

insert into Actualitees values(null, 'message', 'stechelj3', datetime('now', '+3 day', '-1 hour'), '1');
insert into Actualitees values(null, 'message', 'ruchont3',  datetime('now', '+3 day', '-2 hour'), '2');
insert into Actualitees values(null, 'message', 'serirj3',   datetime('now', '+3 day', '-3 hour'), '3');
insert into Actualitees values(null, 'message', 'cartona3',  datetime('now', '+3 day', '-4 hour'), '4');

insert into Actualitees values(null, 'message', 'stechelj4', datetime('now', '+4 day', '-1 hour'), '1');
insert into Actualitees values(null, 'message', 'ruchont4',  datetime('now', '+4 day', '-2 hour'), '2');
insert into Actualitees values(null, 'message', 'serirj4',   datetime('now', '+4 day', '-3 hour'), '3');
insert into Actualitees values(null, 'message', 'cartona4',  datetime('now', '+4 day', '-4 hour'), '4');

insert into Actualitees values(null, 'message', 'stechelj5', datetime('now', '+5 day', '-1 hour'), '1');
insert into Actualitees values(null, 'message', 'ruchont5',  datetime('now', '+5 day', '-2 hour'), '2');
insert into Actualitees values(null, 'message', 'serirj5',   datetime('now', '+5 day', '-3 hour'), '3');
insert into Actualitees values(null, 'message', 'cartona5',  datetime('now', '+5 day', '-4 hour'), '4');

insert into Actualitees values(null, 'message', 'vanthoma1', datetime('now', '+1 day', '-1 hour'), '5');
insert into Actualitees values(null, 'message', 'vanthoma2', datetime('now', '+2 day', '-2 hour'), '5');
insert into Actualitees values(null, 'message', 'vanthoma3', datetime('now', '+3 day', '-3 hour'), '5');
insert into Actualitees values(null, 'message', 'vanthoma4', datetime('now', '+4 day', '-4 hour'), '5');
insert into Actualitees values(null, 'message', 'vanthoma5', datetime('now', '+5 day', '-5 hour'), '5');

insert into Authentification values(null, 'stechelj', 'stechelj', 'role1');
insert into Authentification values(null, 'ruchont', 'ruchont', 'role1');
insert into Authentification values(null, 'serirj', 'serirj', 'role1');
insert into Authentification values(null, 'cartona', 'cartona', 'role1');
insert into Authentification values(null, 'vanthoma', 'vanthoma', 'role1');
