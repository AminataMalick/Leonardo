-- Geoloc

INSERT INTO Geoloc ( id_Geoloc, latitude, longitude )
   VALUES ( 1, 1.1 , 1.1 );

INSERT INTO Geoloc ( id_Geoloc, latitude, longitude )
   VALUES ( 2, 1.2 , 1.2 );

INSERT INTO Geoloc ( id_Geoloc, latitude, longitude )
   VALUES ( 3, 1.3 , 1.3 );



-- User

INSERT INTO User ( id_User, firstName_User, lastName_User, email_User, pwd_User, token_User )
   VALUES ( 1, 'celine', 'botte', 'ce@bo', 'cebo', 'secretcebo');

INSERT INTO User ( id_User, firstName_User, lastName_User, email_User, pwd_User, token_User )
   VALUES ( 2, 'melanie', 'chasane', 'me@ce', 'mece', 'secretmece');

INSERT INTO User ( id_User, firstName_User, lastName_User, email_User, pwd_User, token_User )
   VALUES ( 3, 'antoine', 'carrier', 'an@ca', 'anca', 'secretanca');

INSERT INTO User ( id_User, firstName_User, lastName_User, email_User, pwd_User, token_User )
   VALUES ( 4, 'aminata', 'ba', 'am@ba', 'amba', 'secretamba');

INSERT INTO User ( id_User, firstName_User, lastName_User, email_User, pwd_User, token_User )
   VALUES ( 5, 'solal', 'bedeau', 'so@be', 'sobe', 'secretsobe');

INSERT INTO User ( id_User, firstName_User, lastName_User, email_User, pwd_User, token_User )
   VALUES ( 6, 'pierre', 'belabbes', 'pi@be', 'pibe', 'secretpibe');

INSERT INTO User ( id_User, firstName_User, lastName_User, email_User, pwd_User, token_User )
   VALUES ( 7, 'superMelanie', 'super', 'su@su', 'susu', 'secretsusu' );

INSERT INTO User ( id_User, firstName_User, lastName_User, email_User, pwd_User, token_User )
   VALUES ( 8, 'superCeline', 'superC', 'su@ce', 'suce', 'secretsuce' );

-- Member

INSERT INTO Member ( id_Member, id_User, id_Geoloc )
 VALUES ( 1, 1, 1);

INSERT INTO Member ( id_Member, id_User, id_Geoloc )
 VALUES ( 2, 2, 1);

INSERT INTO Member ( id_Member, id_User, id_Geoloc )
 VALUES ( 3, 3, 1);

INSERT INTO Member ( id_Member, id_User, id_Geoloc )
 VALUES ( 4, 4, 2);

INSERT INTO Member ( id_Member, id_User, id_Geoloc )
 VALUES ( 5, 5, 2);

INSERT INTO Member ( id_Member, id_User, id_Geoloc )
 VALUES ( 6, 6, 3);







-- Admin
INSERT INTO Admin ( id_Admin, id_User)
   VALUES ( 1, 7);

INSERT INTO Admin ( id_Admin, id_User)
   VALUES ( 2, 8);





-- Shop

INSERT INTO Shop ( id_Shop, name_Shop, description_Shop, id_Member )
   VALUES ( 1, 'leShopDeMamie', 'Le jolie shop de Mamie, pleins de bons produits', 2 );

INSERT INTO Shop ( id_Shop, name_Shop, description_Shop, id_Member )
   VALUES ( 2, 'LeLapinVert', 'Vente de jolies produits pour le plaisir de chacun' , 1);

INSERT INTO Shop ( id_Shop, name_Shop, description_Shop, id_Member )
   VALUES ( 3, 'PommeDePin', 'Qui veut mes jolies petites pommes ?', 5 );




-- ShopChat

INSERT INTO ShopChat ( id_Chat, id_Shop, id_Member)
   VALUES ( 1, 1, 1 );

INSERT INTO ShopChat ( id_Chat, id_Shop, id_Member)
   VALUES ( 2, 2, 5 );

INSERT INTO ShopChat ( id_Chat, id_Shop, id_Member)
   VALUES ( 3, 3, 6 );


-- Message
-- Format date : YYYY-MM-DD


INSERT INTO Message ( id_Message, date_Message, content_Message, id_Member, id_Chat )
   VALUES ( 1, '2010-01-01', 'Bien le bonjour petite perruche', 1, 1 );

