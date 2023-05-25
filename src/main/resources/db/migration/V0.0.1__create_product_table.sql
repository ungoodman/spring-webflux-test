CREATE TABLE IF NOT EXISTS t_product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cd VARCHAR(255) NOT NULL,
    nm VARCHAR(255) NOT NULL,
    amount INT8 NULL,
    sts_cd VARCHAR(255) NOT NULL,
    crt_time TIMESTAMPTZ NULL,
    crt_by VARCHAR(255) NOT NULL,
    upd_time TIMESTAMPTZ NULL,
    upd_by VARCHAR(255) NULL
);