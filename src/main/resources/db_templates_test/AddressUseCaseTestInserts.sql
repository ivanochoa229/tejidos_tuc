-- FIND BY ID
INSERT INTO clients(
	id_client, name, lastname, dni, phone, deleted)
	VALUES (1000, 'Homer', 'Simpson', '51258746', '9512487583', false);

INSERT INTO addresses(
	id_address, street, "number", state, province, id_client, deleted)
	VALUES (1000, 'siempreviva', '123', 'springfield', 'dakota', 1000, false);

-- FIND ALL BY CLIENT

INSERT INTO clients(
	id_client, name, lastname, dni, phone, deleted)
	VALUES (1001, 'Bart', 'Simpson', '51258789', '9513217583', false);

INSERT INTO clients(
    	id_client, name, lastname, dni, phone, deleted)
    	VALUES (1002, 'Marge', 'Simpson', '47258789', '9513217568', false);

 INSERT INTO clients(
     	id_client, name, lastname, dni, phone, deleted)
     	VALUES (1003, 'Lisa', 'Simpson', '37258789', '1513217568', false);

INSERT INTO addresses(
	id_address, street, "number", state, province, id_client, deleted)
	VALUES (1001, 'siempreviva', '1234', 'springfield', 'dakota', 1001, false);

INSERT INTO addresses(
	id_address, street, "number", state, province, id_client, deleted)
	VALUES (1002, 'siempreviva', '12345', 'springfield', 'dakota', 1003, true);

-- DELETE ADDRESS

INSERT INTO addresses(
	id_address, street, "number", state, province, id_client, deleted)
	VALUES (1003, 'siempreviva', '12346', 'springfield', 'dakota', 1000, true);

-- SAVE ADDRESS
 INSERT INTO clients(
     	id_client, name, lastname, dni, phone, deleted)
     	VALUES (1004, 'Abraham', 'Simpson', '7258789', '1213217568', false);

-- UPDATE ADDRESS

 INSERT INTO clients(
     	id_client, name, lastname, dni, phone, deleted)
     	VALUES (1005, 'Mona', 'Simpson', '7258781', '1213217561', false);

INSERT INTO addresses(
	id_address, street, "number", state, province, id_client, deleted)
	VALUES  (1004, 'siempreviva', '12347', 'springfield', 'dakota', 1005, false),
	        (1005, 'Rondeau', '10081', 'Las Talitas', 'Tucuman', 1005, false);

