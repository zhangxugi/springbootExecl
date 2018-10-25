create table Teachers(
tnos int auto_increment primary key,
tnames varchar(50),
typeses varchar(50),
tpasswords varchar(50)
)

INSERT into Teachers(tnames,typeses,tpasswords) values('zx','s','132')
INSERT INTO Teachers(tnos,tnames,typeses,tpasswords) VALUES(2,'ss','dd','456')
select * from Teachers