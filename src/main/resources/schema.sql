
create table boleto_entity (id varchar(255) not null, cnab_file_id varchar(255), line_number integer not null, raw varchar(255), structured varchar(255), type varchar(255), primary key (id));
