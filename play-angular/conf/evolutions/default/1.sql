# --- !Ups
create table "intern"("name" varchar(200),"email" varchar(200),"mobile" int,"address" varchar(200),"emergency_contact" int,"id" serial Primary Key );

insert into "intern" values('akshay','aks@gmail.com',3256,'dabhri',354536,1);

insert into "intern" values('saksham','saksh@gmail.com',3256,'dabhri',354536,2);


# --- !Downs
DROP TABLE "intern";

