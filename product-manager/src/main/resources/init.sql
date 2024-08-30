
CREATE TABLE IF NOT EXISTS KORISNICI (
                                         ID INT AUTO_INCREMENT PRIMARY KEY,
                                         NAME VARCHAR(255)
);



CREATE TABLE IF NOT EXISTS PRODUCTS_METADATA (
                                        ID INT AUTO_INCREMENT PRIMARY KEY,
                                        DATUM_VRIJEME_KREIRANJA TIMESTAMP,
                                        NASLOV VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS PRODUCTS (
    NAME VARCHAR(255),
    CIJENA DECIMAL(10,2),
    JEDINICA VARCHAR(255),
    OCJENA INT,
    METADATA_ID INT,
    FOREIGN KEY (METADATA_ID) REFERENCES PRODUCTS_METADATA(ID),
    CONSTRAINT CHK_Ocjena CHECK (OCJENA>=1 AND OCJENA<=5)
    );
