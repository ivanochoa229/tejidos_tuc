-- Crear la tabla categories
CREATE TABLE IF NOT EXISTS categories (
    id_category SERIAL PRIMARY KEY,
    name VARCHAR(45) NOT NULL UNIQUE
);

-- Crear la tabla units
CREATE TABLE IF NOT EXISTS units (
    id_unit SERIAL PRIMARY KEY,
    unit_name VARCHAR(45) NOT NULL UNIQUE
);

-- Crear la tabla items (con campo deleted)
CREATE TABLE IF NOT EXISTS items (
    id_item SERIAL PRIMARY KEY,
    price DOUBLE PRECISION NOT NULL,
    quantity DOUBLE PRECISION NOT NULL,
    name VARCHAR(45) NOT NULL UNIQUE,
    id_category INT NOT NULL,
    id_unit INT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE, -- Campo deleted
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

-- Crear la tabla clients (con campo deleted)
CREATE TABLE IF NOT EXISTS clients (
    id_client SERIAL PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    lastname VARCHAR(45),
    dni VARCHAR(45) UNIQUE,
    phone VARCHAR(45) NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE -- Campo deleted
);

-- Crear la tabla sales
CREATE TABLE IF NOT EXISTS sales (
    id_sale SERIAL PRIMARY KEY,
    total DOUBLE PRECISION NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING_PAYMENT',
    id_client INT NOT NULL,
    CONSTRAINT fk_sale_client
        FOREIGN KEY (id_client)
        REFERENCES clients (id_client)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

-- Crear la tabla sales_items (tabla intermedia)
CREATE TABLE IF NOT EXISTS sales_items (
    id_sale INT NOT NULL,
    id_item INT NOT NULL,
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
    id_address SERIAL PRIMARY KEY,
    street VARCHAR(45) NOT NULL,
    number VARCHAR(45),
    state VARCHAR(45) NOT NULL,
    province VARCHAR(45) NOT NULL,
    id_client INT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE, -- Campo deleted
    CONSTRAINT fk_address_client
        FOREIGN KEY (id_client)
        REFERENCES clients (id_client)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);