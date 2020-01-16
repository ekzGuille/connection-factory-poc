CLEAR SCR 
DISCONNECT  
CONNECT SYS AS SYSDBA; 

DROP USER GUILLERMO; 

CREATE USER GUILLERMO IDENTIFIED BY Guillermo; 
GRANT DBA TO GUILLERMO; 
GRANT ALL PRIVILEGES TO GUILLERMO; 

DROP TABLE TAXI cascade constraints; 
DROP TABLE TAXISTA cascade constraints; 
DROP TABLE TALLER cascade constraints; 
DROP TABLE CONDUCIR cascade constraints; 
DROP TABLE REVISAR cascade constraints; 

CREATE TABLE TAXI( 
    d_mat VARCHAR2(10), 
    d_tfno NUMBER(9), 
    d_km NUMBER(6), 
    CONSTRAINT PK_d_mat_TAXI PRIMARY KEY (d_mat), 
    CONSTRAINT U_d_tfno UNIQUE (d_tfno), 
    CONSTRAINT CK_NN_d_km CHECK (d_km IS NOT NULL) 
);

CREATE TABLE TAXISTA( 
    d_dni VARCHAR2(10), 
    d_nom VARCHAR2(40), 
    d_tfno NUMBER(9), 
    d_dir VARCHAR2(60), 
    d_edad NUMBER(2), 
    CONSTRAINT PK_d_dni_TAXISTA PRIMARY KEY (d_dni), 
    CONSTRAINT CK_NN_d_nom_TAXISTA CHECK (d_nom IS NOT NULL) 
);

CREATE TABLE TALLER( 
    d_cod NUMBER(4), 
    d_nom1 VARCHAR2(40), 
    d_tfno NUMBER(9), 
    d_nom VARCHAR2(40), 
    CONSTRAINT PK_d_cod PRIMARY KEY (d_cod), 
    CONSTRAINT CK_NN_d_nom_TALLER CHECK (d_nom IS NOT NULL) 
);

CREATE TABLE CONDUCIR( 
    d_dni VARCHAR2(10), 
    d_mat VARCHAR2(10), 
    d_fecha DATE, 
    d_hora_inicio DATE, 
    d_hora_fin DATE, 
    CONSTRAINT PK_d_dni_d_fecha_CONDUCIR PRIMARY KEY (d_dni,d_fecha), 
    CONSTRAINT CK_NN_d_mat CHECK (d_mat IS NOT NULL), 
    CONSTRAINT CK_NN_d_hora_inicio CHECK (d_hora_inicio IS NOT NULL), 
    CONSTRAINT FK_d_dni FOREIGN KEY (d_dni) REFERENCES TAXISTA(d_dni), 
    CONSTRAINT FK_d_mat_CONDUCIR FOREIGN KEY (d_mat) REFERENCES TAXI(d_mat), 
    CONSTRAINT CK_HORA_MAYOR CHECK (d_hora_inicio <= d_hora_fin) 
);

CREATE TABLE REVISAR( 
    d_cod NUMBER(4), 
    d_mat VARCHAR2(10), 
    d_fecha DATE, 
    CONSTRAINT PK_d_mat_d_fecha_REVISAR PRIMARY KEY (d_mat,d_fecha), 
    CONSTRAINT CK_NN_d_cod CHECK (d_cod IS NOT NULL), 
    CONSTRAINT FK_d_cod FOREIGN KEY (d_cod) REFERENCES TALLER(d_cod), 
    CONSTRAINT FK_d_mat_REVISAR FOREIGN KEY (d_mat) REFERENCES TAXI(d_mat) 
);


GRANT SELECT, INSERT, UPDATE, DELETE ON TAXI TO GUILLERMO;
GRANT SELECT, INSERT, UPDATE, DELETE ON TAXISTA TO GUILLERMO;
GRANT SELECT, INSERT, UPDATE, DELETE ON TALLER TO GUILLERMO;
GRANT SELECT, INSERT, UPDATE, DELETE ON CONDUCIR TO GUILLERMO;
GRANT SELECT, INSERT, UPDATE, DELETE ON REVISAR TO GUILLERMO;

INSERT INTO TAXI (d_mat, d_tfno, d_km) VALUES ('1111AAA', 978111111, 688); 
INSERT INTO TAXI (d_mat, d_tfno, d_km) VALUES ('1111BBB', 978222222, 1002); 
INSERT INTO TAXI (d_mat, d_tfno, d_km) VALUES ('1111CCC', 978333333, 882); 

INSERT INTO TAXISTA (d_dni, d_nom, d_tfno, d_dir, d_edad) VALUES ('21183288A','Juan Perez',987111111,'Calle 1',32); 
INSERT INTO TAXISTA (d_dni, d_nom, d_tfno, d_dir, d_edad) VALUES ('21183288B','Carlos Lorenz',987222222,'Calle 2',52); 
INSERT INTO TAXISTA (d_dni, d_nom, d_tfno, d_dir, d_edad) VALUES ('21183288C','Maria Martín',987333333,'Calle 3',40); 

INSERT INTO TALLER (d_cod, d_nom1, d_tfno, d_nom) VALUES (1111,'Afredo',988111111,'Pascual'); 
INSERT INTO TALLER (d_cod, d_nom1, d_tfno, d_nom) VALUES (2222,'Roberto',988222222,'Santiago'); 
INSERT INTO TALLER (d_cod, d_nom1, d_tfno, d_nom) VALUES (3333,'Julian',988333333,'Jorge'); 

INSERT INTO CONDUCIR (d_dni, d_mat, d_fecha, d_hora_inicio, d_hora_fin) VALUES ('21183288A','1111AAA',TO_DATE('12/04/2010','dd/mm/yyyy'),TO_DATE('12/04/2010 11:02:44','dd/mm/yyyy hh24:mi:ss'),TO_DATE('12/04/2010 13:02:44','dd/mm/yyyy hh24:mi:ss')); 
INSERT INTO CONDUCIR (d_dni, d_mat, d_fecha, d_hora_inicio, d_hora_fin) VALUES ('21183288C','1111BBB',TO_DATE('13/04/2010','dd/mm/yyyy'),TO_DATE('13/04/2010 21:02:44','dd/mm/yyyy hh24:mi:ss'),TO_DATE('14/04/2010 15:02:44','dd/mm/yyyy hh24:mi:ss')); 
INSERT INTO CONDUCIR (d_dni, d_mat, d_fecha, d_hora_inicio, d_hora_fin) VALUES ('21183288B','1111CCC',TO_DATE('14/04/2010','dd/mm/yyyy'),TO_DATE('14/04/2010 20:02:44','dd/mm/yyyy hh24:mi:ss'),TO_DATE('14/04/2010 23:02:44','dd/mm/yyyy hh24:mi:ss')); 

INSERT INTO REVISAR (d_cod, d_mat, d_fecha) VALUES (2222,'1111CCC',TO_DATE('20/04/2010','dd/mm/yyyy')); 
INSERT INTO REVISAR (d_cod, d_mat, d_fecha) VALUES (1111,'1111BBB',TO_DATE('19/04/2010','dd/mm/yyyy')); 
INSERT INTO REVISAR (d_cod, d_mat, d_fecha) VALUES (3333,'1111AAA',TO_DATE('17/04/2010','dd/mm/yyyy')); 

COMMIT;
