create database giif_v3;

use giif_v3;

-- Tabla empleados-----------------------

create table Empleados (
	
    DNI varchar(9) ,
    Username varchar(50) unique not null,
    Password_hash varchar(255) not null,
    Nombre varchar(50) not null,
    Apellido varchar(50) not null,
    Depatamento_ID int 

);

-- Tabla Roles--------------------

create table Roles (
	
    DNI varchar(9),
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
	
    Departamento_ID int auto_increment,
    Nombre varchar(50) not null

);

-- Tabla proyectos---------

create table Proyectos (
	
    Proyecto_ID int auto_increment,
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

create table 