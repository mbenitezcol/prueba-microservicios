-- src/main/resources/setup/setup-oracle.sql
CREATE TABLESPACE prueba_data DATAFILE 'prueba_data.dbf' SIZE 100M AUTOEXTEND ON;
CREATE TABLESPACE prueba_index DATAFILE 'prueba_index.dbf' SIZE 100M AUTOEXTEND ON;

CREATE USER prueba IDENTIFIED BY prueba
DEFAULT TABLESPACE prueba_data
TEMPORARY TABLESPACE TEMP
QUOTA UNLIMITED ON prueba_data
QUOTA UNLIMITED ON prueba_index;

GRANT CONNECT, RESOURCE, DBA TO prueba;
