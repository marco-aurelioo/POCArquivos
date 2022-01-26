POC - File Writer
-------------------------------------------------------
O que queremos validar:

-[x] Tempos e consumo de escrita em arquivos grandes.
-[x] Consumo de memória pelo processo de consulta e escrita.

### Teste preliminar setup:

JDK 11 - compatibilidade com java 8

Banco em memória usando H2

Massa de dados 1.000.000 de linhas com 150 posições para cada linha.

Objeto persistido será composto por mais informações alem de campo texto que irá compor o arquivo.

Script apoio

```
delete from boleto_entity;
select count(*) from boleto_entity;
@loop 100000
insert into boleto_entity(id, cnab_file_id, line_number, structured, type, raw)values( random_uuid() ,1 , ? ,'structured','type','123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890');
@statement select count(*) from boleto_entity;
select * from boleto_entity ;
```

### Teste final setup:

JDK 11 - compatibilidade com java 8

JVM com limite de memória em 512mb para simular aspecto da infra de prod. (-Xms256m -Xmx512m )

Banco postgres dockenizado

Massa de dados 1.000.000 de linhas com 150 posições para cada linha.

Objeto persistido será composto por mais informações alem de campo texto que irá compor o arquivo.
Script apoio
```
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

```

Resultado:

O impacto maior esta na etapa de buscar o conteudo da fonte de dados e serializar no arquivo.
foi comprovado no teste que
- Não é viavel recuperar de forma unica todo o conteudo para serializar no arquivo.
- Deve ser fracionado o retorno em quantidades que não impacte a memória e de tempo do Garbage Collection limpar dados ja utilizados.
- Deve ter uma busca especializada para trazer apenas o conteudo esperado para a utilização no arquivo.
- Os tempos de escrita do arquivo são irrelevantes em relação a leitura e trafego dos dados.

