CREATE DATABASE IF NOT EXISTS BDbyMEL ;

USE BDbyMEL ;


CREATE TABLE IF NOT EXISTS Geoloc (
        id_Geoloc bigint,
        latitude double NOT NULL,
        longitude double NOT NULL,
    PRIMARY KEY (id_Geoloc),
    CHECK (id_Geoloc >0)
);

CREATE TABLE IF NOT EXISTS Member (
        id_Member bigint,
        firstName_Member varchar(255) NOT NULL,
        lastName_Member varchar(255) NOT NULL,
        email_Member varchar(255),
    pwd_Member varchar(255) NOT NULL,
    id_Geoloc bigint,
    PRIMARY KEY (id_Member),
    FOREIGN KEY (id_Geoloc) REFERENCES Geoloc(id_Geoloc)
);
    
CREATE TABLE IF NOT EXISTS Chat (
        id_Chat bigint,
    PRIMARY KEY (id_Chat)
);

CREATE TABLE IF NOT EXISTS Message (
        id_Message bigint,
        date_Message date NOT NULL,
        content_Message varchar(255) NOT NULL,
        id_Chat bigint,
    PRIMARY KEY (id_Message),
    FOREIGN KEY (id_Chat) REFERENCES Chat(id_Chat)
);

CREATE TABLE IF NOT EXISTS ChatMember (
        id_Member bigint,
        id_Chat bigint,
    FOREIGN KEY (id_Member) REFERENCES Member(id_Member),
    FOREIGN KEY (id_Chat) REFERENCES Chat(id_Chat)
);

CREATE TABLE IF NOT EXISTS Admin (
        id_Admin bigint,
        firstName_Admin varchar(255) NOT NULL,
        lastName_Admin varchar(255) NOT NULL,
        email_Admin varchar(255),
    pwd_Admin varchar(255) NOT NULL,
    PRIMARY KEY (id_Admin)
);

CREATE TABLE IF NOT EXISTS Shop (
        id_Shop bigint,
        name_Shop varchar(255) NOT NULL,
        description_Shop varchar(255),
    PRIMARY KEY (id_Shop)
);

CREATE TABLE IF NOT EXISTS RetailPoint (
        id_Retail bigint,
        name_Retail varchar(255) NOT NULL,
    id_Geoloc bigint,
    PRIMARY KEY (id_Retail),
    FOREIGN KEY (id_Geoloc) REFERENCES Geoloc(id_Geoloc)
);

CREATE TABLE IF NOT EXISTS ShopRetail (
        id_Retail bigint,
        id_Shop bigint,
    FOREIGN KEY (id_Retail) REFERENCES RetailPoint(id_Retail),
    FOREIGN KEY (id_Shop) REFERENCES Shop(id_Shop)
);

CREATE TABLE IF NOT EXISTS Product (
        id_Product bigint,
        name_Product varchar(255) NOT NULL,
    UnityPrice float,
    id_Shop bigint,
    PRIMARY KEY (id_Product),
    FOREIGN KEY (id_Shop) REFERENCES Shop(id_Shop)
);

CREATE TABLE IF NOT EXISTS Tag (
        id_Tag bigint,
        keyword varchar(255) NOT NULL,
    PRIMARY KEY (id_Tag)
);

CREATE TABLE IF NOT EXISTS ProductTag (
        id_Product bigint,
        id_Tag bigint,
        FOREIGN KEY (id_Product) REFERENCES Product(id_Product),
    FOREIGN KEY (id_Tag) REFERENCES Tag(id_Tag)
);







