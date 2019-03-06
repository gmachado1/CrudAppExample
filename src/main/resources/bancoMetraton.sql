-- criando base de dados
create database metratonWeb;
-- criando tabelas
use metratonWeb;

create table cargo(
id_cargo int auto_increment not null primary key,
nome varchar(50));

create table usuario(
id_usuario int auto_increment not null primary key,
nome varchar(50) not null,
id_cargo int not null,
FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo));
	
create table telefone(
id_telefone int auto_increment not null primary key,
ddd int(2) not null,
numero int(9) not null,
id_usuario int not null,
FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario));

-- populando a base
-- cargo
INSERT INTO metratonweb.cargo (`id_cargo`,`nome`) VALUES (1,'Arquiteto');
INSERT INTO metratonweb.cargo (`id_cargo`,`nome`) VALUES (2,'Programador');
INSERT INTO metratonweb.cargo (`id_cargo`,`nome`) VALUES (3,'Designer');
INSERT INTO metratonweb.cargo (`id_cargo`,`nome`) VALUES (4,'Caminhoneiro');


-- usuario
INSERT INTO metratonweb.usuario (`id_usuario`,`nome`,`id_cargo`) VALUES (1,'Gustavo',1);
INSERT INTO metratonweb.usuario (`id_usuario`,`nome`,`id_cargo`) VALUES (2,'Hamilton',2);
INSERT INTO metratonweb.usuario (`id_usuario`,`nome`,`id_cargo`) VALUES (3,'Rafael',3);
INSERT INTO metratonweb.usuario (`id_usuario`,`nome`,`id_cargo`) VALUES (9,'Rafaela',3);
INSERT INTO metratonweb.usuario (`id_usuario`,`nome`,`id_cargo`) VALUES (4,'Zeca',4);
INSERT INTO metratonweb.usuario (`id_usuario`,`nome`,`id_cargo`) VALUES (5,'Zamora',4);
INSERT INTO metratonweb.usuario (`id_usuario`,`nome`,`id_cargo`) VALUES (6,'Zigoto',4);
INSERT INTO metratonweb.usuario (`id_usuario`,`nome`,`id_cargo`) VALUES (7,'Zolane',4);
INSERT INTO metratonweb.usuario (`id_usuario`,`nome`,`id_cargo`) VALUES (8,'Zúmia',4);


-- telefone
select *from telefone
INSERT INTO metratonweb.telefone (`id_telefone`,`ddd`,`numero`,`id_usuario`) VALUES (1,21,984588122,1);
INSERT INTO metratonweb.telefone (`id_telefone`,`ddd`,`numero`,`id_usuario`) VALUES (2,21,926182040,2);
INSERT INTO metratonweb.telefone (`id_telefone`,`ddd`,`numero`,`id_usuario`) VALUES (3,21,833701812,3);
INSERT INTO metratonweb.telefone (`id_telefone`,`ddd`,`numero`,`id_usuario`) VALUES (9,21,242424242,9);
INSERT INTO metratonweb.telefone (`id_telefone`,`ddd`,`numero`,`id_usuario`) VALUES (5,21,123456788,5);
INSERT INTO metratonweb.telefone (`id_telefone`,`ddd`,`numero`,`id_usuario`) VALUES (6,21,887654321,6);
INSERT INTO metratonweb.telefone (`id_telefone`,`ddd`,`numero`,`id_usuario`) VALUES (7,31,113355779,7);
INSERT INTO metratonweb.telefone (`id_telefone`,`ddd`,`numero`,`id_usuario`) VALUES (8,11,224466880,8);
-- update metratonweb.telefone set id_usuario=8 where id_telefone=8

-- selects das tabelas
select * from metratonweb.cargo;
select * from metratonweb.usuario;
select * from metratonweb.telefone;

-- Questao Banco de dados 
-- a) Selecione o nome dos usuários que não possuem telefone.
Select u.nome AS nome from metratonweb.usuario as u 
	left join telefone as t on u.id_usuario=t.id_usuario 
    where t.numero is null;

-- b) Selecione o nome de todos os caminhoneiros do Rio de Janeiro.
select u.nome, t.ddd,t.numero f, c.nome from usuario as u 
	inner join telefone as t on t.id_usuario = u.id_usuario 
    inner join cargo as c on u.id_cargo = c.id_cargo 
where t.ddd = 21 and c.nome= 'Caminhoneiro'; 

/** c) Selecione o nome dos cargos que possuem mais de 2 funcionários 
 e ordene de forma decrescente. **/
 Select  c.nome ,  count(c.nome)  qtd  from Cargo as c 
Inner join usuario as u on u.id_cargo = c.id_cargo
Group by c.nome Having qtd > 2;

-- select users
Select * from metratonweb.usuario
-- select busca
Select u.id_usuario as idUsuario, u.nome as nomeUsuario , c.id_cargo as idCargo, 
c.nome as nomeCargo, t.id_telefone as idTel, t.ddd as ddd, t.numero as numero 
from metratonweb.usuario as u left join metratonweb.telefone as t on t.id_usuario=u.id_usuario 
inner join metratonweb.cargo as c on  u.id_cargo=c.id_cargo -- where u.nome like 'raf%';
 