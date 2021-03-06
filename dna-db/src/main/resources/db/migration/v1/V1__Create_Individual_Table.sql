--DROP TABLE individual;

CREATE TABLE individual 
(      id			                   INTEGER        PRIMARY KEY
,      title                           VARCHAR(10)    NOT NULL
,      first_name                      VARCHAR(40)    NOT NULL
,      middle_name                     VARCHAR(40)
,      last_name                       VARCHAR(40)    NOT NULL
,      dob                             DATE           NOT NULL
,      last_update_timestamp           TIMESTAMP      DEFAULT current_timestamp
,      UNIQUE (first_name, last_name, dob)
);