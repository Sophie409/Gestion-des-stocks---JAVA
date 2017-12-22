CREATE TABLE Produit (
  idProduit INTEGER,
  nom VARCHAR(32) NOT NULL,
  prixUnitaireHT NUMBER,
  quantiteStock INTEGER,
  CONSTRAINT pk_produit PRIMARY KEY (idProduit),
  CONSTRAINT uc_nomUnique UNIQUE (nom)
);

CREATE SEQUENCE seq_id START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trigg_id_auto_increment
BEFORE INSERT ON Produit
FOR EACH ROW

BEGIN
  SELECT seq_id.NEXTVAL INTO :NEW.idProduit
  FROM DUAL;
END;
