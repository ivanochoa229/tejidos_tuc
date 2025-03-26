-- SAVE ALL
INSERT INTO categories( id_category, name_category) VALUES (1000, 'HILO');
INSERT INTO units (id_unit, unit_name) VALUES (1000, 'KILOGRAM');
INSERT INTO public.items( id_item, price_item, quantity, description_item, id_category, id_unit, deleted)
        VALUES  (1000, 45.0, 100.0, 'semigrueso rojo', 1000, 1000, false);
INSERT INTO types_payment (id_type_payment, type_payment) VALUES (1000, 'EFECTIVO');
INSERT INTO payments( id_payment, total_payment, description_payment, deleted, id_type_payment)
 	VALUES  (1000, 100.0, 'No observations', false, 1000);
INSERT INTO public.clients(id_client, name, lastname, dni, phone, deleted)
	VALUES (1000, 'Juan', 'Perez', '45815204', '4856954871', false);
INSERT INTO public.sales(id_sale, total, status, id_client, id_payment)
	VALUES (1000, 100.0, 'PENDING_PAYMENT', 1000, 1000);