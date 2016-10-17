CREATE TABLE individual_profile
(      individual_profile_id           INTEGER        PRIMARY KEY
,      individual_id                   INTEGER        REFERENCES individual
,      finger_print_data               JSON
,      retina_scan_data                JSON
,      matching_order                  JSON
,      last_update_timestamp           TIMESTAMP      DEFAULT current_timestamp
);