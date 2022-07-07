use BookStoreDB
create database BookStoreDB
create table Account (
	username varchar(50) primary key,
	password varchar(100) not null,
	fullname nvarchar(100) not null,
	role varchar(10) not null,
	address nvarchar(100),
	phone_no varchar(12),
	sex varchar(10),
)
go
create table Product (
	product_id  varchar(10) primary key,
	title nvarchar(50) not null,
	author nvarchar(50),
	public_date date,
	price money,
	available_quantity int, 
)
go
create table Ordered(
	order_id int identity(1,1) primary key,
	order_date date,
	total money,
	customer_name nvarchar(50),
	customer_address nvarchar(100),
	customer_phone varchar(12),
	order_status varchar(15) default 'processing'
)
go
create table Order_Detail (
	order_detail_id int identity(1,1) primary key,
	quantity int,
	price money,
	product_id  varchar(10),
	order_id int, 
)
go
--order_detail FK setting
alter table Order_Detail
add constraint FK_Order_Detail_Ordered 
foreign key (order_id) references Ordered(order_id) 
on delete cascade 
on update cascade
go
alter table Order_Detail
add constraint FK_Order_Detail_Product 
foreign key (product_id) references Product(product_id)
on delete cascade
on update cascade
go
--product_default setting
ALTER TABLE Product
ADD CONSTRAINT default_price
DEFAULT 0 FOR price;
go
ALTER TABLE Product
ADD CONSTRAINT default_available_quantity
DEFAULT 0 FOR available_quantity;
go
--account default setting
ALTER TABLE Account
ADD CONSTRAINT default_sex
DEFAULT 'Others' FOR sex;
go
ALTER TABLE Account
ADD CONSTRAINT default_role
DEFAULT 'User' FOR role;



select * from Account
select * from Product
select * from Order_Detail
select * from Ordered

select * from Product
insert into Product(product_id, title, author, public_date, price, available_quantity) 
values('P01','Advanced Java Web For Beginners','KhanhKT', '2022-04-12',100,100)

insert into Product(product_id, title, author, public_date, price, available_quantity) 
values('P02','SpringBoot MVC2','Huynq', '2020-06-08',70,200)

insert into Product(product_id, title, author, public_date, price, available_quantity) 
values('P03','Rich Dad Poor Dad','Robert Kiyosaki', '1997-02-27',150,50)

insert into Product(product_id, title, author, public_date, price, available_quantity) 
values('P04','Code Dao Ki Su','Pham Huy Hoang', '2021-07-15',88,120)

insert into Product(product_id, title, author, public_date, price, available_quantity) 
values('P05','The Pragmatic Programmer','Andy Hunt', '2012-07-29',123,200)

insert into Product(product_id, title, author, public_date, price, available_quantity) 
values('P06','Thinking In Java','Bruce Eckel', '2002-08-12',123,12)

Select product_id, title, author, public_date, price, available_quantity from Product

INSERT INTO Ordered (order_date, total, customer_name, customer_address, customer_phone)
                        VALUES ('2022-11-12',345,'ha','gh','123213')
						

