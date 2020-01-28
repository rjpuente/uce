-- =============================================================================
-- diagram name: noname1
-- created on: 7/12/2019 17:33:46
-- diagram version: 
-- =============================================================================


create table "tipo_combustible" (
	"id_tipo_combustible" numeric(1) not null,
	"descripcion" varchar(50) not null,
	primary key("id_tipo_combustible")
);

create table "tipos_vehiculos" (
	"id_tipos_vehiculos" numeric(1) not null,
	"descripcion" varchar(50) not null,
	primary key("id_tipos_vehiculos")
);

create table "marcas" (
	"id_marcas" numeric(1) not null,
	"descripcion" varchar(50) not null,
	primary key("id_marcas")
);

create table "ticket" (
	"id_ticket" numeric(5) not null,
	"fecha_salida" date not null,
	"hora_salida" time not null,
	"fecha_entrega" date not null,
	"pago_alquiler" numeric(5,2) not null,
	"empleado" numeric(10) not null,
	"cliente" numeric(10) not null,
	"tipo_pago" numeric(1) not null,
	"vehiculo_placa" varchar(8) not null,
	primary key("id_ticket")
);

create table "vehiculos" (
	"numero_placa" varchar(8) not null,
	"precio_actual" numeric(10,2) not null,
	"marcas" numeric(1) not null,
	"tipos_combustible" numeric(1) not null,
	"tipos_vehiculos" numeric(1) not null,
	"color" numeric(2) not null,
	primary key("numero_placa")
);

create table "color" (
	"id_color" numeric(2) not null,
	"descripcion" varchar(50) not null,
	primary key("id_color")
);

create table "empleados" (
	"id_empleado" numeric(10) not null,
	"nombre_empleado" varchar(50) not null,
	"direccion_empleado" varchar(50) not null,
	"correo_electronico_empleado" varchar(50) not null,
	primary key("id_empleado")
);

create table "clientes" (
	"cedula" numeric(10) not null,
	"nombre_cliente" varchar(50) not null,
	"direccion_cliente" varchar(50) not null,
	"correo_electronico_cliente" varchar(50) not null,
	primary key("cedula")
);

create table "tipos_pago" (
	"id_tipo_pago" numeric(1) not null,
	"descripcion" varchar(50) not null,
	primary key("id_tipo_pago")
);


alter table "ticket" add constraint "fk_ticket_to_vehiculos" foreign key ("vehiculo_placa")
	references "vehiculos"("numero_placa")
	match simple
	on delete no action
	on update no action
	not deferrable;

alter table "ticket" add constraint "fk_ticket_to_empleados" foreign key ("empleado")
	references "empleados"("id_empleado")
	match simple
	on delete no action
	on update no action
	not deferrable;

alter table "ticket" add constraint "fk_ticket_to_clientes" foreign key ("cliente")
	references "clientes"("cedula")
	match simple
	on delete no action
	on update no action
	not deferrable;

alter table "ticket" add constraint "fk_ticket_to_tipos_pago" foreign key ("tipo_pago")
	references "tipos_pago"("id_tipo_pago")
	match simple
	on delete no action
	on update no action
	not deferrable;

alter table "vehiculos" add constraint "fk_vehiculos_to_tipo_combustible" foreign key ("tipos_combustible")
	references "tipo_combustible"("id_tipo_combustible")
	match simple
	on delete no action
	on update no action
	not deferrable;

alter table "vehiculos" add constraint "fk_vehiculos_to_marcas" foreign key ("marcas")
	references "marcas"("id_marcas")
	match simple
	on delete no action
	on update no action
	not deferrable;

alter table "vehiculos" add constraint "fk_vehiculos_to_tipos_vehiculos" foreign key ("tipos_vehiculos")
	references "tipos_vehiculos"("id_tipos_vehiculos")
	match simple
	on delete no action
	on update no action
	not deferrable;

alter table "vehiculos" add constraint "fk_vehiculos_to_color" foreign key ("color")
	references "color"("id_color")
	match simple
	on delete no action
	on update no action
	not deferrable;

