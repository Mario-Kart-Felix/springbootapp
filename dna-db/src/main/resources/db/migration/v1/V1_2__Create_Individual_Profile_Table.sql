CREATE TABLE individual_profile
(      id					           INTEGER        PRIMARY KEY
,      individual_id                   INTEGER        REFERENCES individual
,      finger_print_data               VARCHAR(255)
,      retina_scan_data                VARCHAR(255)
,      last_update_timestamp           TIMESTAMP      DEFAULT current_timestamp
);