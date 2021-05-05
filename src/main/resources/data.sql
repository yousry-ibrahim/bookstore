-- insert in system user
insert into system_user (id,user_name,password)
values (user_sequence.nextval,'smartD','smartD');

-- insert the promotions
insert into promotion (id,percentage,reason) values (promotion_sequence.nextval,0.15,'EID');
insert into promotion (id,percentage,reason) values (promotion_sequence.nextval,0.2,'RAMADAN');
insert into promotion (id,percentage,reason) values (promotion_sequence.nextval,0.1,'NEW YEAR');
insert into promotion (id,percentage,reason) values (promotion_sequence.nextval,0.5,'SUMMER');
insert into promotion (id,percentage,reason) values (promotion_sequence.nextval,0.3,'WINTER');
insert into promotion (id,percentage,reason) values (promotion_sequence.nextval,0.25,'MANAGEMENT');

-- insert the classifications
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Religion',1);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'scientific',2);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Fantasy',4);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Historical Fiction',3);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Horror',4);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Literary Fiction',2);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Romance',1);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Sci-Fi',1);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Short Stories',3);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Biographies',2);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Cookbooks',5);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'Essays',6);
insert into classification (id,name,promotion_id) values (classification_sequence.nextval,'History',1);

-- insert the authors
insert into author (id,title,name,biography) values (author_sequence.nextval,'Dr','Mostafa Mahmoud','pla pla pla pla');
insert into author (id,title,name,biography) values (author_sequence.nextval,'Dr','Ahmed khaled Tawfik','pla pla pla pla');
insert into author (id,title,name,biography) values (author_sequence.nextval,'Engineer',' Joshua Bloch','pla pla pla pla');
insert into author (id,title,name,biography) values (author_sequence.nextval,'Engineer',' Scott McNealy','pla pla pla pla');
insert into author (id,title,name,biography) values (author_sequence.nextval,'Dr','Dan Russell','pla pla pla pla');
insert into author (id,title,name,biography) values (author_sequence.nextval,'Dr','Herbert Schildt','pla pla pla pla');

-- insert the books
insert into book (id,isbn,title,description,price,author_id,classification_id) values (book_sequence.nextval,'isbn0001','The Spider','pla pla pla pla',50,1,8);
insert into book (id,isbn,title,description,price,author_id,classification_id) values (book_sequence.nextval,'isbn0002','utopia','pla pla pla pla',50,2,8);
insert into book (id,isbn,title,description,price,author_id,classification_id) values (book_sequence.nextval,'isbn0003','effective java','pla pla pla pla',30,3,2);
insert into book (id,isbn,title,description,price,author_id,classification_id) values (book_sequence.nextval,'isbn0004','java best','pla pla pla pla',35,4,2);
insert into book (id,isbn,title,description,price,author_id,classification_id) values (book_sequence.nextval,'isbn0005','enjoy your life','pla pla pla pla',55,5,9);
insert into book (id,isbn,title,description,price,author_id,classification_id) values (book_sequence.nextval,'isbn0006','Head first java','pla pla pla pla',25,6,2);
insert into book (id,isbn,title,description,price,author_id,classification_id) values (book_sequence.nextval,'isbn0007','java in action','pla pla pla pla',35,3,2);