
use master
go

drop table if exists dbo.actividades_personas
drop table if exists dbo.equipos_personas
drop table if exists dbo.personas
drop table if exists dbo.actividades
drop table if exists dbo.equipos
drop table if exists dbo.generos
drop table if exists dbo.nacionalidades
go

/* ------------------------------------------
   Tabla: nacionalidades
   ------------------------------------------ */
create table dbo.nacionalidades
(
 cod_nacionalidad		varchar(3)			not null
						constraint PK__nacionalidades___END primary key,
 nom_nacionalidad		varchar(50)			not null
)
go

insert into dbo.nacionalidades(cod_nacionalidad, nom_nacionalidad)
values('AR', 'ARGENTINA'),
	  ('BO', 'BOLIVIANA'),
	  ('BR', 'BRASILEÑA'),
	  ('CH', 'CHILENA'),
	  ('CO', 'COLOMBIANA'),
	  ('EC', 'ECUATORIANA'),
	  ('PE', 'PERUANA'),
	  ('PY', 'PARAGUAYA'),
	  ('UY', 'URUGUAYA'),
	  ('VE', 'VENEZOLANA')
go

/* ------------------------------------------
   Tabla: generos
   ------------------------------------------ */
create table dbo.generos
(
 cod_genero				varchar(2)			not null
						constraint PK__generos__END primary key,
 nom_genero				varchar(20)			not null
)	
go

insert into dbo.generos(cod_genero, nom_genero)
values('F', 'Femenino'),
      ('M', 'Masculino'),
	  ('X', 'No binario')
go

/* ------------------------------------------
   Tabla: equipos
   ------------------------------------------ */
create table dbo.equipos
(
 nro_equipo				smallint			not null
						constraint PK__equipos__END primary key,
 nom_equipo				varchar(50)			not null
)
go

insert into dbo.equipos(nro_equipo, nom_equipo)
values(1, 'ASOCIACIÓN ATLÉTICA ARGENTINOS JUNIORS'),
      (2, 'CLUB ATLÉTICO BELGRANO DE CÓRDOBA'),
      (3, 'CLUB ATLÉTICO BOCA JUNIOR'),
	  (4, 'CLUB ATLÉTICO INDEPENDIENTE'),
	  (5, 'INSTITUTO ATLÉTICO CENTRAL CÓRDOBA'),
	  (6, 'RACING CLUB'),
	  (7, 'CLUB ATLÉTICO RIVER PLATE'),
	  (8, 'CLUB ATLÉTICO SAN LORENZO DE ALMAGRO'),
	  (9, 'CLUB ATLÉTICO TALLERES'),
	  (10, 'CLUB ATLÉTICO VELEZ SARSFIELD')
go



/* ------------------------------------------
   Tabla: actividades
   ------------------------------------------ */
create table dbo.actividades
(
 nro_actividad				smallint		not null
							constraint PK__actividades__END primary key,
 nom_actividad				varchar(40)		not null
)
go

insert into dbo.actividades(nro_actividad, nom_actividad)
values(1, 'Bailar'),
      (2, 'Cantar'),
      (3, 'Hacer deportes'),
      (4, 'Leer'),
      (5, 'Meditar'),
      (6, 'Pescar'),
      (7, 'Ver televisión')
go

/* ------------------------------------------
   Tabla: personas
   ------------------------------------------ */
create table dbo.personas
(
 nro_persona				integer			not null identity
							constraint PK__personas__END primary key,
 apellido					varchar(40)		not null,
 nombre						varchar(100)	not null,
 clave						varchar(20)		not null,
 correo						varchar(255)	not null,
 cod_genero					varchar(2)		null
							constraint FK__personas__generos__END references dbo.generos,
 fecha_nacimiento			date			null,
 cod_nacionalidad			varchar(3)		null
							constraint FK__personas__nacionalidades__END references dbo.nacionalidades,
)
go

/* ------------------------------------------
   Tabla: equipos_personas
   ------------------------------------------ */
create table dbo.equipos_personas
(
 nro_persona				integer			not null
							constraint FK__equipos_personas__personas__END references dbo.personas,
 nro_equipo					smallint		not null
							constraint FK__equipos_personas__equipos__END references dbo.equipos,
 constraint PK__equipos_personas__END 
			primary key (nro_persona, nro_equipo)
)
go

