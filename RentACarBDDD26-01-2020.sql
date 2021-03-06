PGDMP     -    !                 x            RentACar    12.0    12.0 J    l           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            m           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            n           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            o           1262    16884    RentACar    DATABASE     �   CREATE DATABASE "RentACar" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Ecuador.1252' LC_CTYPE = 'Spanish_Ecuador.1252';
    DROP DATABASE "RentACar";
                postgres    false            �            1259    16885    cargo    TABLE     �   CREATE TABLE public.cargo (
    codigo_cargo integer NOT NULL,
    descripcion character varying(50),
    cargo_administrador_sistema integer
);
    DROP TABLE public.cargo;
       public         heap    postgres    false            �            1259    16888    cargo_codigo_cargo_seq    SEQUENCE     �   CREATE SEQUENCE public.cargo_codigo_cargo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.cargo_codigo_cargo_seq;
       public          postgres    false    202            p           0    0    cargo_codigo_cargo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.cargo_codigo_cargo_seq OWNED BY public.cargo.codigo_cargo;
          public          postgres    false    203            �            1259    16890    clientes    TABLE     E  CREATE TABLE public.clientes (
    cedula numeric(10,0) NOT NULL,
    nombre_cliente character varying(50) NOT NULL,
    direccion_cliente character varying(50) NOT NULL,
    correo_electronico_cliente character varying(50) NOT NULL,
    contrasena_cliente character varying(50) NOT NULL,
    estado_registro character(1)
);
    DROP TABLE public.clientes;
       public         heap    postgres    false            �            1259    17016    color    TABLE     d   CREATE TABLE public.color (
    id_color integer NOT NULL,
    descripcion character varying(50)
);
    DROP TABLE public.color;
       public         heap    postgres    false            �            1259    17014    color_id_color_seq    SEQUENCE     �   CREATE SEQUENCE public.color_id_color_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.color_id_color_seq;
       public          postgres    false    213            q           0    0    color_id_color_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.color_id_color_seq OWNED BY public.color.id_color;
          public          postgres    false    212            �            1259    16896 	   empleados    TABLE     t  CREATE TABLE public.empleados (
    id_empleado numeric(10,0) NOT NULL,
    nombre_empleado character varying(50) NOT NULL,
    direccion_empleado character varying(50) NOT NULL,
    correo_electronico_empleado character varying(50) NOT NULL,
    contrasena_empleado character varying(50) NOT NULL,
    empleadocargo integer NOT NULL,
    estado_empelados character(1)
);
    DROP TABLE public.empleados;
       public         heap    postgres    false            �            1259    16990    marcas    TABLE     f   CREATE TABLE public.marcas (
    id_marcas integer NOT NULL,
    descripcion character varying(50)
);
    DROP TABLE public.marcas;
       public         heap    postgres    false            �            1259    16988    marcas_id_marcas_seq    SEQUENCE     �   CREATE SEQUENCE public.marcas_id_marcas_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.marcas_id_marcas_seq;
       public          postgres    false    209            r           0    0    marcas_id_marcas_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.marcas_id_marcas_seq OWNED BY public.marcas.id_marcas;
          public          postgres    false    208            �            1259    16902    permiso    TABLE     u   CREATE TABLE public.permiso (
    codigo_permiso integer NOT NULL,
    descripcion character varying(50) NOT NULL
);
    DROP TABLE public.permiso;
       public         heap    postgres    false            �            1259    17052    ticket    TABLE     I  CREATE TABLE public.ticket (
    id_ticket integer NOT NULL,
    fecha_salida date NOT NULL,
    fecha_entrega date NOT NULL,
    pago_alquiler numeric(5,2),
    cliente numeric(10,0),
    tipo_pago integer NOT NULL,
    vehiculo_placa character varying(8),
    empleado numeric(10,0),
    estado_ticket character(1) NOT NULL
);
    DROP TABLE public.ticket;
       public         heap    postgres    false            �            1259    17050    ticket_id_ticket_seq    SEQUENCE     �   CREATE SEQUENCE public.ticket_id_ticket_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.ticket_id_ticket_seq;
       public          postgres    false    217            s           0    0    ticket_id_ticket_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.ticket_id_ticket_seq OWNED BY public.ticket.id_ticket;
          public          postgres    false    216            �            1259    17034    tipo_combustible    TABLE     z   CREATE TABLE public.tipo_combustible (
    id_tipo_combustible integer NOT NULL,
    descripcion character varying(50)
);
 $   DROP TABLE public.tipo_combustible;
       public         heap    postgres    false            �            1259    17032 (   tipo_combustible_id_tipo_combustible_seq    SEQUENCE     �   CREATE SEQUENCE public.tipo_combustible_id_tipo_combustible_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ?   DROP SEQUENCE public.tipo_combustible_id_tipo_combustible_seq;
       public          postgres    false    215            t           0    0 (   tipo_combustible_id_tipo_combustible_seq    SEQUENCE OWNED BY     u   ALTER SEQUENCE public.tipo_combustible_id_tipo_combustible_seq OWNED BY public.tipo_combustible.id_tipo_combustible;
          public          postgres    false    214            �            1259    17065 
   tipos_pago    TABLE     m   CREATE TABLE public.tipos_pago (
    id_tipo_pago integer NOT NULL,
    descripcion character varying(50)
);
    DROP TABLE public.tipos_pago;
       public         heap    postgres    false            �            1259    17063    tipos_pago_id_tipo_pago_seq    SEQUENCE     �   CREATE SEQUENCE public.tipos_pago_id_tipo_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.tipos_pago_id_tipo_pago_seq;
       public          postgres    false    219            u           0    0    tipos_pago_id_tipo_pago_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.tipos_pago_id_tipo_pago_seq OWNED BY public.tipos_pago.id_tipo_pago;
          public          postgres    false    218            �            1259    17003    tipos_vehiculos    TABLE     x   CREATE TABLE public.tipos_vehiculos (
    id_tipos_vehiculos integer NOT NULL,
    descripcion character varying(50)
);
 #   DROP TABLE public.tipos_vehiculos;
       public         heap    postgres    false            �            1259    17001 &   tipos_vehiculos_id_tipos_vehiculos_seq    SEQUENCE     �   CREATE SEQUENCE public.tipos_vehiculos_id_tipos_vehiculos_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.tipos_vehiculos_id_tipos_vehiculos_seq;
       public          postgres    false    211            v           0    0 &   tipos_vehiculos_id_tipos_vehiculos_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.tipos_vehiculos_id_tipos_vehiculos_seq OWNED BY public.tipos_vehiculos.id_tipos_vehiculos;
          public          postgres    false    210            �            1259    16917 	   vehiculos    TABLE     �  CREATE TABLE public.vehiculos (
    numero_placa character varying(8) NOT NULL,
    precio_actual numeric(10,2) NOT NULL,
    estado_vehiculo character(1) NOT NULL,
    "año_vehiculo" date NOT NULL,
    marcas integer NOT NULL,
    tipos_vehiculos integer NOT NULL,
    tipos_combustible integer NOT NULL,
    color integer NOT NULL,
    cantidad integer,
    imagen_vehiculo character varying(50),
    imagen_vehiculo2 character varying(50),
    imagen_vehiculo3 character varying(50)
);
    DROP TABLE public.vehiculos;
       public         heap    postgres    false            �
           2604    16920    cargo codigo_cargo    DEFAULT     x   ALTER TABLE ONLY public.cargo ALTER COLUMN codigo_cargo SET DEFAULT nextval('public.cargo_codigo_cargo_seq'::regclass);
 A   ALTER TABLE public.cargo ALTER COLUMN codigo_cargo DROP DEFAULT;
       public          postgres    false    203    202            �
           2604    17019    color id_color    DEFAULT     p   ALTER TABLE ONLY public.color ALTER COLUMN id_color SET DEFAULT nextval('public.color_id_color_seq'::regclass);
 =   ALTER TABLE public.color ALTER COLUMN id_color DROP DEFAULT;
       public          postgres    false    212    213    213            �
           2604    16993    marcas id_marcas    DEFAULT     t   ALTER TABLE ONLY public.marcas ALTER COLUMN id_marcas SET DEFAULT nextval('public.marcas_id_marcas_seq'::regclass);
 ?   ALTER TABLE public.marcas ALTER COLUMN id_marcas DROP DEFAULT;
       public          postgres    false    209    208    209            �
           2604    17055    ticket id_ticket    DEFAULT     t   ALTER TABLE ONLY public.ticket ALTER COLUMN id_ticket SET DEFAULT nextval('public.ticket_id_ticket_seq'::regclass);
 ?   ALTER TABLE public.ticket ALTER COLUMN id_ticket DROP DEFAULT;
       public          postgres    false    216    217    217            �
           2604    17037 $   tipo_combustible id_tipo_combustible    DEFAULT     �   ALTER TABLE ONLY public.tipo_combustible ALTER COLUMN id_tipo_combustible SET DEFAULT nextval('public.tipo_combustible_id_tipo_combustible_seq'::regclass);
 S   ALTER TABLE public.tipo_combustible ALTER COLUMN id_tipo_combustible DROP DEFAULT;
       public          postgres    false    215    214    215            �
           2604    17068    tipos_pago id_tipo_pago    DEFAULT     �   ALTER TABLE ONLY public.tipos_pago ALTER COLUMN id_tipo_pago SET DEFAULT nextval('public.tipos_pago_id_tipo_pago_seq'::regclass);
 F   ALTER TABLE public.tipos_pago ALTER COLUMN id_tipo_pago DROP DEFAULT;
       public          postgres    false    219    218    219            �
           2604    17006 "   tipos_vehiculos id_tipos_vehiculos    DEFAULT     �   ALTER TABLE ONLY public.tipos_vehiculos ALTER COLUMN id_tipos_vehiculos SET DEFAULT nextval('public.tipos_vehiculos_id_tipos_vehiculos_seq'::regclass);
 Q   ALTER TABLE public.tipos_vehiculos ALTER COLUMN id_tipos_vehiculos DROP DEFAULT;
       public          postgres    false    211    210    211            X          0    16885    cargo 
   TABLE DATA           W   COPY public.cargo (codigo_cargo, descripcion, cargo_administrador_sistema) FROM stdin;
    public          postgres    false    202   �Z       Z          0    16890    clientes 
   TABLE DATA           �   COPY public.clientes (cedula, nombre_cliente, direccion_cliente, correo_electronico_cliente, contrasena_cliente, estado_registro) FROM stdin;
    public          postgres    false    204   ,[       c          0    17016    color 
   TABLE DATA           6   COPY public.color (id_color, descripcion) FROM stdin;
    public          postgres    false    213   �\       [          0    16896 	   empleados 
   TABLE DATA           �   COPY public.empleados (id_empleado, nombre_empleado, direccion_empleado, correo_electronico_empleado, contrasena_empleado, empleadocargo, estado_empelados) FROM stdin;
    public          postgres    false    205   �\       _          0    16990    marcas 
   TABLE DATA           8   COPY public.marcas (id_marcas, descripcion) FROM stdin;
    public          postgres    false    209   d]       \          0    16902    permiso 
   TABLE DATA           >   COPY public.permiso (codigo_permiso, descripcion) FROM stdin;
    public          postgres    false    206   �]       g          0    17052    ticket 
   TABLE DATA           �   COPY public.ticket (id_ticket, fecha_salida, fecha_entrega, pago_alquiler, cliente, tipo_pago, vehiculo_placa, empleado, estado_ticket) FROM stdin;
    public          postgres    false    217   �]       e          0    17034    tipo_combustible 
   TABLE DATA           L   COPY public.tipo_combustible (id_tipo_combustible, descripcion) FROM stdin;
    public          postgres    false    215   �]       i          0    17065 
   tipos_pago 
   TABLE DATA           ?   COPY public.tipos_pago (id_tipo_pago, descripcion) FROM stdin;
    public          postgres    false    219   �]       a          0    17003    tipos_vehiculos 
   TABLE DATA           J   COPY public.tipos_vehiculos (id_tipos_vehiculos, descripcion) FROM stdin;
    public          postgres    false    211   ^       ]          0    16917 	   vehiculos 
   TABLE DATA           �   COPY public.vehiculos (numero_placa, precio_actual, estado_vehiculo, "año_vehiculo", marcas, tipos_vehiculos, tipos_combustible, color, cantidad, imagen_vehiculo, imagen_vehiculo2, imagen_vehiculo3) FROM stdin;
    public          postgres    false    207   <^       w           0    0    cargo_codigo_cargo_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.cargo_codigo_cargo_seq', 4, true);
          public          postgres    false    203            x           0    0    color_id_color_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.color_id_color_seq', 1, true);
          public          postgres    false    212            y           0    0    marcas_id_marcas_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.marcas_id_marcas_seq', 1, true);
          public          postgres    false    208            z           0    0    ticket_id_ticket_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.ticket_id_ticket_seq', 4, true);
          public          postgres    false    216            {           0    0 (   tipo_combustible_id_tipo_combustible_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('public.tipo_combustible_id_tipo_combustible_seq', 1, true);
          public          postgres    false    214            |           0    0    tipos_pago_id_tipo_pago_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.tipos_pago_id_tipo_pago_seq', 1, true);
          public          postgres    false    218            }           0    0 &   tipos_vehiculos_id_tipos_vehiculos_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.tipos_vehiculos_id_tipos_vehiculos_seq', 1, true);
          public          postgres    false    210            �
           2606    16922    cargo cargo_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (codigo_cargo);
 :   ALTER TABLE ONLY public.cargo DROP CONSTRAINT cargo_pkey;
       public            postgres    false    202            �
           2606    16924    clientes clientes_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (cedula);
 @   ALTER TABLE ONLY public.clientes DROP CONSTRAINT clientes_pkey;
       public            postgres    false    204            �
           2606    17021    color color_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.color
    ADD CONSTRAINT color_pkey PRIMARY KEY (id_color);
 :   ALTER TABLE ONLY public.color DROP CONSTRAINT color_pkey;
       public            postgres    false    213            �
           2606    16928    empleados empleados_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_pkey PRIMARY KEY (id_empleado);
 B   ALTER TABLE ONLY public.empleados DROP CONSTRAINT empleados_pkey;
       public            postgres    false    205            �
           2606    16995    marcas marcas_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.marcas
    ADD CONSTRAINT marcas_pkey PRIMARY KEY (id_marcas);
 <   ALTER TABLE ONLY public.marcas DROP CONSTRAINT marcas_pkey;
       public            postgres    false    209            �
           2606    16932    permiso permiso_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT permiso_pkey PRIMARY KEY (codigo_permiso);
 >   ALTER TABLE ONLY public.permiso DROP CONSTRAINT permiso_pkey;
       public            postgres    false    206            �
           2606    17057    ticket ticket_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_pkey PRIMARY KEY (id_ticket);
 <   ALTER TABLE ONLY public.ticket DROP CONSTRAINT ticket_pkey;
       public            postgres    false    217            �
           2606    17039 &   tipo_combustible tipo_combustible_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public.tipo_combustible
    ADD CONSTRAINT tipo_combustible_pkey PRIMARY KEY (id_tipo_combustible);
 P   ALTER TABLE ONLY public.tipo_combustible DROP CONSTRAINT tipo_combustible_pkey;
       public            postgres    false    215            �
           2606    17070    tipos_pago tipos_pago_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.tipos_pago
    ADD CONSTRAINT tipos_pago_pkey PRIMARY KEY (id_tipo_pago);
 D   ALTER TABLE ONLY public.tipos_pago DROP CONSTRAINT tipos_pago_pkey;
       public            postgres    false    219            �
           2606    17008 $   tipos_vehiculos tipos_vehiculos_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.tipos_vehiculos
    ADD CONSTRAINT tipos_vehiculos_pkey PRIMARY KEY (id_tipos_vehiculos);
 N   ALTER TABLE ONLY public.tipos_vehiculos DROP CONSTRAINT tipos_vehiculos_pkey;
       public            postgres    false    211            �
           2606    16942    vehiculos vehiculos_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT vehiculos_pkey PRIMARY KEY (numero_placa);
 B   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT vehiculos_pkey;
       public            postgres    false    207            �
           2606    16943    empleados empleadocargo_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleadocargo_fk FOREIGN KEY (empleadocargo) REFERENCES public.cargo(codigo_cargo);
 D   ALTER TABLE ONLY public.empleados DROP CONSTRAINT empleadocargo_fk;
       public          postgres    false    202    2748    205            �
           2606    17076    ticket fk_ticket_to_clientes    FK CONSTRAINT     �   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_ticket_to_clientes FOREIGN KEY (cliente) REFERENCES public.clientes(cedula);
 F   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_ticket_to_clientes;
       public          postgres    false    204    217    2750            �
           2606    17058    ticket fk_ticket_to_empleados    FK CONSTRAINT     �   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_ticket_to_empleados FOREIGN KEY (empleado) REFERENCES public.empleados(id_empleado);
 G   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_ticket_to_empleados;
       public          postgres    false    2752    217    205            �
           2606    17071    ticket fk_ticket_to_tipos_pago    FK CONSTRAINT     �   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_ticket_to_tipos_pago FOREIGN KEY (tipo_pago) REFERENCES public.tipos_pago(id_tipo_pago);
 H   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_ticket_to_tipos_pago;
       public          postgres    false    217    219    2768            �
           2606    17081    ticket fk_ticket_to_vehiculos    FK CONSTRAINT     �   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_ticket_to_vehiculos FOREIGN KEY (vehiculo_placa) REFERENCES public.vehiculos(numero_placa);
 G   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_ticket_to_vehiculos;
       public          postgres    false    207    217    2756            �
           2606    17027    vehiculos fk_vehiculos_to_color    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT fk_vehiculos_to_color FOREIGN KEY (color) REFERENCES public.color(id_color);
 I   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT fk_vehiculos_to_color;
       public          postgres    false    2762    213    207            �
           2606    17022     vehiculos fk_vehiculos_to_marcas    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT fk_vehiculos_to_marcas FOREIGN KEY (marcas) REFERENCES public.marcas(id_marcas);
 J   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT fk_vehiculos_to_marcas;
       public          postgres    false    2758    209    207            �
           2606    17040 *   vehiculos fk_vehiculos_to_tipo_combustible    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT fk_vehiculos_to_tipo_combustible FOREIGN KEY (tipos_combustible) REFERENCES public.tipo_combustible(id_tipo_combustible);
 T   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT fk_vehiculos_to_tipo_combustible;
       public          postgres    false    2764    207    215            �
           2606    17009 )   vehiculos fk_vehiculos_to_tipos_vehiculos    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehiculos
    ADD CONSTRAINT fk_vehiculos_to_tipos_vehiculos FOREIGN KEY (tipos_vehiculos) REFERENCES public.tipos_vehiculos(id_tipos_vehiculos);
 S   ALTER TABLE ONLY public.vehiculos DROP CONSTRAINT fk_vehiculos_to_tipos_vehiculos;
       public          postgres    false    207    2760    211            X   G   x�3�tL����,.)JL�/�4�2�t��+s��9�R�S�3��<N�ԢԒT�`�����b��=... ���      Z   w  x���=�1�k�)|C���u�E�z���g=��x���I�A� E�)���|��rAs���ǻ�h�6����F����i����4�L!'O=9o1��>E��`t�,9A������y7to��B�K���.\�K�w�rX\M	�WEe�!a�ά�����z9�7�6���?m����Ѿ���Ƕ���D�ڴj��޸Pj�s�H:HDFa1Xp�1+Si�T��v?�o�v)T��o����S�� C�(�y��U�8��h5��W�y"z':������̡�k��Q}��SFL��!2e���	���S�qn�x�w�(���C5d�4��W����ry��;<X��{aX���cg��8�B����n�*�����$��      c      x�3��KM/������ v�      [   |   x�M���0 g�+���N��ǪbGHl,!NQ)4�Oab���0��1x�6���N�Q���m9��E�d�R��s����z,�����d�i�L� �Y���|����V�{O�����<�0�]+�>�_&B      _      x�3���,.N������ �Y      \      x������ � �      g      x������ � �      e      x�3�tO,����K����� '�      i      x�3�,I,�J-I����� #��      a      x�3�tI-�/*�,������ /�      ]   V   x�+�,4055�0�30�t�4202�50�52�4�B�����Լ�b}O0�H/� ]�U���������"��Ġ��� $����� ���     