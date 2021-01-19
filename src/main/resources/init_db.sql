SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE public.cars (
                             id bigint NOT NULL,
                             model character varying,
                             manufacturer_id bigint,
                             deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE public.cars OWNER TO postgres;

CREATE SEQUENCE public.cars_car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.cars_car_id_seq OWNER TO postgres;

ALTER SEQUENCE public.cars_car_id_seq OWNED BY public.cars.id;

CREATE TABLE public.cars_drivers (
                                     driver_id bigint NOT NULL,
                                     car_id bigint NOT NULL
);

ALTER TABLE public.cars_drivers OWNER TO postgres;

CREATE TABLE public.drivers (
                                id bigint NOT NULL,
                                name character varying,
                                license_number character varying,
                                deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE public.drivers OWNER TO postgres;

CREATE SEQUENCE public.drivers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.drivers_id_seq OWNER TO postgres;

ALTER SEQUENCE public.drivers_id_seq OWNED BY public.drivers.id;

CREATE TABLE public.manufacturers (
                                      id bigint NOT NULL,
                                      name character varying,
                                      country character varying,
                                      deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE public.manufacturers OWNER TO postgres;

CREATE SEQUENCE public.manufacturers_manufacturer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.manufacturers_manufacturer_id_seq OWNER TO postgres;

ALTER SEQUENCE public.manufacturers_manufacturer_id_seq OWNED BY public.manufacturers.id;

ALTER TABLE ONLY public.cars ALTER COLUMN id SET DEFAULT nextval('public.cars_car_id_seq'::regclass);

ALTER TABLE ONLY public.drivers ALTER COLUMN id SET DEFAULT nextval('public.drivers_id_seq'::regclass);

ALTER TABLE ONLY public.manufacturers ALTER COLUMN id SET DEFAULT nextval('public.manufacturers_manufacturer_id_seq'::regclass);

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.drivers
    ADD CONSTRAINT drivers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.manufacturers
    ADD CONSTRAINT manufacturers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT car_manufacturer_fk FOREIGN KEY (manufacturer_id) REFERENCES public.manufacturers(id);

ALTER TABLE ONLY public.cars_drivers
    ADD CONSTRAINT cars_id FOREIGN KEY (car_id) REFERENCES public.cars(id);

ALTER TABLE ONLY public.cars_drivers
    ADD CONSTRAINT drivers_id FOREIGN KEY (driver_id) REFERENCES public.drivers(id);
