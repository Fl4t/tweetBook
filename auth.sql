CREATE TABLE IF NOT EXISTS Personnes (
  id_personne integer,
  nom text not null,
  prenom text not null,
  date_naissance date not null,
  email text not null,
  visibilite text not null,
  PRIMARY KEY(id_personne),
  CONSTRAINT visibilite CHECK (visibilite IN ('aucune', 'amis', 'tous'))
);

CREATE TABLE IF NOT EXISTS Amis (
  id_personne1 integer,
  id_personne2 integer,
  date_ajout date not null,
  PRIMARY KEY(id_personne1, id_personne2),
  constraint fk_amis_personne1 FOREIGN KEY (id_personne1) REFERENCES Personnes (id_personne),
  constraint fk_amis_personne2 FOREIGN KEY (id_personne2) REFERENCES Personnes (id_personne),
  constraint id_personne1 check (id_personne1 != id_personne2),
  constraint id_personne2 check (id_personne2 != id_personne1)
);

CREATE TABLE IF NOT EXISTS Actualitees (
  id_actualite integer,
  contenu text not null,
  date_ajout datetime not null,
  id_personne integer not null,
  PRIMARY KEY(id_actualite),
  constraint fk_actualitees_personne FOREIGN KEY (id_personne) REFERENCES Personnes (id_personne)
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

insert into Amis values('1', '2', date('now', '+1 day'));
insert into Amis values('2', '1', date('now', '+1 day'));

insert into Amis values('1', '3', date('now', '+2 day'));
insert into Amis values('3', '1', date('now', '+2 day'));

insert into Amis values('1', '4', date('now', '+3 day'));
insert into Amis values('4', '1', date('now', '+3 day'));

insert into Amis values('2', '3', date('now', '+5 day'));
insert into Amis values('3', '2', date('now', '+5 day'));

insert into Amis values('2', '4', date('now', '+6 day'));
insert into Amis values('4', '2', date('now', '+6 day'));

insert into Amis values('3', '4', date('now', '+2 day'));
insert into Amis values('4', '3', date('now', '+2 day'));

insert into Amis values('3', '5', date('now', '+3 day'));
insert into Amis values('5', '3', date('now', '+3 day'));

insert into Actualitees values(null, 'stechelj1', datetime('now', '+1 hour', '+1 day'), '1');
insert into Actualitees values(null, 'stechelj2', datetime('now', '+2 hour', '+2 day'), '1');
insert into Actualitees values(null, 'stechelj3', datetime('now', '+3 hour', '+3 day'), '1');
insert into Actualitees values(null, 'stechelj4', datetime('now', '+4 hour', '+4 day'), '1');
insert into Actualitees values(null, 'stechelj5', datetime('now', '+5 hour', '+5 day'), '1');

insert into Actualitees values(null, 'ruchont1', datetime('now', '+1 hour', '+1 day'), '2');
insert into Actualitees values(null, 'ruchont2', datetime('now', '+2 hour', '+2 day'), '2');
insert into Actualitees values(null, 'ruchont3', datetime('now', '+3 hour', '+3 day'), '2');
insert into Actualitees values(null, 'ruchont4', datetime('now', '+4 hour', '+4 day'), '2');
insert into Actualitees values(null, 'ruchont5', datetime('now', '+5 hour', '+5 day'), '2');

insert into Actualitees values(null, 'serirj1', datetime('now', '+1 hour', '+1 day'), '3');
insert into Actualitees values(null, 'serirj2', datetime('now', '+2 hour', '+2 day'), '3');
insert into Actualitees values(null, 'serirj3', datetime('now', '+3 hour', '+3 day'), '3');
insert into Actualitees values(null, 'serirj4', datetime('now', '+4 hour', '+4 day'), '3');
insert into Actualitees values(null, 'serirj5', datetime('now', '+5 hour', '+5 day'), '3');

insert into Actualitees values(null, 'cartona1', datetime('now', '+1 hour', '+1 day'), '4');
insert into Actualitees values(null, 'cartona2', datetime('now', '+2 hour', '+2 day'), '4');
insert into Actualitees values(null, 'cartona3', datetime('now', '+3 hour', '+3 day'), '4');
insert into Actualitees values(null, 'cartona4', datetime('now', '+4 hour', '+4 day'), '4');
insert into Actualitees values(null, 'cartona5', datetime('now', '+5 hour', '+5 day'), '4');

insert into Actualitees values(null, 'vanthoma1', datetime('now', '+1 hour', '+1 day'), '5');
insert into Actualitees values(null, 'vanthoma2', datetime('now', '+2 hour', '+2 day'), '5');
insert into Actualitees values(null, 'vanthoma3', datetime('now', '+3 hour', '+3 day'), '5');
insert into Actualitees values(null, 'vanthoma4', datetime('now', '+4 hour', '+4 day'), '5');
insert into Actualitees values(null, 'vanthoma5', datetime('now', '+5 hour', '+5 day'), '5');

insert into Authentification values(null, 'stechelj', 'stechelj', 'role1');
insert into Authentification values(null, 'ruchont', 'ruchont', 'role1');
insert into Authentification values(null, 'serirj', 'serirj', 'role1');
insert into Authentification values(null, 'cartona', 'cartona', 'role1');
insert into Authentification values(null, 'vanthoma', 'vanthoma', 'role1');
