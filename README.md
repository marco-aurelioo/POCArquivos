POC - File Writer

Queremos validar:
tempos e consumo de escrita em arquivos grandes:

Setup 0:
Banco H2 in memory.
    console integrado.

Massa de dados 1.000.000 

script apoio

'''

delete from boleto_entity;

select count(*) from boleto_entity;

@loop 200000
insert into boleto_entity(id, cnab_file_id, line_number, structured, type, raw)values( random_uuid() ,1 , ? ,'structured','type','123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890');
@statement select count(*) from boleto_entity;

select * from boleto_entity ;

'''


postgres
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
truncate boleto_entity ;

select count(1) from boleto_entity be
select * from boleto_entity be where line_number = 1000000

DO $$
BEGIN
FOR r IN 1..1000000
LOOP
insert into boleto_entity(id, cnab_file_id, line_number, structured, type, raw)values( uuid_generate_v4(),1 , r ,'structured','type', to_char(r, 'fm0000000') || '-9012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890');

	end loop;
end $$;



