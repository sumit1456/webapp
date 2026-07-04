-- V3: Seed default admin record

INSERT INTO admin (username, password)
VALUES ('admin', 'admin123')
ON CONFLICT DO NOTHING;
