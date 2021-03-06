PGDMP     5                     x            RentACar    12.1    12.0 2    S           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            T           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            U           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            V           1262    16580    RentACar    DATABASE     �   CREATE DATABASE "RentACar" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Ecuador.1252' LC_CTYPE = 'Spanish_Ecuador.1252';
    DROP DATABASE "RentACar";
                postgres    false            �            1259    16683    cargo    TABLE     �   CREATE TABLE public.cargo (
    codigo_cargo integer NOT NULL,
    descripcion character varying(50),
    cargo_administrador_sistema integer
);
    DROP TABLE public.cargo;
       public         heap    postgres    false            �            1259    16681    cargo_codigo_cargo_seq    SEQUENCE     �   CREATE SEQUENCE public.cargo_codigo_cargo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.cargo_codigo_cargo_seq;
       public          postgres    false    213            W           0    0    cargo_codigo_cargo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.cargo_codigo_cargo_seq OWNED BY public.cargo.codigo_cargo;
          public          postgres    false    212            �            1259    16584    clientes    TABLE     E  CREATE TABLE public.clientes (
    cedula numeric(10,0) NOT NULL,
    nombre_cliente character varying(50) NOT NULL,
    direccion_cliente character varying(50) NOT NULL,
    correo_electronico_cliente character varying(50) NOT NULL,
    contrasena_cliente character varying(50) NOT NULL,
    estado_registro character(1)
);
    DROP TABLE public.clientes;
       public         heap    postgres    false            �            1259    16587    color    TABLE     r   CREATE TABLE public.color (
    id_color numeric(2,0) NOT NULL,
    descripcion character varying(50) NOT NULL
);
    DROP TABLE public.color;
       public         heap    postgres    false            �            1259    16590 	   empleados    TABLE     Q  CREATE TABLE public.empleados (
    id_empleado numeric(10,0) NOT NULL,
    nombre_empleado character varying(50) NOT NULL,
    direccion_empleado character varying(50) NOT NULL,
    correo_electronico_empleado character varying(50) NOT NULL,
    contrasena_empleado character varying(50) NOT NULL,
    empleadocargo integer NOT NULL
);
    DROP TABLE public.empleados;
       public         heap    postgres    false            �            1259    16593    marcas    TABLE     t   CREATE TABLE public.marcas (
    id_marcas numeric(1,0) NOT NULL,
    descripcion character varying(50) NOT NULL
);
    DROP TABLE public.marcas;
       public         heap    postgres    false            �            1259    16596    permiso    TABLE     u   CREATE TABLE public.permiso (
    codigo_permiso integer NOT NULL,
    descripcion character varying(50) NOT NULL
);
    DROP TABLE public.permiso;
       public         heap    postgres    false            �            1259    16599    ticket    TABLE       CREATE TABLE public.ticket (
    id_ticket numeric(5,0) NOT NULL,
    fecha_salida date NOT NULL,
    hora_salida time without time zone NOT NULL,
    fecha_entrega date NOT NULL,
    pago_alquiler numeric(5,2) NOT NULL,
    empleado numeric(10,0) NOT NULL,
    cliente numeric(10,0) NOT NULL,
    tipo_pago numeric(1,0) NOT NULL,
    vehiculo_placa character varying(8) NOT NULL
);
    DROP TABLE public.ticket;
       public         heap    postgres    false            �            1259    16602    tipo_combustible    TABLE     �   CREATE TABLE public.tipo_combustible (
    id_tipo_combustible numeric(1,0) NOT NULL,
    descripcion character varying(50) NOT NULL
);
 $   DROP TABLE public.tipo_combustible;
       public         heap    postgres    false            �            1259    16605 
   tipos_pago    TABLE     {   CREATE TABLE public.tipos_pago (
    id_tipo_pago numeric(1,0) NOT NULL,
    descripcion character varying(50) NOT NULL
);
    DROP TABLE public.tipos_pago;
       public         heap    postgres    false            �            1259    16608    tipos_vehiculos    TABLE     �   CREATE TABLE public.tipos_vehiculos (
    id_tipos_vehiculos numeric(1,0) NOT NULL,
    descripcion character varying(50) NOT NULL
);
 #   DROP TABLE public.tipos_vehiculos;
       public         heap    postgres    false            �            1259    16611 	   vehiculos    TABLE       CREATE TABLE public.vehiculos (
    numero_placa character varying(8) NOT NULL,
    precio_actual numeric(10,2) NOT NULL,
    marcas numeric(1,0) NOT NULL,
    tipos_combustible numeric(1,0) NOT NULL,
    tipos_vehiculos numeric(1,0) NOT NULL,
    color numeric(2,0) NOT NULL
);
    DROP TABLE public.vehiculos;
       public         heap    postgres    false            �
           2604    16686    cargo codigo_cargo    DEFAULT     x   ALTER TABLE ONLY public.cargo ALTER COLUMN codigo_cargo SET DEFAULT nextval('public.cargo_codigo_cargo_seq'::regclass);
 A   ALTER TABLE public.cargo ALTER COLUMN codigo_cargo DROP DEFAULT;
       public          postgres    false    212    213    213            P          0    16683    cargo 
   TABLE DATA           W   COPY public.cargo (codigo_cargo, descripcion, cargo_administrador_sistema) FROM stdin;
    public          postgres    false    213   �=       E          0    16584    clientes 
   TABLE DATA           �   COPY public.clientes (cedula, nombre_cliente, direccion_cliente, correo_electronico_cliente, contrasena_cliente, estado_registro) FROM stdin;
    public          postgres    false    202   �=       F          0    16587    color 
   TABLE DATA           6   COPY public.color (id_color, descripcion) FROM stdin;
    public          postgres    false    203   ?       G          0    16590 	   empleados 
   TABLE DATA           �   COPY public.empleados (id_empleado, nombre_empleado, direccion_empleado, correo_electronico_empleado, contrasena_empleado, empleadocargo) FROM stdin;
    public          postgres    false    204   3?       H          0    16593    marcas 
   TABLE DATA           8   COPY public.marcas (id_marcas, descripcion) FROM stdin;
    public          postgres    false    205   �?       I          0    16596    permiso 
   TABLE DATA           >   COPY public.permiso (codigo_permiso, descripcion) FROM stdin;
    public          postgres    false    206   �?       J          0    16599    ticket 
   TABLE DATA           �   COPY public.ticket (id_ticket, fecha_salida, hora_salida, fecha_entrega, pago_alquiler, empleado, cliente, tipo_pago, vehiculo_placa) FROM stdin;
    public          postgres    false    207   �?       K          0    16602    tipo_combustible 
   TABLE DATA           L   COPY public.tipo_combustible (id_tipo_combustible, descripcion) FROM stdin;
    public          postgres    false    208   @       L          0    16605 
   tipos_pago 
   TABLE DATA           ?   COPY public.tipos_pago (id_tipo_pago, descripcion) FROM stdin;
    public          postgres    false    209   1@       M          0    16608    tipos_vehiculos 
   TABLE DATA           J   COPY public.tipos_vehiculos (id_tipos_vehiculos, descripcion) FROM stdin;
    public          postgres    false    210   N@       N          0    16611 	   vehiculos 
   TABLE DATA           s   COPY public.vehiculos (numero_placa, precio_actual, marcas, tipos_combustible, tipos_vehiculos, color) FROM stdin;
    public          postgres    false    211   k@       X           0    0    cargo_codigo_cargo_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.cargo_codigo_cargo_seq', 4, true);
          public          postgres    false    212            �
           2606    16688    cargo cargo_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (codigo_cargo);
 :   ALTER TABLE ONLY public.cargo DROP CONSTRAINT cargo_pkey;
       public            postgres    false    213            �
           2606    16617    clientes clientes_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (cedula);
 @   ALTER TABLE ONLY public.clientes DROP CONSTRAINT clientes_pkey;
       public            postgres    false    202            �
           2606    16619    color color_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.color
    ADD CONSTRAINT color_pkey PRIMARY KEY (id_color);
 :   ALTER TABLE ONLY public.color DROP CONSTRAINT color_pkey;
       public            postgres    false    203            �
           2606    16621    empleados empleados_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_pkey PRIMARY KEY (id_empleado);
 B   ALTER TABLE ONLY public.empleados DROP CONSTRAINT empleados_pkey;
       public            postgres    false    204            �
           2606    16623    marcas marcas_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.marcas
    ADD CONSTRAINT marcas_pkey PRIMARY KEY (id_marcas);
 <   ALTER TABLE ONLY public.marcas DROP CONSTRAINT marcas_pkey;
       public            postgres    false    205            �
           2606    16625    permiso permiso_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT permiso_pkey PRIMARY KEY (codigo_permiso);
 >   ALTER TABLE ONLY public.permiso DROP CONSTRAINT permiso_pkey;
       public            postgres    false    206            �
           2606    16627    ticket ticket_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_pkey PRIMARY KEY (id_ticket);
 <   ALTER TABLE ONLY public.ticket DROP CONSTRAINT ticket_pkey;
       public            postgres    false    207            �
           2606    16629 &   tipo_combustible tipo_combustible_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public.tipo_combustible
    ADD CONSTRAINT tipo_combustible_pkey PRIMARY KEY (id_tipo_combustible);
 P   ALTER TABLE ONLY public.tipo_combustible DROP CONSTRAINT tipo_combustible_pkey;
       public            postgres    false    208            �
           2606    16631    tipos_pago tipos_pago_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.tipos_pago
    ADD CONSTRAINT tipos_pago_pkey PRIMARY KEY (id_tipo_pago);
 D   ALTER TABLE ONLY public.tipos_pago DROP CONSTRAINT tipos_pago_pkey;
       public            postgres    false    209            �
           2606    16633 $   tipos_vehiculos tipos_vehiculos_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.tipos_vehiculos
    ADD CONSTRAINT tipos_vehiculos_pkey PRIMARY KEY (id_tipos_vehiculos);
 N   ALTER TABLE ONLY public.tipos_vehiculos DROP CONSTRAINT tipos_vehiculos_pkey;
       public            postgres    false    210            �
           2606    16635    vehiculos vehiculos_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT vehiculos_pkey PRIMARY KEY (numero_placa);
 B   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT vehiculos_pkey;
       public            postgres    false    211            �
           2606    16694    empleados empleadocargo_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleadocargo_fk FOREIGN KEY (empleadocargo) REFERENCES public.cargo(codigo_cargo);
 D   ALTER TABLE ONLY public.empleados DROP CONSTRAINT empleadocargo_fk;
       public          postgres    false    213    2749    204            �
           2606    16641    ticket fk_ticket_to_clientes    FK CONSTRAINT     �   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_ticket_to_clientes FOREIGN KEY (cliente) REFERENCES public.clientes(cedula);
 F   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_ticket_to_clientes;
       public          postgres    false    207    202    2729            �
           2606    16646    ticket fk_ticket_to_empleados    FK CONSTRAINT     �   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_ticket_to_empleados FOREIGN KEY (empleado) REFERENCES public.empleados(id_empleado);
 G   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_ticket_to_empleados;
       public          postgres    false    2733    204    207            �
           2606    16651    ticket fk_ticket_to_tipos_pago    FK CONSTRAINT     �   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_ticket_to_tipos_pago FOREIGN KEY (tipo_pago) REFERENCES public.tipos_pago(id_tipo_pago);
 H   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_ticket_to_tipos_pago;
       public          postgres    false    209    207    2743            �
           2606    16656    ticket fk_ticket_to_vehiculos    FK CONSTRAINT     �   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_ticket_to_vehiculos FOREIGN KEY (vehiculo_placa) REFERENCES public.vehiculos(numero_placa);
 G   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_ticket_to_vehiculos;
       public          postgres    false    2747    211    207            �
           2606    16661    vehiculos fk_vehiculos_to_color    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT fk_vehiculos_to_color FOREIGN KEY (color) REFERENCES public.color(id_color);
 I   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT fk_vehiculos_to_color;
       public          postgres    false    2731    203    211            �
           2606    16666     vehiculos fk_vehiculos_to_marcas    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT fk_vehiculos_to_marcas FOREIGN KEY (marcas) REFERENCES public.marcas(id_marcas);
 J   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT fk_vehiculos_to_marcas;
       public          postgres    false    211    2735    205            �
           2606    16671 *   vehiculos fk_vehiculos_to_tipo_combustible    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT fk_vehiculos_to_tipo_combustible FOREIGN KEY (tipos_combustible) REFERENCES public.tipo_combustible(id_tipo_combustible);
 T   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT fk_vehiculos_to_tipo_combustible;
       public          postgres    false    208    211    2741            �
           2606    16676 )   vehiculos fk_vehiculos_to_tipos_vehiculos    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT fk_vehiculos_to_tipos_vehiculos FOREIGN KEY (tipos_vehiculos) REFERENCES public.tipos_vehiculos(id_tipos_vehiculos);
 S   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT fk_vehiculos_to_tipos_vehiculos;
       public          postgres    false    210    2745    211            P   G   x�3�tL����,.)JL�/�4�2�t��+s��9�R�S�3��<N�ԢԒT�`�����b��=... ���      E   /  x��ѽn! ���)�`��g���H.�$RrMշ�OU*UU�����0�L<�ڹݦ�0��}�v^��<6�w'��^NCV��U � =�9�UCB�N��ۑ)���?�����6ȭ�c�&���޴�В��N0��R�3A3(H��K�.׶��~�"��-��m�i�����G���`��z�Ź��*I��5#�}Y�)���)f3M3��Gv���l]��j��QSƖ" �|���}�H���te��"�i���e��L�
E}%G͡�]jO�9EUT�� �܎/�q? ̶�H      F      x������ � �      G   z   x�M���0 ��+���v��GT�#$6��(����n'�r�ȇ�؜�]���{���(z0��Iˡ�΀�� R�֤�W;]�n���r�/b��d�]�� �����` �51���|L��/�%�      H      x������ � �      I      x������ � �      J      x������ � �      K      x������ � �      L      x������ � �      M      x������ � �      N      x������ � �     