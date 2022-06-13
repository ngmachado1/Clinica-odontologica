create table if not exists odontologos(
    id int auto_increment primary key,
    numero_matricula int,
    nombre varchar(255),
    apellido varchar(255)
);

create table if not exists pacientes(
    id int auto_increment primary key,
    nombre varchar(255),
    apellido varchar(255),
    domicilio_id int,
    fecha_ingreso timeStamp
);

create table if not exists domicilios(
    id int auto_increment primary key,
    numero varchar(255),
    calle varchar(255),
    localidad varchar(255),
    provincia varchar(255)
);

