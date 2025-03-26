INSERT INTO types_payment (id_type_payment, type_payment) VALUES (1000, 'EFECTIVO');
INSERT INTO public.clients(id_client, name, lastname, dni, phone, deleted)
	VALUES (1000, 'Juan', 'Perez', '45815204', '4856954871', false);
INSERT INTO categories( id_category, name_category) VALUES (1000, 'HILO');
INSERT INTO units (id_unit, unit_name) VALUES (1000, 'KILOGRAM');
INSERT INTO public.items( id_item, price_item, quantity, description_item, id_category, id_unit, deleted)
        VALUES  (1000, 45.0, 100.0, 'semigrueso rojo', 1000, 1000, false);

-- FINISH SALE
INSERT INTO payments( id_payment, total_payment, description_payment, deleted, id_type_payment)
 	VALUES  (1000, 100.0, 'No observations', false, 1000);
INSERT INTO public.sales(id_sale, total, status, id_client, id_payment)
	VALUES (1000, 100.0, 'PENDING_PAYMENT', 1000, 1000);

-- FIND BY ID
INSERT INTO payments( id_payment, total_payment, description_payment, deleted, id_type_payment)
 	VALUES  (1001, 100.0, 'No observations', false, 1000);
INSERT INTO public.sales(id_sale, total, status, id_client, id_payment)
	VALUES (1001, 450.0, 'PENDING_PAYMENT', 1000, 1001);
INSERT INTO public.sales_items(id_sale, id_item, quantity, price, subtotal)
	VALUES (1001, 1000, 10.0, 45.0, 450.0);

-- FIND ALL BY CLIENT
INSERT INTO public.clients(id_client, name, lastname, dni, phone, deleted)
	VALUES (1001, 'Miguel', 'Perez', '45815205', '4856954871', false);
INSERT INTO payments( id_payment, total_payment, description_payment, deleted, id_type_payment)
 	VALUES  (1002, 100.0, 'No observations', false, 1000);
INSERT INTO public.sales(id_sale, total, status, id_client, id_payment)
	VALUES (1002, 10.0, 'PENDING_PAYMENT', 1001, 1002);
INSERT INTO public.sales_items(id_sale, id_item, quantity, price, subtotal)
	VALUES (1002, 1000, 10.0, 1.0, 10.0);