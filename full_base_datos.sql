-- Generado por Oracle SQL Developer Data Modeler 24.3.1.351.0831
--   en:        2026-05-04 22:21:47 CLT
--   sitio:      Oracle Database 21c
--   tipo:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE AUTOR 
    ( 
     id_autor     NUMBER (10)  NOT NULL , 
     nombre_autor VARCHAR2 (50)  NOT NULL , 
     nacionalidad VARCHAR2 (50)  NOT NULL 
    ) 
;

ALTER TABLE AUTOR 
    ADD CONSTRAINT AUTOR_PK PRIMARY KEY ( id_autor ) ;

CREATE TABLE CARRITO 
    ( 
     id_carrito     NUMBER (10)  NOT NULL , 
     cantidad       NUMBER (500)  NOT NULL , 
     fecha_agregado DATE  NOT NULL 
    ) 
;

ALTER TABLE CARRITO 
    ADD CONSTRAINT CARRITO_PK PRIMARY KEY ( id_carrito ) ;

CREATE TABLE DEMOGRAFIA 
    ( 
     id_demografia NUMBER (10)  NOT NULL , 
     nombre        VARCHAR2 (50)  NOT NULL 
    ) 
;

ALTER TABLE DEMOGRAFIA 
    ADD CONSTRAINT DEMOGRAFIA_PK PRIMARY KEY ( id_demografia ) ;

CREATE TABLE ESTILO 
    ( 
     id_estilo     NUMBER (10)  NOT NULL , 
     nombre_estilo VARCHAR2 (50)  NOT NULL 
    ) 
;

ALTER TABLE ESTILO 
    ADD CONSTRAINT ESTILO_PK PRIMARY KEY ( id_estilo ) ;

CREATE TABLE genero_manga 
    ( 
     GENEROS_id_genero NUMBER (10)  NOT NULL , 
     MANGA_id_manga    NUMBER (10)  NOT NULL 
    ) 
;

ALTER TABLE genero_manga 
    ADD CONSTRAINT genero_manga_PK PRIMARY KEY ( GENEROS_id_genero, MANGA_id_manga ) ;

CREATE TABLE GENEROS 
    ( 
     id_genero     NUMBER (10)  NOT NULL , 
     nombre_genero VARCHAR2 (50)  NOT NULL 
    ) 
;

ALTER TABLE GENEROS 
    ADD CONSTRAINT GENEROS_PK PRIMARY KEY ( id_genero ) ;

CREATE TABLE INVENTARIO 
    ( 
     id_inventario  NUMBER (10)  NOT NULL , 
     stock          NUMBER (50)  NOT NULL , 
     bodega         VARCHAR2 (50)  NOT NULL , 
     MANGA_id_manga NUMBER (10)  NOT NULL 
    ) 
;

ALTER TABLE INVENTARIO 
    ADD CONSTRAINT INVENTARIO_PK PRIMARY KEY ( id_inventario ) ;

CREATE TABLE MANGA 
    ( 
     id_manga                 NUMBER (10)  NOT NULL , 
     titulo                   VARCHAR2 (50)  NOT NULL , 
     precio                   NUMBER (10)  NOT NULL , 
     sinopsis                 VARCHAR2 (50)  NOT NULL , 
     AUTOR_id_autor           NUMBER (10)  NOT NULL , 
     ORIGEN_id_origen         NUMBER (10)  NOT NULL , 
     ESTILO_id_estilo         NUMBER (10)  NOT NULL , 
     DEMOGRAFIA_id_demografia NUMBER (10)  NOT NULL 
    ) 
;

ALTER TABLE MANGA 
    ADD CONSTRAINT MANGA_PK PRIMARY KEY ( id_manga ) ;

CREATE TABLE ORIGEN 
    ( 
     id_origen          NUMBER (10)  NOT NULL , 
     pais               VARCHAR2 (50)  NOT NULL , 
     editorial_original VARCHAR2 (50)  NOT NULL 
    ) 
;

ALTER TABLE ORIGEN 
    ADD CONSTRAINT ORIGEN_PK PRIMARY KEY ( id_origen ) ;

CREATE TABLE PAGO 
    ( 
     id_pago           NUMBER (10)  NOT NULL , 
     monto             NUMBER (100)  NOT NULL , 
     metodo_de_pago    NUMBER (100)  NOT NULL , 
     fecha_transaccion DATE  NOT NULL 
    ) 
;

ALTER TABLE PAGO 
    ADD CONSTRAINT PAGO_PK PRIMARY KEY ( id_pago ) ;

CREATE TABLE Relation_12 
    ( 
     PAGO_id_pago       NUMBER (10)  NOT NULL , 
     USUARIO_id_usuario NUMBER (10)  NOT NULL 
    ) 