/* ------------------------------------------
   Tabla: actividades_personas
   ------------------------------------------ */
create table dbo.actividades_personas
(
 nro_persona				integer			not null
							constraint FK__actividades_personas__personas__END references dbo.personas,
 nro_actividad				smallint		not null
							constraint FK__actividades_personas__actividades__END references dbo.actividades,
 constraint PK__actividades_personas__END 
			primary key (nro_persona, nro_actividad)
)
go

/* ------------------------------------------
   Procedimientos: get_nacionalidades
   ------------------------------------------ */
create or alter procedure dbo.get_nacionalidades
as
begin

  select cod_nacionalidad,
         nom_nacionalidad
    from dbo.nacionalidades (nolock)
   order by nom_nacionalidad

end
go

-- execute dbo.get_nacionalidades

/* ------------------------------------------
   Procedimientos: get_generos
   ------------------------------------------ */
create or alter procedure dbo.get_generos
as
begin

  select cod_genero,
         nom_genero
    from dbo.generos (nolock)
   order by nom_genero

end
go

-- execute dbo.get_generos

/* ------------------------------------------
   Procedimientos: get_equipos
   ------------------------------------------ */
create or alter procedure dbo.get_equipos
as
begin

  select nro_equipo,
         nom_equipo
    from dbo.equipos (nolock)
   order by nom_equipo

end
go

-- execute dbo.get_equipos

/* ------------------------------------------
   Procedimientos: get_actividades
   ------------------------------------------ */
create or alter procedure dbo.get_actividades
as
begin

  select nro_actividad,
         nom_actividad
    from dbo.actividades (nolock)
   order by nom_actividad

end
go

-- execute dbo.get_actividades

/* ------------------------------------------
   Procedimientos: ins_persona
   ------------------------------------------ */
create or alter procedure dbo.ins_persona
(
 @apellido			varchar(40),
 @nombre			varchar(100),
 @clave				varchar(20),
 @correo			varchar(255),
 @cod_genero		varchar(2)		= null,
 @fecha_nacimiento	date			= null,
 @cod_nacionalidad	varchar(3)		= null,
 @equipos			varchar(4000)	= null,
 @actividades		varchar(4000)	= null
)
as
begin

  declare @nro_persona integer

  declare @eq table
  (
   nro_equipo		smallint
  )

  declare @act table
  (
   nro_actividad	smallint
  )

  insert into @eq(nro_equipo)
  select nro_equipo
    from openjson(@equipos) 
    with(
	     nro_equipo		smallint '$'
        ) 

  insert into @act(nro_actividad)
  select nro_actividad
    from openjson(@actividades)
    with(
	     nro_actividad	smallint '$'
        ) 

  insert into dbo.personas(apellido, nombre, clave, correo, cod_genero, fecha_nacimiento, cod_nacionalidad)
  values(@apellido, @nombre, @clave, @correo, @cod_genero, @fecha_nacimiento, @cod_nacionalidad)

  set @nro_persona = @@identity

  insert into dbo.equipos_personas(nro_persona, nro_equipo)
  select @nro_persona,
         nro_equipo
	from @eq

  insert into dbo.actividades_personas(nro_persona, nro_actividad)
  select @nro_persona,
         nro_actividad
	from @act

end
go

-- execute dbo.ins_persona @apellido='APELLIDO', @nombre='NOMBRE', @clave='123456', @correo='apenom@ubp.edu.ar', @cod_genero='F', @cod_nacionalidad='AR', @fecha_nacimiento='01 jan 1900', @equipos='[2,3]', @actividades='[1,2]'

/* ------------------------------------------
   Procedimientos: get_personas
   ------------------------------------------ */
create or alter procedure dbo.get_personas
(
 @string_busqueda	varchar(255)	= null
)
as
begin

  set @string_busqueda = '%' + isnull(ltrim(rtrim(@string_busqueda)), '') + '%'

  select p.nro_persona,
       p.apellido,
       p.nombre,
		 p.clave,
		 p.correo,
		 g.nom_genero,
		 p.cod_genero,
		 p.fecha_nacimiento,
		 n.nom_nacionalidad,
		 p.cod_nacionalidad,
		 p.nro_persona
    from dbo.personas p (nolock)
	     left join dbo.generos g (nolock)
		   on p.cod_genero       = g.cod_genero
		 left join dbo.nacionalidades n (nolock)
		   on p.cod_nacionalidad = n.cod_nacionalidad
   where (apellido                 like @string_busqueda
      or  nombre                   like @string_busqueda
	  or  apellido + ', ' + nombre like @string_busqueda
	  or  apellido + ' ' + nombre  like @string_busqueda
	  or  nombre + ' ' + apellido  like @string_busqueda)	
   order by apellido,
            nombre

