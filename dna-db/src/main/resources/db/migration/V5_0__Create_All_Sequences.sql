CREATE SEQUENCE individual_seq
START 10000000
INCREMENT 10
MAXVALUE 99999999
CACHE 10;

CREATE SEQUENCE address_seq
START 1
MAXVALUE 99999999;

CREATE SEQUENCE individual_profile_seq
START 1
MAXVALUE 99999999;

CREATE SEQUENCE sample_profile_seq
START 1
MAXVALUE 999999999999
NO CYCLE;