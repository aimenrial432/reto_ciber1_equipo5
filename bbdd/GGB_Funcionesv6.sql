use Gestion_Gastos_Beta;

-- Consulta select-----
select bda_Empleados.Id_user, bda_Empleados.DNI, bda_Empleados.Nombre,
bda_Empleados.Apellido, bda_Empleados.e_mail, bda_Empleados.Username,
bda_Empleados.Fecha_reg, bda_Empleados.Departamento_Id, bda_Departamentos.Nombre_dep,
bda_Empleados.Id_rol, bda_Roles.Rol, bda_Empleados.Estado from bda_Empleados 
join bda_Departamentos 
on bda_Empleados.Departamento_Id = bda_Departamentos.Departamento_Id
join bda_Roles
on bda_Empleados.Id_rol = bda_Roles.Id_rol;

-- Vista a partir de la consulta select-----
create view select bda_Empleados.Id_user, bda_Empleados.DNI, bda_Empleados.Nombre,
bda_Empleados.Apellido, bda_Empleados.e_mail, bda_Empleados.Username,
bda_Empleados.Fecha_reg, bda_Empleados.Departamento_Id, bda_Departamentos.Nombre_dep,
bda_Empleados.Id_rol, bda_Roles.Rol, bda_Empleados.Estado from bda_Empleados
join bda_Departamentos 
on bda_Empleados.Departamento_Id = bda_Departamentos.Departamento_Id
join bda_Roles
on bda_Empleados.Id_rol = bda_Roles.Id_rol;

----Vista para el calculo del registro de horas------------
create view select_registro as 
select Id_user, Semana, Horas from bda_Registro_horas;