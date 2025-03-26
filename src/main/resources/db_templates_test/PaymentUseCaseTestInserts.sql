INSERT INTO types_payment (id_type_payment, type_payment) VALUES (1000, 'EFECTIVO');

-- FIND BY ID
INSERT INTO payments(
	id_payment, total_payment, description_payment, deleted, id_type_payment)
	VALUES  (1000, 100.0, 'No observations', false, 1000),
	        (1001, 100.0, 'No observations', true, 1000);
-- CANCEL PAYMENT
INSERT INTO payments(
	id_payment, total_payment, description_payment, deleted, id_type_payment)
	VALUES  (1002, 100.0, 'No observations', false, 1000),
	        (1003, 100.0, 'No observations', true, 1000);