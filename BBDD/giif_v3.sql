drop database if exists giif_v3;


create database giif_v3;

use giif_v3;

-- Tabla empleados-----------------------

create table Empleados (
	
    DNI varchar(9) ,
    Username varchar(50) unique not null,
    Password_hash varchar(255) not null,
    Nombre varchar(50) not null,
    Apellido varchar(50) not null,
    Departamento_Id int,
    Id_rol int

);

-- Tabla Roles--------------------

create table Roles (
	Id_rol int auto_increment primary key,
    Rol varchar(20) not null

);

-- Tabla conexiones--------------------

create table Conexiones (
	
    DNI varchar(9),
    Fecha datetime not null,
    Ip varchar(50) not null

);

-- Tabla Departamentos----------------

create table Departamentos (
	
    Departamento_Id int auto_increment primary key,
    Nombre varchar(50) not null

);

-- Tabla proyectos---------

create table Proyectos (
	
    Proyecto_Id int auto_increment primary key,
    Descripcion varchar(255),
    Fecha_inicio Date not null,
    Fecha_fin Date

);

-- Tabla integrantes---------------

create table Integrantes (
	
    Proyecto_ID int,
    DNI varchar(9)

);

-- Tabla Registro de horas ----------------

CREATE TABLE Registro_horas (
	DNI varchar(9),
	Dia date not null,
	Semana int(11) not null,
	Horas float not null,
	Proyecto_Id int(11)
    
    );
    
    -- Tabla gastos------------------
   CREATE TABLE Gastos (
	Gasto_Id int(11) auto_increment primary key,
	Proyecto_Id int(11),
	DNI varchar(9),
	Pais varchar(50) not null,
	Ciudad varchar(50) not null,
	Fecha date not null,
	Costo decimal(7,2) not null 
  
  );
  
  -- Tabla dietas
  
  CREATE TABLE Dietas (
	Gasto_Id int(11),
	Tipo varchar(50) not null,
	Descripcion varchar(255) not null,
	Region varchar(20) not null
    
);

-- Tabla transporte--------------
CREATE TABLE Transporte (
	Gasto_Id int(11),
	Medio varchar(50) not null,
	Distancia decimal(7,2) not null,
	Peaje decimal(7,2) not null,
	Parking decimal(7,2) not null
  
);


-- Primary keys ---------------------------------------

-- tabla Empleados-----------------
alter table Empleados add primary key (DNI);

-- Keys tabla integrantes----------------------
alter table Integrantes add primary key (Proyecto_Id, DNI);

-- Keys tabla conexiones-----------------
alter table Conexiones add primary key(DNI, Fecha);

-- keys tabla registro horas
alter table Registro_horas add primary key (DNI, Dia);



-- Foreign keys------------------------------------------

-- tabla Empleados-----------------
alter table Empleados add foreign key (Id_rol) references Roles(Id_rol);
alter table Empleados add foreign key (Departamento_Id) references Departamentos(Departamento_Id);

-- Keys tabla integrantes----------------------
alter table Integrantes add foreign key (Proyecto_Id) references Proyectos(Proyecto_Id);
alter table Integrantes add foreign key (DNI) references Empleados(DNI);

-- Keys tabla conexiones-----------------
alter table Conexiones add foreign key (DNI) references Empleados(DNI);

-- keys tabla registro horas
alter table Registro_horas add foreign key (DNI) references Empleados(DNI);
alter table Registro_horas add foreign key (Proyecto_Id) references Proyectos(Proyecto_Id);

-- Keys tabla gastos------------------------
alter table Gastos add foreign key (DNI) references Empleados(DNI);
alter table Gastos add foreign key (Proyecto_Id) references Proyectos(Proyecto_Id);

-- Keys tabla dietas-----------------------
alter table Dietas add foreign key (Gasto_Id) references Gastos(Gasto_Id);

-- Keys tabla transportes---------------------------
alter table Transporte add foreign key (Gasto_Id) references Gastos(Gasto_Id);