CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS users (
    id_user BIGINT PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    enabled               BOOLEAN NOT NULL DEFAULT true,
    role VARCHAR(100)     NOT NULL,
    password VARCHAR(255) NOT NULL,
    account_non_locked    BOOLEAN NOT NULL DEFAULT true
);

ALTER TABLE public.users ALTER COLUMN id_user SET DEFAULT nextval('users_id_seq');
ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id_user;