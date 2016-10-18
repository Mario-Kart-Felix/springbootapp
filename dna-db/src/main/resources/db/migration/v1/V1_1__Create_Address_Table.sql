CREATE TABLE address 
(      id		                       INTEGER        PRIMARY KEY
,      individual_id                   INTEGER        REFERENCES individual
,      address_line1                   VARCHAR(100)   NOT NULL
,      address_line2                   VARCHAR(100)
,      county                          VARCHAR(50)
,      country                         VARCHAR(50)    NOT NULL
,      post_code                       VARCHAR(8)     NOT NULL
,      last_update_timestamp           TIMESTAMP      DEFAULT current_timestamp
);