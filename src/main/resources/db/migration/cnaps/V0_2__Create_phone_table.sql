CREATE TABLE IF NOT EXISTS "phone"
(
    id                     varchar
        constraint phone_pk primary key default uuid_generate_v4(),
    cnaps_employee_id varchar,
    value                  varchar not null unique,
    constraint phone_cnaps_employee foreign key (cnaps_employee_id) references "cnaps_employee" (id)
);
