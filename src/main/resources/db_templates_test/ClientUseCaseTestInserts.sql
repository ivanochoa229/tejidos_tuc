-- SAVE CLIENT
INSERT INTO public.clients(
	id_client, name, lastname, dni, phone, deleted)
	VALUES (1000, 'Juan', 'Perez', '45815204', '4856954871', false);

-- DELETE CLIENT

INSERT INTO public.clients(
	id_client, name, lastname, dni, phone, deleted)
	VALUES  (1001, 'Ricardo', 'Tapia', '40815204', '1856954871', false),
	        (1002, 'German', 'Garmendia', '10815204', '1856054871', true)
	;

-- FIND BY ID
INSERT INTO public.clients(
	id_client, name, lastname, dni, phone, deleted)
	VALUES  (1003, 'Nestor', 'Ortigoza', '40815444', '1856988871', false),
	        (1004, 'German', 'Urrutia', '10815504', '1856854871', true)
	;

-- UPDATE CLIENT
INSERT INTO public.clients(
	id_client, name, lastname, dni, phone, deleted)
	VALUES  (1005, 'Mariano', 'Closs', '40815441', '1856988870', false),
	        (1006, 'Hector', 'Salcedo', '10815404', '1856854872', false),
	        (1007, 'Jose', 'Moreno', '10815405', '176854872', true),
	        (1008, 'Nelson', 'Rufino', '66815405', '876854872', false)
	;