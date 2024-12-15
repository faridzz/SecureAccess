-- Create the table if it does not exist
CREATE TABLE IF NOT EXISTS producers (
     id SERIAL PRIMARY KEY,
     name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
    );

-- Insert sample producers
INSERT INTO producers (name, email) VALUES
    ('Producer One', 'producer.one@example.com'),
    ('Producer Two', 'producer.two@example.com'),
    ('Producer Three', 'producer.three@example.com'),
    ('Producer Four', 'producer.four@example.com'),
    ('Producer Five', 'producer.five@example.com'),
    ('Producer Six', 'producer.six@example.com'),
    ('Producer Seven', 'producer.seven@example.com'),
    ('Producer Eight', 'producer.eight@example.com'),
    ('Producer Nine', 'producer.nine@example.com'),
    ('Producer Ten', 'producer.ten@example.com')  ON CONFLICT (email) DO NOTHING;;
