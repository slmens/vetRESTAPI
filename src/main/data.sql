PGDMP       #                {            vet    16.0    16.0 3    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    19678    vet    DATABASE     w   CREATE DATABASE vet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_Turkey.1254';
    DROP DATABASE vet;
                postgres    false            �            1259    19680    animals    TABLE       CREATE TABLE public.animals (
    id bigint NOT NULL,
    breed character varying(255),
    colour character varying(255),
    date_of_birth date,
    gender character varying(255),
    name character varying(255),
    species character varying(255),
    customer_id bigint
);
    DROP TABLE public.animals;
       public         heap    postgres    false            �            1259    19679    animals_id_seq    SEQUENCE     w   CREATE SEQUENCE public.animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.animals_id_seq;
       public          postgres    false    216            �           0    0    animals_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.animals_id_seq OWNED BY public.animals.id;
          public          postgres    false    215            �            1259    19689    appointments    TABLE     �   CREATE TABLE public.appointments (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    animal_id bigint,
    doctor_id bigint
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            �            1259    19688    appointments_id_seq    SEQUENCE     |   CREATE SEQUENCE public.appointments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.appointments_id_seq;
       public          postgres    false    218            �           0    0    appointments_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.appointments_id_seq OWNED BY public.appointments.id;
          public          postgres    false    217            �            1259    19696    available_dates    TABLE     o   CREATE TABLE public.available_dates (
    id bigint NOT NULL,
    available_date date,
    doctor_id bigint
);
 #   DROP TABLE public.available_dates;
       public         heap    postgres    false            �            1259    19695    available_dates_id_seq    SEQUENCE        CREATE SEQUENCE public.available_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.available_dates_id_seq;
       public          postgres    false    220            �           0    0    available_dates_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.available_dates_id_seq OWNED BY public.available_dates.id;
          public          postgres    false    219            �            1259    19703 	   customers    TABLE     �   CREATE TABLE public.customers (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    name character varying(255),
    phone_number character varying(255)
);
    DROP TABLE public.customers;
       public         heap    postgres    false            �            1259    19702    customers_id_seq    SEQUENCE     y   CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.customers_id_seq;
       public          postgres    false    222            �           0    0    customers_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;
          public          postgres    false    221            �            1259    19712    doctors    TABLE     �   CREATE TABLE public.doctors (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    name character varying(255),
    phone_number character varying(255)
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    19711    doctors_id_seq    SEQUENCE     w   CREATE SEQUENCE public.doctors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.doctors_id_seq;
       public          postgres    false    224            �           0    0    doctors_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;
          public          postgres    false    223            �            1259    19721    vaccines    TABLE     �   CREATE TABLE public.vaccines (
    id bigint NOT NULL,
    vaccine_code character varying(100),
    protection_end_date date,
    protection_start_date date,
    animal_id bigint
);
    DROP TABLE public.vaccines;
       public         heap    postgres    false            �            1259    19720    vaccines_id_seq    SEQUENCE     x   CREATE SEQUENCE public.vaccines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.vaccines_id_seq;
       public          postgres    false    226            �           0    0    vaccines_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.vaccines_id_seq OWNED BY public.vaccines.id;
          public          postgres    false    225            3           2604    19754 
   animals id    DEFAULT     h   ALTER TABLE ONLY public.animals ALTER COLUMN id SET DEFAULT nextval('public.animals_id_seq'::regclass);
 9   ALTER TABLE public.animals ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            4           2604    19755    appointments id    DEFAULT     r   ALTER TABLE ONLY public.appointments ALTER COLUMN id SET DEFAULT nextval('public.appointments_id_seq'::regclass);
 >   ALTER TABLE public.appointments ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            5           2604    19756    available_dates id    DEFAULT     x   ALTER TABLE ONLY public.available_dates ALTER COLUMN id SET DEFAULT nextval('public.available_dates_id_seq'::regclass);
 A   ALTER TABLE public.available_dates ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            6           2604    19757    customers id    DEFAULT     l   ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);
 ;   ALTER TABLE public.customers ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            7           2604    19758 
   doctors id    DEFAULT     h   ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);
 9   ALTER TABLE public.doctors ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223    224            8           2604    19759    vaccines id    DEFAULT     j   ALTER TABLE ONLY public.vaccines ALTER COLUMN id SET DEFAULT nextval('public.vaccines_id_seq'::regclass);
 :   ALTER TABLE public.vaccines ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    226    226            �          0    19680    animals 
   TABLE DATA           g   COPY public.animals (id, breed, colour, date_of_birth, gender, name, species, customer_id) FROM stdin;
    public          postgres    false    216   �9       �          0    19689    appointments 
   TABLE DATA           R   COPY public.appointments (id, appointment_date, animal_id, doctor_id) FROM stdin;
    public          postgres    false    218   s:       �          0    19696    available_dates 
   TABLE DATA           H   COPY public.available_dates (id, available_date, doctor_id) FROM stdin;
    public          postgres    false    220   �:       �          0    19703 	   customers 
   TABLE DATA           P   COPY public.customers (id, address, city, mail, name, phone_number) FROM stdin;
    public          postgres    false    222   q;       �          0    19712    doctors 
   TABLE DATA           N   COPY public.doctors (id, address, city, mail, name, phone_number) FROM stdin;
    public          postgres    false    224   <       �          0    19721    vaccines 
   TABLE DATA           k   COPY public.vaccines (id, vaccine_code, protection_end_date, protection_start_date, animal_id) FROM stdin;
    public          postgres    false    226   �<       �           0    0    animals_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.animals_id_seq', 8, true);
          public          postgres    false    215            �           0    0    appointments_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.appointments_id_seq', 9, true);
          public          postgres    false    217            �           0    0    available_dates_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.available_dates_id_seq', 35, true);
          public          postgres    false    219            �           0    0    customers_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.customers_id_seq', 6, true);
          public          postgres    false    221            �           0    0    doctors_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.doctors_id_seq', 6, true);
          public          postgres    false    223            �           0    0    vaccines_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.vaccines_id_seq', 9, true);
          public          postgres    false    225            :           2606    19687    animals animals_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public            postgres    false    216            <           2606    19694    appointments appointments_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public            postgres    false    218            >           2606    19701 $   available_dates available_dates_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT available_dates_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT available_dates_pkey;
       public            postgres    false    220            @           2606    19710    customers customers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    222            B           2606    19719    doctors doctors_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    224            D           2606    19726    vaccines vaccines_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public            postgres    false    226            F           2606    19734 (   appointments fk95vepu86o8syqtux9gkr71bhy    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id) REFERENCES public.animals(id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk95vepu86o8syqtux9gkr71bhy;
       public          postgres    false    218    216    4666            E           2606    19729 #   animals fkb36lt3kj4mrbdx5btxmp4j60n    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id) REFERENCES public.customers(id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n;
       public          postgres    false    222    4672    216            I           2606    19749 $   vaccines fkeasdy15b2kp5j4k13x2dfudqs    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs FOREIGN KEY (animal_id) REFERENCES public.animals(id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs;
       public          postgres    false    4666    216    226            G           2606    19739 (   appointments fkmujeo4tymoo98cmf7uj3vsv76    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76;
       public          postgres    false    224    218    4674            H           2606    19744 +   available_dates fknb419ilm71d71rm584rk460pk    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT fknb419ilm71d71rm584rk460pk FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);
 U   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT fknb419ilm71d71rm584rk460pk;
       public          postgres    false    220    224    4674            �   �   x�MϽj�@�z�)�
����BX)R�Đ&�ZZ·�[�:ᐧ�9����̖x�%7����ngy�oҬH�-&�2.=��BäI�dţ��m������^�wL�25Z���ղ�>�j�4��\͑�E{�����*�2O�����������^���'Ϣs���_~U��zP�f���|>c~ {�H�      �   O   x�mι� �x4��7�Z�&@Z�&{�8)�u������;r���M�H�!K�$�1J�+��+���K�>x. /�a      �   �   x�]͹�0�Xۋ�AR�����/�_2�G�����u��f����?��c�Źⵙ��,Q��������r�.�r���O���e��+�Ɵ���������~���˃}y�/_�ˋ�n���ξ��ɾ\�˝�a} W�      �   �   x���1!��N��&,hme����fV�N��MX�d��`�Ք���|yXu���Y��B��9��)9BT�WY!͕���ug@����P֍���y����$����|�Taҩ�������{Sk!��7a
,7��e�ZC��      �   �   x��п
�0�9y�<�b��Ep)�`�\�m�!*M��B]u�>��d��}���2Tv?8��)��+r�hb:M/cAWN"�$ӻ�.��S�2)�xc��t�Q�N3��1s-�TC����E;�����`��V1QyȤp�q=����m����,B&':�]�����c��w֏��c�^������      �   L   x�]˻�0��ڷ��m�e�`�9 U��ҧ���L�Y��V��(9�s~8(P���
���ξY'����2     