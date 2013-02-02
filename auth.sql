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
  constraint fk_amis_personne2 FOREIGN KEY (id_personne2) REFERENCES Personnes (id_personne),
  constraint id_personne1 check (id_personne1 != id_personne2),
  constraint id_personne2 check (id_personne2 != id_personne1)
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

insert into Amis values('1', '2', datetime('now', '-1 day'));
insert into Amis values('2', '1', datetime('now', '-1 day'));

insert into Amis values('1', '3', datetime('now', '-2 day'));
insert into Amis values('3', '1', datetime('now', '-2 day'));

insert into Amis values('1', '4', datetime('now', '-3 day'));
insert into Amis values('4', '1', datetime('now', '-3 day'));

insert into Amis values('2', '3', datetime('now', '-5 day'));
insert into Amis values('3', '2', datetime('now', '-5 day'));

insert into Amis values('2', '4', datetime('now', '-6 day'));
insert into Amis values('4', '2', datetime('now', '-6 day'));

insert into Amis values('3', '4', datetime('now', '-2 day'));
insert into Amis values('4', '3', datetime('now', '-2 day'));

insert into Amis values('3', '5', datetime('now', '-3 day'));
insert into Amis values('5', '3', datetime('now', '-3 day'));

insert into Actualitees values(null, 'message', 'stechelj1', datetime('now', '-1 hour', '-1 day', '-1 minute'), '1');
insert into Actualitees values(null, 'message', 'stechelj2', datetime('now', '-2 hour', '-2 day', '-2 minute'), '1');
insert into Actualitees values(null, 'message', 'stechelj3', datetime('now', '-3 hour', '-3 day', '-3 minute'), '1');
insert into Actualitees values(null, 'message', 'stechelj4', datetime('now', '-4 hour', '-4 day', '-4 minute'), '1');
insert into Actualitees values(null, 'message', 'stechelj5', datetime('now', '-5 hour', '-5 day', '-5 minute'), '1');

insert into Actualitees values(null, 'message', 'ruchont1', datetime('now', '-1 hour', '-1 day', '-6 minute'), '2');
insert into Actualitees values(null, 'message', 'ruchont2', datetime('now', '-2 hour', '-2 day', '-7 minute'), '2');
insert into Actualitees values(null, 'message', 'ruchont3', datetime('now', '-3 hour', '-3 day', '-8 minute'), '2');
insert into Actualitees values(null, 'message', 'ruchont4', datetime('now', '-4 hour', '-4 day', '-9 minute'), '2');
insert into Actualitees values(null, 'message', 'ruchont5', datetime('now', '-5 hour', '-5 day', '-10 minute'), '2');

insert into Actualitees values(null, 'message', 'serirj1', datetime('now', '-1 hour', '-1 day', '-11 minute'), '3');
insert into Actualitees values(null, 'message', 'serirj2', datetime('now', '-2 hour', '-2 day', '-12 minute'), '3');
insert into Actualitees values(null, 'message', 'serirj3', datetime('now', '-3 hour', '-3 day', '-13 minute'), '3');
insert into Actualitees values(null, 'message', 'serirj4', datetime('now', '-4 hour', '-4 day', '-14 minute'), '3');
insert into Actualitees values(null, 'message', 'serirj5', datetime('now', '-5 hour', '-5 day', '-15 minute'), '3');

insert into Actualitees values(null, 'message', 'cartona1', datetime('now', '-1 hour', '-1 day', '-16 minute'), '4');
insert into Actualitees values(null, 'message', 'cartona2', datetime('now', '-2 hour', '-2 day', '-17 minute'), '4');
insert into Actualitees values(null, 'message', 'cartona3', datetime('now', '-3 hour', '-3 day', '-18 minute'), '4');
insert into Actualitees values(null, 'message', 'cartona4', datetime('now', '-4 hour', '-4 day', '-19 minute'), '4');
insert into Actualitees values(null, 'message', 'cartona5', datetime('now', '-5 hour', '-5 day', '-20 minute'), '4');

insert into Actualitees values(null, 'message', 'vanthoma1', datetime('now', '-1 hour', '-1 day', '-21 minute'), '5');
insert into Actualitees values(null, 'message', 'vanthoma2', datetime('now', '-2 hour', '-2 day', '-22 minute'), '5');
insert into Actualitees values(null, 'message', 'vanthoma3', datetime('now', '-3 hour', '-3 day', '-23 minute'), '5');
insert into Actualitees values(null, 'message', 'vanthoma4', datetime('now', '-4 hour', '-4 day', '-24 minute'), '5');
insert into Actualitees values(null, 'message', 'vanthoma5', datetime('now', '-5 hour', '-5 day', '-25 minute'), '5');

insert into Authentification values(null, 'stechelj', 'stechelj', 'role1');
insert into Authentification values(null, 'ruchont', 'ruchont', 'role1');
insert into Authentification values(null, 'serirj', 'serirj', 'role1');
insert into Authentification values(null, 'cartona', 'cartona', 'role1');
insert into Authentification values(null, 'vanthoma', 'vanthoma', 'role1');
