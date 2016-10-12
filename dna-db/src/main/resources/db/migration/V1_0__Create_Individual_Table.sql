--DROP TABLE individual;

CREATE TABLE individual 
(      individual_id                   INTEGER        PRIMARY KEY
,      title                           VARCHAR(10)    NOT NULL
,      first_name                      VARCHAR(40)    NOT NULL
,      last_name                       VARCHAR(40)
,      dob                             DATE           NOT NULL
,      last_update_timestamp           TIMESTAMP      DEFAULT current_timestamp
,      UNIQUE (first_name, last_name, dob)
);