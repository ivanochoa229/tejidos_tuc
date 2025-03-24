-- CREATE types_payment table
CREATE TABLE IF NOT EXISTS types_payment (
    id_type_payment SERIAL PRIMARY KEY,
    type_payment VARCHAR(45) NOT NULL UNIQUE
);

-- CREATE payments TABLE
CREATE TABLE IF NOT EXISTS payments (
    id_payment SERIAL PRIMARY KEY,
    total_payment DOUBLE PRECISION NOT NULL,
    description_payment VARCHAR(150),
    id_type_payment INT NOT NULL,
    CONSTRAINT fk_payments_types_payment
        FOREIGN KEY (id_type_payment)
        REFERENCES types_payment (id_type_payment)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

-- update sales table
ALTER TABLE sales ADD COLUMN id_payment INT NOT NULL;

ALTER TABLE sales ADD CONSTRAINT fk_sale_payment
    FOREIGN KEY (id_payment)
    REFERENCES payments (id_payment)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;