CREATE TABLE sample_profile
(      sample_profile_id               INTEGER        PRIMARY KEY
,      finger_print_data               JSON
,      retina_scan_data                JSON
,      matching_order                  JSON
,      last_update_timestamp           TIMESTAMP      DEFAULT current_timestamp
);