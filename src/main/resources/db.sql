;              
CREATE USER IF NOT EXISTS "SA" SALT 'c5213a6df1336582' HASH 'b47693eb73a352c68e08104b25aedc8d26ed9b84596d54e2353ebd17b4123c16' ADMIN;          
CREATE SEQUENCE "PUBLIC"."HIBERNATE_SEQUENCE" START WITH 2;    
CREATE MEMORY TABLE "PUBLIC"."BOOK_ENTITY"(
    "ID" INTEGER NOT NULL,
    "AUTHOR_NAME" VARCHAR(255),
    "BOOK_NAME" VARCHAR(255),
    "IS_RECOMMENDED" BOOLEAN NOT NULL,
    "PRICE" DOUBLE NOT NULL
);     
ALTER TABLE "PUBLIC"."BOOK_ENTITY" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_1" PRIMARY KEY("ID");   
-- 12 +/- SELECT COUNT(*) FROM PUBLIC.BOOK_ENTITY;             
INSERT INTO "PUBLIC"."BOOK_ENTITY" VALUES
(1, 'Lisa Wingate', 'Before We Were Yours: A Novel', FALSE, 340.0),
(2, 'Barbara Davis', 'When Never Comes', FALSE, 179.0),
(3, 'Giles Andreae, Guy Parker-Rees', 'Giraffes Can''t Dance', FALSE, 200.5),
(4, 'Kristin Hannah', 'The Great Alone: A Novel Kristin Hannah', TRUE, 495.0),
(5, 'Annejet van der Zijl, Michele Hutchison', 'An American Princess: The Many Lives of Allene Tew', TRUE, 149.0),
(6, 'Anthony William', 'Medical Medium Life-Changing Foods', FALSE, 929.7000122070312),
(7, 'Gaz Oakley', 'Vegan 100', FALSE, 897.9600219726562),
(8, 'Carol McCloud', 'Have You Filled A Bucket Today?', FALSE, 290.05999755859375),
(9, 'Eric Carle', 'The Very Hungry Caterpillar', FALSE, 208.50999450683594),
(10, 'Angie Thomas', 'The Hate U Give', FALSE, 319.1600036621094),
(11, 'Kate Quinn', 'The Alice Network', FALSE, 393.2200012207031),
(12, 'British Library', 'Harry Potter - A History of Magic', FALSE, 1379.780029296875);         
CREATE MEMORY TABLE "PUBLIC"."ORDER_ENTITY"(
    "ID" INTEGER NOT NULL,
    "BOOK_ID" INTEGER,
    "USER_ID" INTEGER
);        
ALTER TABLE "PUBLIC"."ORDER_ENTITY" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.ORDER_ENTITY;             
CREATE MEMORY TABLE "PUBLIC"."USER_ENTITY"(
    "ID" INTEGER NOT NULL,
    "DATE_OF_BIRTH" VARCHAR(255) NOT NULL,
    "NAME" VARCHAR(255),
    "PASSWORD" VARCHAR(255) NOT NULL,
    "SURNAME" VARCHAR(255),
    "USERNAME" VARCHAR(255) NOT NULL
);           
ALTER TABLE "PUBLIC"."USER_ENTITY" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("ID");   
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.USER_ENTITY;              
INSERT INTO "PUBLIC"."USER_ENTITY" VALUES
(1, '11/12/1991', 'john', '$2a$10$K7zCkU0O8omlvDHahF1ml.kyDsBvMXJqXNrCgt7xWPyUTQ966EKyS', 'doe', 'john.doe');        
ALTER TABLE "PUBLIC"."USER_ENTITY" ADD CONSTRAINT "PUBLIC"."UK_2JSK4EAKD0RMVYBO409WGWXUW" UNIQUE("USERNAME");  
ALTER TABLE "PUBLIC"."ORDER_ENTITY" ADD CONSTRAINT "PUBLIC"."FK4NA8YKXYEMVS9AUHONDWOJ7IR" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USER_ENTITY"("ID") NOCHECK;              
ALTER TABLE "PUBLIC"."ORDER_ENTITY" ADD CONSTRAINT "PUBLIC"."FK9418EAOAPAOGUWI0SAXJK4H43" FOREIGN KEY("BOOK_ID") REFERENCES "PUBLIC"."BOOK_ENTITY"("ID") NOCHECK;              
