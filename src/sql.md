# Table create queries:

# Animal:
CREATE TABLE IF NOT EXISTS public.animals
(
id bigint NOT NULL DEFAULT nextval('animals_id_seq'::regclass),
breed character varying(255) COLLATE pg_catalog."default",
colour character varying(255) COLLATE pg_catalog."default",
date_of_birth date,
gender character varying(255) COLLATE pg_catalog."default",
name character varying(255) COLLATE pg_catalog."default",
species character varying(255) COLLATE pg_catalog."default",
customer_id bigint,
CONSTRAINT animals_pkey PRIMARY KEY (id),
CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id)
REFERENCES public.customers (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.animals
OWNER to postgres;

# Appointments:
-- Table: public.appointments

-- DROP TABLE IF EXISTS public.appointments;

CREATE TABLE IF NOT EXISTS public.appointments
(
id bigint NOT NULL DEFAULT nextval('appointments_id_seq'::regclass),
appointment_date timestamp(6) without time zone,
animal_id bigint,
doctor_id bigint,
CONSTRAINT appointments_pkey PRIMARY KEY (id),
CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id)
REFERENCES public.animals (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION,
CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id)
REFERENCES public.doctors (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.appointments
OWNER to postgres;

# Available Dates:
-- Table: public.available_dates

-- DROP TABLE IF EXISTS public.available_dates;

CREATE TABLE IF NOT EXISTS public.available_dates
(
id bigint NOT NULL DEFAULT nextval('available_dates_id_seq'::regclass),
available_date date,
doctor_id bigint,
CONSTRAINT available_dates_pkey PRIMARY KEY (id),
CONSTRAINT fknb419ilm71d71rm584rk460pk FOREIGN KEY (doctor_id)
REFERENCES public.doctors (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.available_dates
OWNER to postgres;

# Customers:
-- Table: public.customers

-- DROP TABLE IF EXISTS public.customers;

CREATE TABLE IF NOT EXISTS public.customers
(
id bigint NOT NULL DEFAULT nextval('customers_id_seq'::regclass),
address character varying(255) COLLATE pg_catalog."default",
city character varying(255) COLLATE pg_catalog."default",
mail character varying(255) COLLATE pg_catalog."default",
name character varying(255) COLLATE pg_catalog."default",
phone_number character varying(255) COLLATE pg_catalog."default",
CONSTRAINT customers_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.customers
OWNER to postgres;

# Doctors:
-- Table: public.doctors

-- DROP TABLE IF EXISTS public.doctors;

CREATE TABLE IF NOT EXISTS public.doctors
(
id bigint NOT NULL DEFAULT nextval('doctors_id_seq'::regclass),
address character varying(255) COLLATE pg_catalog."default",
city character varying(255) COLLATE pg_catalog."default",
mail character varying(255) COLLATE pg_catalog."default",
name character varying(255) COLLATE pg_catalog."default",
phone_number character varying(255) COLLATE pg_catalog."default",
CONSTRAINT doctors_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.doctors
OWNER to postgres;

# Vaccines:
-- Table: public.vaccines

-- DROP TABLE IF EXISTS public.vaccines;

CREATE TABLE IF NOT EXISTS public.vaccines
(
id bigint NOT NULL DEFAULT nextval('vaccines_id_seq'::regclass),
vaccine_code character varying(100) COLLATE pg_catalog."default",
protection_end_date date,
protection_start_date date,
animal_id bigint,
CONSTRAINT vaccines_pkey PRIMARY KEY (id),
CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs FOREIGN KEY (animal_id)
REFERENCES public.animals (id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.vaccines
OWNER to postgres;

# DATA INSERT QUERIES

# Animal:
INSERT INTO public.animals(
id, breed, colour, date_of_birth, gender, name, species, customer_id)
VALUES (4,Scottish Fold,white,2021-03-07,male,Bulut,cat,4);