;

ALTER TABLE Relation_12 
    ADD CONSTRAINT Relation_12_PK PRIMARY KEY ( PAGO_id_pago, USUARIO_id_usuario ) ;

CREATE TABLE RESEÑA 
    ( 
     id_reseña          NUMBER (10)  NOT NULL , 
     comentario         VARCHAR2 (50)  NOT NULL , 
     calificacion       NUMBER  NOT NULL , 
     USUARIO_id_usuario NUMBER (10)  NOT NULL , 
     MANGA_id_manga     NUMBER (10)  NOT NULL 
    ) 
;

ALTER TABLE RESEÑA 
    ADD CONSTRAINT RESEÑA_PK PRIMARY KEY ( id_reseña ) ;

CREATE TABLE USUARIO 
    ( 
     id_usuario         NUMBER (10)  NOT NULL , 
     nombre             VARCHAR2 (50)  NOT NULL , 
     correo             VARCHAR2 (50)  NOT NULL , 
     dirreccion         VARCHAR2 (50)  NOT NULL , 
     contraseña         VARCHAR2 (50)  NOT NULL , 
     CARRITO_id_carrito NUMBER (10)  NOT NULL 
    ) 
;

ALTER TABLE USUARIO 
    ADD CONSTRAINT USUARIO_PK PRIMARY KEY ( id_usuario ) ;

ALTER TABLE genero_manga 
    ADD CONSTRAINT genero_manga_GENEROS_FK FOREIGN KEY 
    ( 
     GENEROS_id_genero
    ) 
    REFERENCES GENEROS 
    ( 
     id_genero
    ) 
;

ALTER TABLE genero_manga 
    ADD CONSTRAINT genero_manga_MANGA_FK FOREIGN KEY 
    ( 
     MANGA_id_manga
    ) 
    REFERENCES MANGA 
    ( 
     id_manga
    ) 
;

ALTER TABLE INVENTARIO 
    ADD CONSTRAINT INVENTARIO_MANGA_FK FOREIGN KEY 
    ( 
     MANGA_id_manga
    ) 
    REFERENCES MANGA 
    ( 
     id_manga
    ) 
;

ALTER TABLE MANGA 
    ADD CONSTRAINT MANGA_AUTOR_FK FOREIGN KEY 
    ( 
     AUTOR_id_autor
    ) 
    REFERENCES AUTOR 
    ( 
     id_autor
    ) 
;

ALTER TABLE MANGA 
    ADD CONSTRAINT MANGA_DEMOGRAFIA_FK FOREIGN KEY 
    ( 
     DEMOGRAFIA_id_demografia
    ) 
    REFERENCES DEMOGRAFIA 
    ( 
     id_demografia
    ) 
;

ALTER TABLE MANGA 
    ADD CONSTRAINT MANGA_ESTILO_FK FOREIGN KEY 
    ( 
     ESTILO_id_estilo
    ) 
    REFERENCES ESTILO 
    ( 
     id_estilo
    ) 
;

ALTER TABLE MANGA 
    ADD CONSTRAINT MANGA_ORIGEN_FK FOREIGN KEY 
    ( 
     ORIGEN_id_origen
    ) 
    REFERENCES ORIGEN 
    ( 
     id_origen
    ) 
;

ALTER TABLE Relation_12 
    ADD CONSTRAINT Relation_12_PAGO_FK FOREIGN KEY 
    ( 
     PAGO_id_pago
    ) 
    REFERENCES PAGO 
    ( 
     id_pago
    ) 
;

ALTER TABLE Relation_12 
    ADD CONSTRAINT Relation_12_USUARIO_FK FOREIGN KEY 
    ( 
     USUARIO_id_usuario
    ) 
    REFERENCES USUARIO 
    ( 
     id_usuario
    ) 
;

ALTER TABLE RESEÑA 
    ADD CONSTRAINT RESEÑA_MANGA_FK FOREIGN KEY 
    ( 
     MANGA_id_manga
    ) 
    REFERENCES MANGA 
    ( 
     id_manga
    ) 
;

ALTER TABLE RESEÑA 
    ADD CONSTRAINT RESEÑA_USUARIO_FK FOREIGN KEY 
    ( 
     USUARIO_id_usuario
    ) 
    REFERENCES USUARIO 
    ( 
     id_usuario
    ) 
;

ALTER TABLE USUARIO 
    ADD CONSTRAINT USUARIO_CARRITO_FK FOREIGN KEY 
    ( 
     CARRITO_id_carrito
    ) 
    REFERENCES CARRITO 
    ( 
     id_carrito
    ) 
;



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            13
-- CREATE INDEX                             0
-- ALTER TABLE                             25
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
