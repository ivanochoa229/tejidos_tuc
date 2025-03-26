INSERT INTO units (id_unit, unit_name) VALUES (1, 'KILOGRAM');
INSERT INTO categories( id_category, name_category) VALUES (1, 'HILO');

-- SAVE ITEM
INSERT INTO public.items(
	id_item, price_item, quantity, description_item, id_category, id_unit, deleted)
	VALUES  (1000, 45.0, 100.0, 'semigrueso rojo', 1, 1, false);

-- FIND BY ID
INSERT INTO public.items(
	id_item, price_item, quantity, description_item, id_category, id_unit, deleted)
	VALUES  (1001, 45.0, 100.0, 'semigrueso verde', 1, 1, false),
	        (1002, 45.0, 100.0, 'semigrueso crema', 1, 1, true);

-- FIND ENTITY BY ID
INSERT INTO public.items(
	id_item, price_item, quantity, description_item, id_category, id_unit, deleted)
	VALUES  (1003, 45.0, 100.0, 'semigrueso azul', 1, 1, false);

-- DELETE ITEM
INSERT INTO public.items(
	id_item, price_item, quantity, description_item, id_category, id_unit, deleted)
	VALUES  (1004, 45.0, 100.0, 'semigrueso naranja', 1, 1, false),
	        (1005, 45.0, 100.0, 'semigrueso amarillo', 1, 1, true);

-- DELETE ITEM
INSERT INTO public.items(
	id_item, price_item, quantity, description_item, id_category, id_unit, deleted)
	VALUES  (1006, 45.0, 100.0, 'semigrueso celetes', 1, 1, false),
	        (1007, 45.0, 100.0, 'semigrueso negro', 1, 1, true),
	        (1008, 45.0, 100.0, 'semigrueso ocre', 1, 1, false);