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
    
    set num=(select id_empleado from bda_Empleados having max(id_empleado));
    set usuario=(select Username from bda_Empleados having max(id_empleado));
    set contra_hash=(select Password_hash from bda_Empleados having max(id_empleado));
    set correo=(select e_mail from bda_Empleados having max(id_empleado));
    set url="http://www.ciber_wordpress.com";
    
    insert into wggbt_users (ID, user_login, user_pass, user_nicename, user_email, user_url, user_registered, user_status, display_name) values
		(num, usuario, contra_hash, usuario, correo, url, now(), 0, usuario);
	

end $$
delimiter ;


select user_login from wggbt_users having max(ID);

delete from bda_Empleados where Id_rol=2;

select substring(user_email, locate(user_email, ".")+1 ) from wggbt_users having  max(ID);

insert into bda_Empleados  values (
	(select generar_dni()),
    'Piko',
    'Palos',
    'piko.palos@wordpress',
    'user_piko',
    sha1('Pass_piko123'),
    now(),
    2,
    2

);

create table puto_email (

	id_email int primary key auto_increment,
    email varchar (100)


);

insert into puto_email (email) values ('oier.garcia@maristak.com');

select * from puto_email;


