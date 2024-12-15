-- Create the table if it does not exist
CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price > 0),
    producer_id BIGINT NOT NULL, -- Logical foreign key, not enforced by DB
    UNIQUE (name, producer_id)
    );


-- Insert sample products for each producer
INSERT INTO products (name, price, producer_id) VALUES
-- Products for Producer One
('Product A1', 10.00, 1),
('Product A2', 15.00, 1),
('Product A3', 20.00, 1),
('Product A4', 25.00, 1),
('Product A5', 30.00, 1),

-- Products for Producer Two
('Product B1', 12.50, 2),
('Product B2', 17.50, 2),
('Product B3', 22.50, 2),
('Product B4', 27.50, 2),
('Product B5', 32.50, 2),

-- Products for Producer Three
('Product C1', 11.00, 3),
('Product C2', 13.00, 3),
('Product C3', 19.00, 3),
('Product C4', 24.00, 3),
('Product C5', 29.00, 3),

-- Products for Producer Four
('Product D1', 14.00, 4),
('Product D2', 16.00, 4),
('Product D3', 18.00, 4),
('Product D4', 23.00, 4),
('Product D5', 28.00, 4),

-- Products for Producer Five
('Product E1', 15.00, 5),
('Product E2', 20.00, 5),
('Product E3', 25.00, 5),
('Product E4', 30.00, 5),
('Product E5', 35.00, 5),

-- Products for Producer Six
('Product F1', 10.50, 6),
('Product F2', 15.50, 6),
('Product F3', 20.50, 6),
('Product F4', 25.50, 6),
('Product F5', 30.50, 6),

-- Products for Producer Seven
('Product G1', 11.50, 7),
('Product G2', 16.50, 7),
('Product G3', 21.50, 7),
('Product G4', 26.50, 7),
('Product G5', 31.50, 7),

-- Products for Producer Eight
('Product H1', 12.00, 8),
('Product H2', 17.00, 8),
('Product H3', 22.00, 8),
('Product H4', 27.00, 8),
('Product H5', 32.00, 8),

-- Products for Producer Nine
('Product I1', 13.00, 9),
('Product I2', 18.00, 9),
('Product I3', 23.00, 9),
('Product I4', 28.00, 9),
('Product I5', 33.00, 9),

-- Products for Producer Ten
('Product J1', 14.50, 10),
('Product J2', 19.50, 10),
('Product J3', 24.50, 10),
('Product J4', 29.50, 10),
('Product J5', 34.50, 10)
    ON CONFLICT (name, producer_id) DO NOTHING;
