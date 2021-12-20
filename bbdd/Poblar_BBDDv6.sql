

-- El siguiente insert pertenece al admin de la aplicacion web
insert into bda_Empleados values (
	null,
	"78999287Y",
    "Admin",
    "Wordpress",
    (select user_email from wggbt_users where ID=1),
    (select user_login from wggbt_users where ID=1),
	"$2y$10$HpSJHeAVUqNLCUUOMdB6qepX0qWhQK1T/rX7y2UPElv.DKKLkjlZi",
    (select user_registered from wggbt_users where ID=1),
    1,
    1

);

-- El siguiente insert pertenece al admin de la aplicacion del movil
insert into bda_Empleados values (
	null,
	"78999288Y",
    "Admin",
    "Appp",
    "admin.app@android.com",
    'user_bapp',
	"$2y$10$sC0c3kduB5r.Su/5.nB8ieBc.WG.xVHGeGSl2PjRwtgA/u.QI0qri",
   now(),
    1,
    1

);

delete from bda_Empleados where Id_user=10;

insert into bda_Empleados values (
null,
	"7699288Y",
    "Pico",
    "Palos",
    "user.empleado1@giif.com",
    'user_empleado1',
	"$2y$10$a.Asnpbob2Lds5n9ilXgE.gm/RoQK7ztxp4NuVm2FZwsw6apb1Huy",
   now(),
    4,
    2
    
);


-- Poblar tabla de Roles
INSERT INTO bda_Roles (Rol) VALUES
('Administrador'),
('Empleado');

-- Poblar tabla Departamentos
INSERT INTO bda_Departamentos (Nombre) VALUES
('Administración'),
('Desarrollo'),
('Mantenimiento'),
('Transportes');

select * from bda_Departamentos;
-- Poblar tabla proyectos
INSERT INTO bda_Proyectos (`Proyecto_Id`, `Descripcion`, `Fecha_inicio`, `Fecha_fin`) VALUES
(1, 'App Gestión' , '2021-09-20', null),
(2, 'Mantenimiento equipos antiguos', '2007-08-23', '2007-08-29'),
(3, 'Mantenimiento actual', curdate(), null),
(4, 'App web', curdate(), null);

