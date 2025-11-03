-- Add role column to users table
ALTER TABLE users ADD COLUMN role VARCHAR(20);

-- Migrate existing data (assuming is_admin field exists)
UPDATE users SET role = 'ROLE_ADMIN' WHERE is_admin = true;
UPDATE users SET role = 'ROLE_USER' WHERE is_admin = false OR is_admin IS NULL;

-- Make role NOT NULL
ALTER TABLE users ALTER COLUMN role SET NOT NULL;

-- Drop the old is_admin column (if it exists)
ALTER TABLE users DROP COLUMN IF EXISTS is_admin;

-- Add index for better performance on role queries
CREATE INDEX idx_users_role ON users(role);