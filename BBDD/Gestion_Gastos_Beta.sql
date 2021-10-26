-- El siguiente script edita y crea las diferentes tablas para la aplicación web y movil del proyecto

use Gestion_Gastos_Beta;

-- Se crea la tabla empleados a partir de la de wggbt_users

-- Tabla Empleados----------------

create table bda_Empleados (

	
	Id_user int,
    DNI varchar(9),
	Nombre varchar(50) not null,
    Apellido varchar(50) not null,
	e_mail varchar(250) not null,
    Username varchar(50) not null,
    Password_hash varchar(255) not null,
    Fecha_reg datetime,
    Departamento_Id int not null,
    Id_rol int not null

);

drop table bda_Empleados;

alter table bda_Empleados add primary key(Id_user, DNI);

-- Creacion de las nuevas tablas para la base de datos-----------------

-- Tabla Roles--------------------

create table bda_Roles (
	Id_rol int auto_increment primary key,
    Rol varchar(20) not null

);


-- Tabla conexiones--------------------

create table bda_Conexiones (
	
    DNI varchar(9),
    Fecha datetime not null,
    Ip varchar(50) not null

);

-- Tabla Departamentos----------------

create table bda_Departamentos (
	
    Departamento_Id int auto_increment primary key,
    Nombre varchar(50) not null

);

-- Tabla proyectos---------

create table bda_Proyectos (
	
    Proyecto_Id int auto_increment primary key,
    Descripcion varchar(255),
    Fecha_inicio Date not null,
    Fecha_fin Date

);

-- Tabla integrantes---------------

create table bda_Integrantes (
	
    Proyecto_Id int,
    DNI varchar(9)

);


-- Tabla Registro de horas ----------------

CREATE TABLE bda_Registro_horas (
	DNI varchar(9),
	Dia date not null,
	Semana int(11) not null,
	Horas float not null,
	Proyecto_Id int(11)
    
    );
    
    -- Tabla gastos------------------
   CREATE TABLE bda_Gastos (
	Gasto_Id int(11) auto_increment primary key,
	Proyecto_Id int(11),
	DNI varchar(9),
	Pais varchar(50) not null,
	Ciudad varchar(50) not null,
	Fecha date not null,
	Costo decimal(7,2) not null 
  
  );
  
  -- Tabla dietas
  
  CREATE TABLE bda_Dietas (
	Gasto_Id int(11),
	Tipo varchar(50) not null,
	Descripcion varchar(255) not null,
	Region varchar(20) not null
    
);

-- Tabla transporte--------------
CREATE TABLE bda_Transporte (
	Gasto_Id int(11),
	Medio varchar(50) not null,
	Distancia decimal(7,2) not null,
	Peaje decimal(7,2) not null,
	Parking decimal(7,2) not null
  
);


-- Primary keys ---------------------------------------

-- Keys tabla integrantes----------------------
alter table bda_Integrantes add primary key (Proyecto_Id, DNI);

-- Keys tabla conexiones-----------------
alter table bda_Conexiones add primary key(DNI, Fecha);

-- keys tabla registro horas
alter table bda_Registro_horas add primary key (DNI, Dia);



-- Foreign keys------------------------------------------

-- tabla Empleados-----------------
alter table wggbt_users  add foreign key (Id_rol) references bda_Roles(Id_rol);
alter table wggbt_users add foreign key (Departamento_Id) references bda_Departamentos(Departamento_Id);

-- Keys tabla integrantes----------------------
alter table bda_Integrantes add foreign key (Proyecto_Id) references bda_Proyectos(Proyecto_Id);
alter table bda_Integrantes add foreign key (DNI) references wggbt_users(DNI);

-- Keys tabla conexiones-----------------
alter table bda_Conexiones add foreign key (DNI) references wggbt_users(DNI);

-- keys tabla registro horas
alter table bda_Registro_horas add foreign key (DNI) references wggbt_users(DNI);
alter table bda_Registro_horas add foreign key (Proyecto_Id) references bda_Proyectos(Proyecto_Id);

-- Keys tabla gastos------------------------
alter table bda_Gastos add foreign key (DNI) references wggbt_users(DNI);
alter table bda_Gastos add foreign key (Proyecto_Id) references bda_Proyectos(Proyecto_Id);

-- Keys tabla dietas-----------------------
alter table bda_Dietas add foreign key (Gasto_Id) references bda_Gastos(Gasto_Id);

-- Keys tabla transportes---------------------------
alter table bda_Transporte add foreign key (Gasto_Id) references bda_Gastos(Gasto_Id);


-- El siguiente insert pertenece al admin por lo tanto se hace por separado ----
insert into bda_Empleados values (
	null,
	"78999287Y",
    "Admin",
    "Wordpress",
    (select user_email from wggbt_users where ID=1),
    (select user_login from wggbt_users where ID=1),
	(select user_pass from wggbt_users where ID=1),
    (select user_registered from wggbt_users where ID=1),
    1,
    1

);

select * from bda_Empleados;

alter table bda_Empleados AUTO_INCREMENT=1;





