

CREATE TABLE IF NOT EXISTS Geoloc (
        id_Geoloc bigint,
        latitude double NOT NULL,
        longitude double NOT NULL,
    PRIMARY KEY (id_Geoloc),
    CHECK (id_Geoloc >0)
);

CREATE TABLE IF NOT EXISTS User (
        id_User bigint,
        firstName_User varchar(255) NOT NULL,
        lastName_User varchar(255) NOT NULL,
        email_User varchar(255) NOT NULL,
    pwd_User varchar(255) NOT NULL,
    token_User varchar(255),
    PRIMARY KEY (id_User)
);

CREATE TABLE IF NOT EXISTS Member (
        id_Member bigint,
        id_User bigint UNIQUE NOT NULL,
    id_Geoloc bigint,
    PRIMARY KEY (id_Member),
    FOREIGN KEY (id_Geoloc) REFERENCES Geoloc(id_Geoloc),
    FOREIGN KEY (id_User) REFERENCES User(id_User)
);
    

CREATE TABLE IF NOT EXISTS Admin (
        id_Admin bigint,
        id_User bigint UNIQUE NOT NULL,
    id_Geoloc bigint,
    PRIMARY KEY (id_Admin),
    FOREIGN KEY (id_Geoloc) REFERENCES Geoloc(id_Geoloc),
    FOREIGN KEY (id_User) REFERENCES User(id_User)
);





CREATE TABLE IF NOT EXISTS Shop (
        id_Shop bigint,
        name_Shop varchar(255) NOT NULL,
        description_Shop text,
    id_Member bigint,
    PRIMARY KEY (id_Shop),
    FOREIGN KEY (id_Member) REFERENCES Member(id_Member)

);

CREATE TABLE IF NOT EXISTS ShopChat (
        id_Chat bigint,
    id_Shop bigint NOT NULL,
    id_Member bigint NOT NULL,
    PRIMARY KEY (id_Chat),
    FOREIGN KEY (id_Shop) REFERENCES Shop(id_Shop),
    FOREIGN KEY (id_Member) REFERENCES Member(id_Member)
);

CREATE TABLE IF NOT EXISTS RetailPoint (
        id_Retail bigint,
        name_Retail varchar(255) NOT NULL,
    id_Geoloc bigint,
    PRIMARY KEY (id_Retail),
    FOREIGN KEY (id_Geoloc) REFERENCES Geoloc(id_Geoloc)
);

CREATE TABLE IF NOT EXISTS ShopRetail (
        id_Shop bigint,
        id_Retail bigint,
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


CREATE TABLE IF NOT EXISTS ShopMember (
        id_Shop bigint,
        id_Member bigint,
        FOREIGN KEY (id_Shop) REFERENCES Shop(id_Shop),
    FOREIGN KEY (id_Member) REFERENCES Member(id_Member)
);


CREATE TABLE IF NOT EXISTS Message (
        id_Message bigint,
        date_Message datetime NOT NULL,
        content_Message text NOT NULL,
    id_Member bigint,
        id_Chat bigint,
    PRIMARY KEY (id_Message),
    FOREIGN KEY (id_Chat) REFERENCES ShopChat(id_Chat),
    FOREIGN KEY (id_Member) REFERENCES Member(id_Member)
);


CREATE TABLE IF NOT EXISTS Recommandation (
	id_Recommandation bigint,
	id_Shop bigint,
	grade_Recommandation int, 
	comment_Recommandation varchar(255), 
	id_Member bigint,
	PRIMARY KEY (id_Recommandation),
	FOREIGN KEY (id_Shop) REFERENCES Shop(id_Shop),
	FOREIGN KEY (id_Member) REFERENCES Member(id_Member),
	CHECK (grade_Recommandation >=0 && grade_Recommandation <=5)
);


