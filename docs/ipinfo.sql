CREATE SEQUENCE public.country_info_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    CACHE 1;

CREATE SEQUENCE public.currency_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    CACHE 1;

CREATE SEQUENCE public.forex_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    CACHE 1;

 CREATE SEQUENCE public.stats_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    CACHE 1;

-- Table: country_info

CREATE TABLE public.country_info
(
    id integer NOT NULL DEFAULT nextval('country_info_id_seq'::regclass),
    country character varying(50),
    estimated_distance integer,
    iso_code character varying(2),
    language bytea,
    last_update timestamp without time zone,
    "time" bytea,
    CONSTRAINT country_info_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

CREATE INDEX idx_country_info_iso_code
ON country_info (iso_code);

-- Table: currency

CREATE TABLE public.currency
(
    id integer NOT NULL DEFAULT nextval('currency_id_seq'::regclass),
    code character varying(3),
    country_info_id integer,
    symbol character varying(3),
    CONSTRAINT currency_pkey PRIMARY KEY (id),
    CONSTRAINT fk_currency_01 FOREIGN KEY (country_info_id)
        REFERENCES public.country_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

-- Table: forex

CREATE TABLE public.forex
(
    id integer NOT NULL DEFAULT nextval('forex_id_seq'::regclass),
    currency character varying(3),
    rate decimal,
    last_update integer,
    CONSTRAINT forex_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

CREATE INDEX idx_forex_iso_currency
ON forex (currency);

-- Table: stats

CREATE TABLE public.stats
(
    id bigint NOT NULL DEFAULT nextval('stats_id_seq'::regclass),
    country_code character varying(2),
    "timestamp" timestamp without time zone,
    CONSTRAINT stats_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

CREATE INDEX idx_stats_country_code
ON stats (country_code);