end
go

-- execute dbo.get_personas

/* ------------------------------------------
   Procedimientos: get_equipos_persona
   ------------------------------------------ */
create or alter procedure dbo.get_equipos_persona
(
 @nro_persona	integer
)
as
begin

  select e.nom_equipo,
         ep.nro_equipo
	from dbo.equipos_personas ep (nolock)	
         join dbo.equipos e (nolock)
		   on ep.nro_equipo = e.nro_equipo
   where ep.nro_persona = @nro_persona

end
go

-- Procedimiento almacenado para borrar una persona

create or alter procedure dbo.delete_persona
(
  @nro_persona	integer
)
as
begin
  declare @equipos varchar(4000);
  declare @actividades varchar(4000);

  select @equipos = STRING_AGG(CAST(nro_equipo as varchar), ',') 
  from dbo.equipos_personas
  where nro_persona = @nro_persona;

  select @actividades = STRING_AGG(CAST(nro_actividad as varchar), ',') 
  from dbo.actividades_personas
  where nro_persona = @nro_persona;

  delete from dbo.equipos_personas where nro_persona = @nro_persona;

  delete from dbo.actividades_personas where nro_persona = @nro_persona;
  
  delete from dbo.personas
  output 
    deleted.nombre, 
    deleted.apellido, 
    deleted.clave, 
    deleted.correo, 
    deleted.cod_genero, 
    deleted.fecha_nacimiento, 
    deleted.cod_nacionalidad, 
    @equipos as equipos, 
    @actividades as actividades
  where nro_persona = @nro_persona;

end
go


--  execute dbo.get_equipos_persona @nro_persona=1
-- INSERT INTO dbo.actividades_personas(nro_persona, nro_actividad)
-- VALUES (1, 3);

/* ------------------------------------------
   Procedimientos: get_actividades_persona
   ------------------------------------------ */
create or alter procedure dbo.get_actividades_persona
(
 @nro_persona	integer
)
as
begin

  select a.nom_actividad,
         ap.nro_actividad
	from dbo.actividades_personas ap (nolock)	
         join dbo.actividades a (nolock)
		   on ap.nro_actividad = a.nro_actividad
   where ap.nro_persona = @nro_persona

end
go

execute dbo.get_actividades_persona @nro_persona=1



-- Insertar una persona llamada Juan Pérez
execute dbo.ins_persona 
    @apellido='Pérez', 
    @nombre='Juan', 
    @clave='claveJuan123', 
    @correo='juan.perez@ubp.edu.ar', 
    @cod_genero='M', 
    @cod_nacionalidad='AR', 
    @fecha_nacimiento='1990-05-15', 
    @equipos='[1,3]', 
    @actividades='[1,4]';

-- Insertar una persona llamada María González
execute dbo.ins_persona 
    @apellido='González', 
    @nombre='María', 
    @clave='claveMaria456', 
    @correo='maria.gonzalez@ubp.edu.ar', 
    @cod_genero='F', 
    @cod_nacionalidad='BO', 
    @fecha_nacimiento='1992-04-12', 
    @equipos='[4,5]', 
    @actividades='[2,5]';

-- Insertar una persona llamada Ana Rodríguez
execute dbo.ins_persona 
    @apellido='Rodríguez', 
    @nombre='Ana', 
    @clave='claveAna789', 
    @correo='ana.rodriguez@ubp.edu.ar', 
    @cod_genero='F', 
    @cod_nacionalidad='CH', 
    @fecha_nacimiento='1995-07-21', 
    @equipos='[7,10]', 
    @actividades='[3,7]';



EXEC dbo.delete_persona @nro_persona = 6

select * from personas

-- Ejemplo de cómo cambiar una clave foránea existente para agregar ON DELETE CASCADE
-- Repite este proceso para todas las claves foráneas que referencian a personas
