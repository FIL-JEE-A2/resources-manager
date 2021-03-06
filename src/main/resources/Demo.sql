--USER
INSERT INTO USER(ID,ADMIN,FIRST_NAME,LAST_NAME,LOGIN,MAIL,PASSWORD,PHONE) 
VALUES(1,TRUE,'Jean','AIMARE','admin','user1001@yopmail.com','','118 218');
INSERT INTO USER(ID,ADMIN,FIRST_NAME,LAST_NAME,LOGIN,MAIL,PASSWORD,PHONE) 
VALUES(2,FALSE,'Michel','BALKO','michel','user1002@yopmail.com','','06 12 13 14 15 16');

--RESOURCE TYPE
INSERT INTO RESOURCE_TYPE(ID,TYPE) VALUES(1,'Voiture');
INSERT INTO RESOURCE_TYPE(ID,TYPE) VALUES(2,'Ordinateur');
INSERT INTO RESOURCE_TYPE(ID,TYPE) VALUES(3,'Projecteur');
INSERT INTO RESOURCE_TYPE(ID,TYPE) VALUES(4,'Salle');
INSERT INTO RESOURCE_TYPE(ID,TYPE) VALUES(5,'Téléphone');

--RESOURCE
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID)   
VALUES(1,'Voiture de fonction Clio noire','Parking 1','Renault CLIO',2,1);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID)  
VALUES(2,'Voiture de fonction Peugeot 105 bleue','Parking 2','Peugeot 105 B',2,1);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(3,'Voiture de fonction Peugeot 105 verte','Parking 2','Peugeot 105 V',2,1);

INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(4,'Ordinateur IBM 105','Bureau 104','Ordi IBM 105',2,2);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(5,'Ordinateur DELL 154','Bureau 456','Ordi DELL 154',2,2);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(6,'Ordinateur ACER','Bureau 112','Ordi ACER',2,2);

INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(7,'Projecteur 1 - lampe ok','Reserve 1','Projo 1',2,3);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(8,'Projecteur 2 - lampe HS','Reserve 1','Projo 2',2,3);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(9,'Projecteur 3 - lampe ok','Reserve 2','Projo 3',2,3);

INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(10,'Salle avec projo','Salle 105','Salle 105',2,4);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(11,'Salle réunion 14 - 20 personnes','Salle 14 (étage 2)','Salle réunion 14',2,4);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(12,'Salle réunion manager','Bureau 106','Salle manager',2,4);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(13,'Salle de pause avec cuisine','Salle 25 (bat 2)','Salle à manger',2,4);

INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(14,'Téléphone "pieuvre" pour réunion téléphone','Réserve 1','Téléphone "pieuvre"',2,5);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(15,'Téléphone avec casque pour assistance','Réserve 2','Téléphone casque',2,5);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(16,'Téléphone pro. avec numéros boîtes','Réserve 1','Téléphone pro.',2,5);
INSERT INTO RESOURCE(ID,DESCRIPTION,LOCALISATION,NAME,USER_ID,RESOURCE_TYPE_ID) 
VALUES(17,'Samsung Galaxy pour démo client','Réserve 1','Téléphone démo Android',2,5);