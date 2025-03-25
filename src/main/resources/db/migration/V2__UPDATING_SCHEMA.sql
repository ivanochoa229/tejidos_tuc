CREATE SEQUENCE IF NOT EXISTS public.types_payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.payments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- CREATE types_payment table
CREATE TABLE IF NOT EXISTS types_payment (
    id_type_payment BIGINT PRIMARY KEY NOT NULL,
    type_payment VARCHAR(45) NOT NULL UNIQUE
);

ALTER TABLE public.types_payment ALTER COLUMN id_type_payment SET DEFAULT nextval('types_payment_id_seq');
ALTER SEQUENCE public.types_payment_id_seq OWNED BY public.types_payment.id_type_payment;

-- CREATE payments TABLE
CREATE TABLE IF NOT EXISTS payments (
    id_payment BIGINT PRIMARY KEY NOT NULL,
    total_payment DOUBLE PRECISION NOT NULL,
    description_payment VARCHAR(150),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    id_type_payment INT NOT NULL,
    CONSTRAINT fk_payments_types_payment
        FOREIGN KEY (id_type_payment)
        REFERENCES types_payment (id_type_payment)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

ALTER TABLE public.payments ALTER COLUMN id_payment SET DEFAULT nextval('payments_id_seq');
ALTER SEQUENCE public.payments_id_seq OWNED BY public.payments.id_payment;

-- update sales table
ALTER TABLE sales ADD COLUMN id_payment INT;

ALTER TABLE sales ADD CONSTRAINT fk_sale_payment
    FOREIGN KEY (id_payment)
    REFERENCES payments (id_payment)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE addresses
ADD CONSTRAINT uk_address_street_number_state_province
UNIQUE (street, number, state, province);