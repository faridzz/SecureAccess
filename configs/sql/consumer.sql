-- Create the table if it does not exist
CREATE TABLE IF NOT EXISTS consumers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
    );

-- Insert 20 sample users
INSERT INTO consumers (name, email) VALUES
    ('John Doe', 'john.doe@example.com'),
    ('Jane Smith', 'jane.smith@example.com'),
    ('Alice Johnson', 'alice.johnson@example.com'),
    ('Bob Brown', 'bob.brown@example.com'),
    ('Charlie Davis', 'charlie.davis@example.com'),
    ('Eve Adams', 'eve.adams@example.com'),
    ('Frank Wright', 'frank.wright@example.com'),
    ('Grace Hall', 'grace.hall@example.com'),
    ('Hank Green', 'hank.green@example.com'),
    ('Ivy Harris', 'ivy.harris@example.com'),
    ('Jack Lee', 'jack.lee@example.com'),
    ('Karen Scott', 'karen.scott@example.com'),
    ('Leo Carter', 'leo.carter@example.com'),
    ('Mia Martinez', 'mia.martinez@example.com'),
    ('Noah White', 'noah.white@example.com'),
    ('Olivia Walker', 'olivia.walker@example.com'),
    ('Paul Young', 'paul.young@example.com'),
    ('Quinn Allen', 'quinn.allen@example.com'),
    ('Ruby Lopez', 'ruby.lopez@example.com'),
    ('Sam Wilson', 'sam.wilson@example.com')
    ON CONFLICT (email) DO NOTHING;