INSERT INTO Message ( id_Message, date_Message, content_Message, id_Member, id_Chat )
   VALUES ( 2, '2010-01-01', 'Comment allez-vous ?', 2, 1 );

INSERT INTO Message ( id_Message, date_Message, content_Message, id_Member, id_Chat )
   VALUES ( 3, '2010-01-01', 'Très bien et vous ?', 1, 1 );

INSERT INTO Message ( id_Message, date_Message, content_Message, id_Member, id_Chat )
   VALUES ( 4, '2010-01-01', 'Bien, merci !', 2, 1 );

INSERT INTO Message ( id_Message, date_Message, content_Message, id_Member, id_Chat )
   VALUES ( 5, '2017-12-20', 'Hola qui est la ?', 5, 2 );

INSERT INTO Message ( id_Message, date_Message, content_Message, id_Member, id_Chat )
   VALUES ( 6, '2017-12-20', 'Personne', 1, 2 );

INSERT INTO Message ( id_Message, date_Message, content_Message, id_Member, id_Chat )
   VALUES ( 7, '2017-12-20', 'Prout', 6, 3 );



-- RetailPoint

INSERT INTO RetailPoint ( id_Retail, name_Retail, id_Geoloc )
   VALUES ( 1, 'Mamie1', 1 );

INSERT INTO RetailPoint ( id_Retail, name_Retail, id_Geoloc )
   VALUES ( 2, 'Mamie2', 1 );

INSERT INTO RetailPoint ( id_Retail, name_Retail, id_Geoloc )
   VALUES ( 3, 'Lapin1', 1 );

INSERT INTO RetailPoint ( id_Retail, name_Retail, id_Geoloc )
   VALUES ( 4, 'Lapin2', 2 );

INSERT INTO RetailPoint ( id_Retail, name_Retail, id_Geoloc )
   VALUES ( 5, 'Pomme1', 3 );




-- ShopRetail

INSERT INTO ShopRetail ( id_Shop, id_Retail )
   VALUES ( 1, 1 );

INSERT INTO ShopRetail ( id_Shop, id_Retail )
   VALUES ( 1, 2 );

INSERT INTO ShopRetail ( id_Shop, id_Retail )
   VALUES ( 2, 3 );

INSERT INTO ShopRetail ( id_Shop, id_Retail )
   VALUES ( 2, 4 );

INSERT INTO ShopRetail ( id_Shop, id_Retail )
   VALUES ( 3, 5 );




-- Product

INSERT INTO Product ( id_Product, name_Product, UnityPrice, id_Shop )
   VALUES ( 1, 'salade', 1.0, 1 );

INSERT INTO Product ( id_Product, name_Product, UnityPrice, id_Shop )
   VALUES ( 2, 'pomme', 3.0, 3 );

INSERT INTO Product ( id_Product, name_Product, UnityPrice, id_Shop )
   VALUES ( 3, 'carotte', 1.5, 2 );

INSERT INTO Product ( id_Product, name_Product, UnityPrice, id_Shop )
   VALUES ( 4, 'tomate', 1.0, 1 );

INSERT INTO Product ( id_Product, name_Product, UnityPrice, id_Shop )
   VALUES ( 5, 'patate', 0.5, 1 );

INSERT INTO Product ( id_Product, name_Product, UnityPrice, id_Shop )
   VALUES ( 6, 'poireau', 1.0, 2 );




-- Tag

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 1, 'salade');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 2, 'pomme');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 3, 'carotte');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 4, 'tomate');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 5, 'patate');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 6, 'poireau');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 7, 'mamie');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 8, 'lapin');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 9, 'pin');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 10, 'potage');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 11, 'bio');

INSERT INTO Tag ( id_Tag, keyword)
   VALUES ( 12, 'jardin');




-- ProductTag

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 1, 1);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 1, 12);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 2, 2);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 3, 3);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 4, 4);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 5, 5);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 6, 6);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 6, 11);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 4, 10);


INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 1, 7);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 4, 7);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 5, 7);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 3, 8);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 6, 8);

INSERT INTO ProductTag ( id_Product, id_Tag)
   VALUES ( 2, 9);




-- ShopMember

INSERT INTO ShopMember ( id_Shop, id_Member)
   VALUES ( 1, 3);
















