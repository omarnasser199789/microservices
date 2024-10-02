-- Enable the UUID extension if it doesn't already exist
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create the customer_id_sequence if it doesn't exist
CREATE SEQUENCE IF NOT EXISTS customer_id_sequence
    START WITH 1  -- Starting value
    INCREMENT BY 1  -- Increment step
    NO MINVALUE  -- No minimum value
    NO MAXVALUE  -- No maximum value
    CACHE 1;  -- Cache one value for performance

-- Create the customer table if it doesn't exist
CREATE TABLE IF NOT EXISTS customer (
    id INTEGER DEFAULT nextval('customer_id_sequence') PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL

    );
