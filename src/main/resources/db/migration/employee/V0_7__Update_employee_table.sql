ALTER TABLE IF EXISTS "employee"
    ADD COLUMN end_to_end_id varchar,
    ALTER COLUMN cnaps drop not null;