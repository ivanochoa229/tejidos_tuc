CREATE SEQUENCE IF NOT EXISTS public.categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.units_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.sales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.addresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Crear la tabla categories
CREATE TABLE IF NOT EXISTS categories (
    id_category BIGINT PRIMARY KEY NOT NULL,
    name_category VARCHAR(45) NOT NULL UNIQUE
);

ALTER TABLE public.categories ALTER COLUMN id_category SET DEFAULT nextval('categories_id_seq');
ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id_category;

-- Crear la tabla units
CREATE TABLE IF NOT EXISTS units (
    id_unit BIGINT PRIMARY KEY NOT NULL,
    unit_name VARCHAR(45) NOT NULL UNIQUE
);

ALTER TABLE public.units ALTER COLUMN id_unit SET DEFAULT nextval('units_id_seq');
ALTER SEQUENCE public.units_id_seq OWNED BY public.units.id_unit;

-- Crear la tabla items (con campo deleted)
CREATE TABLE IF NOT EXISTS items (
    id_item BIGINT PRIMARY KEY NOT NULL,
    price_item DOUBLE PRECISION NOT NULL,
    quantity DOUBLE PRECISION NOT NULL,
    description_item VARCHAR(45) NOT NULL UNIQUE,
    id_category INT NOT NULL,
    id_unit INT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_item_category
        FOREIGN KEY (id_category)
        REFERENCES categories (id_category)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_item_unit
        FOREIGN KEY (id_unit)
        REFERENCES units (id_unit)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

ALTER TABLE public.items ALTER COLUMN id_item SET DEFAULT nextval('items_id_seq');
ALTER SEQUENCE public.items_id_seq OWNED BY public.items.id_item;

-- Crear la tabla clients (con campo deleted)
CREATE TABLE IF NOT EXISTS clients (
    id_client BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(45) NOT NULL,
    lastname VARCHAR(45),
    dni VARCHAR(45) UNIQUE,
    phone VARCHAR(45) NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

ALTER TABLE public.clients ALTER COLUMN id_client SET DEFAULT nextval('clients_id_seq');
ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id_client;

-- Crear la tabla sales
CREATE TABLE IF NOT EXISTS sales (
    id_sale BIGINT PRIMARY KEY NOT NULL,
    total DOUBLE PRECISION NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING_PAYMENT',
    id_client INT NOT NULL,
    CONSTRAINT fk_sale_client
        FOREIGN KEY (id_client)
        REFERENCES clients (id_client)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

ALTER TABLE public.sales ALTER COLUMN id_sale SET DEFAULT nextval('sales_id_seq');
ALTER SEQUENCE public.sales_id_seq OWNED BY public.sales.id_sale;

-- Crear la tabla sales_items (tabla intermedia)
CREATE TABLE IF NOT EXISTS sales_items (
    id_sale BIGINT NOT NULL,
    id_item BIGINT NOT NULL,
    quantity DOUBLE PRECISION NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    subtotal DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (id_sale, id_item),
    CONSTRAINT fk_sales_items_sale
        FOREIGN KEY (id_sale)
        REFERENCES sales (id_sale)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_sales_items_item
        FOREIGN KEY (id_item)
        REFERENCES items (id_item)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

-- Crear la tabla addresses (con campo deleted)
CREATE TABLE IF NOT EXISTS addresses (
    id_address BIGINT PRIMARY KEY NOT NULL,
    street VARCHAR(45) NOT NULL,
    number BIGINT,
    state VARCHAR(45) NOT NULL,
    province VARCHAR(45) NOT NULL,
    id_client INT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_address_client
        FOREIGN KEY (id_client)
        REFERENCES clients (id_client)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

ALTER TABLE public.addresses ALTER COLUMN id_address SET DEFAULT nextval('addresses_id_seq');
ALTER SEQUENCE public.addresses_id_seq OWNED BY public.addresses.id_address;


