create database rheacaoestrutura;
use rheacaoestrutura;

create table usuarios(
iduso int primary key auto_increment not null,
usonome varchar(200) not null,
usoendereco varchar(200),
usocidade varchar(100),
usocelular varchar(50) not null,
usomatricula varchar(20),
usodatacadastro varchar(50) not null,
usousuario varchar(50) not null,
usosenha varchar(50) not null,
usoativo boolean default 1,
usoperfil varchar(50),
usocpf varchar(20)
);
insert into usuarios (usonome,usoendereco,usocidade,usocelular,usomatricula,usocpf,usodatacadastro,usousuario,usosenha,usoativo,usoperfil)
values ("Tarciso Nascimento Bezerra","Rua Presidente Prudente de Morais","Ariquemes/RO","(69) 9.9209-9315","1825-G/RO","983.284.252-20","01/05/2010","admin","admin",1,"Administrador");

create table empresa(
idempresa int primary key auto_increment not null,
empnomefantasia varchar (200) default 'Nome da Empresa',
emprazaosocial varchar (200) default 'Razão Social da Empresa',
empslogan varchar (200) default 'O melhor treinador do Brasil',
empcnpjcpf varchar (20) default '00.000.000/0000-00',
empinscricao varchar (20) default '00000',
empendereco varchar (200) default 'endereco nome da rua e nº',
empcidade varchar (100) default 'nome da cidade',
empestado varchar (2) default 'AC',
empcep varchar (15) default '00.000-000',
emptelefone varchar (14) default '(00)0000-0000',
empcelular varchar (16) default '(00)0.0000-0000',
empemail varchar (100) default 'exemplo@exemplo.com.br',
emplogo longblob,
empativado boolean default 0,
emphashdeativacao varchar (500)
);
insert into empresa (empnomefantasia) values ("Nome da Empresa");


create table processos(
idprocesso int primary key auto_increment not null,
proctitulo varchar (200),
procdata varchar (30),
processo longblob,
procnumpaginas varchar (10),
procdescricao varchar (400),
procdataarquivo varchar (30),
procpalavraschave varchar (100)

);