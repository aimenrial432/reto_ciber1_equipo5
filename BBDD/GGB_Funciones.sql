-- Funcion para generar un dni aleatorio 

drop function if exists generar_dni;

delimiter $$
create function generar_dni() returns varchar(9)
begin

	declare num int;
    declare letra char;
    declare dni varchar(9);
    declare num_letra int;
    set num=(select round(rand()*99999999));
    set num_letra=(select round(rand()*27));
    set letra=(select letra_dni from bda_letraDni where id_num=num_letra);
    set dni=concat(num,letra);
    
    return dni;

end $$
delimiter ;


-- El siguiente trigger inserta un dni antes de la inserccion del resto de datos en la tabla bda_Empleados

drop trigger if exists insert_dni;
delimiter $$
create trigger inser_dni before insert on bda_Empleados for each row
begin
	declare num int;
	declare user_dni varchar(9);
    set user_dni=(select generar_dni());
    set num=(select id_empleado from bda_Empleados having max(id_empleado));
    insert into bda_Empleados (id_empleado, DNI) values(num+1, user_dni);


end $$
delimiter ;


-- El siguiente trigger inserta en la tabla de wggbt_users los datos recogidos de la tabla bda_Empleados
drop trigger if exists new_empleado;
delimiter $$
create trigger new_empleado after insert on bda_Empleados for each row
begin
	
    declare num int;
	declare usuario varchar (60);
    declare contra_hash varchar(255);
    declare correo varchar(100);
    declare url varchar(100);
    
    set num=(select id_user from bda_Empleados having max(id_user));
    set usuario=(select Username from bda_Empleados having max(id_user));
    set contra_hash=(select Password_hash from bda_Empleados having max(id_user));
    set correo=(select e_mail from bda_Empleados having max(id_user));
    set url="http://www.ciber_wordpress.com";
    
    insert into wggbt_users (ID, user_login, user_pass, user_nicename, user_email, user_url, user_registered, user_status, display_name) values
		(num, usuario, contra_hash, usuario, correo, url, now(), 0, usuario);
	

end $$
delimiter ;

drop trigger if exists set_fecha;
delimiter $$

create trigger set_fecha after insert on wggbt_users for each row
begin
	declare fecha datetime;
    set fecha=(select user_registered from wggbt_users having max(ID));
    
    update bda_Empleados set Fecha_reg=fecha where Id_user=last_insert_id();



end $$
delimiter ;



INSERT INTO bda_Empleados (DNI, Nombre, Apellido, e_mail, Username, Password_hash, Departamento_Id, Id_rol ) VALUES ('7899t', 'prueba', 'prueba', 'prueba@prueba.com', 'user_prueba', '$2y$10$Sw0Ouk1lf5okOMVIqONL4udD7OxArqowENY1LTJGV/tk9Ut8aeUf6', 2, 2);


