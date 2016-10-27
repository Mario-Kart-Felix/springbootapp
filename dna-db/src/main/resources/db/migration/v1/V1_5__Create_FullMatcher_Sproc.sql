CREATE OR REPLACE FUNCTION fullMatcher(IN sample_fPrint VARCHAR, IN sample_retina VARCHAR, OUT indId  integer)
RETURNS integer AS
$BODY$

BEGIN

	SELECT ind.id INTO indId
	FROM  individual_profile prof
	JOIN individual ind ON prof.individual_id = ind.id
	WHERE 
		
		prof.finger_print_data = $1 
	AND 
		prof.retina_scan_data = $2;

END;
$BODY$
language plpgsql;