-- user: menudb
-- pw: 1234

drop table tbl_dinner cascade constraints;
drop table tbl_food cascade constraints;
drop table tbl_menu;
drop table tbl_member cascade constraints;
drop table tbl_like;
drop table tbl_review cascade constraints;
drop table tbl_book;
drop table tbl_recommend;
drop table tbl_photo;

drop sequence seq_dinner;
drop sequence seq_member;
drop sequence seq_review;
drop sequence seq_book;
drop sequence seq_food;
drop sequence seq_photo;

create table
  tbl_dinner (
    dinner_no varchar2 (11) primary key,
    dinner_name varchar2 (100) not null,
    dinner_addr varchar2 (100) not null,
    dinner_open varchar2 (20) not null,
    dinner_close varchar2 (20) not null,
    dinner_phone char(13) not null,
    dinner_email varchar2 (30) not null,
    dinner_parking char(1) not null check (dinner_parking in ('y', 'n')),
    dinner_max_person number not null, -- 최대 수용 인원
    busi_no char(12) not null, -- 사업자 등록 번호
    dinner_id varchar2 (20) not null,
    dinner_pw varchar2 (60) not null,
    dinner_confirm char(1) default 'n' check (dinner_confirm in ('y', 'n')) -- 승인 여부
  );

-- 'd' || to_char(sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0')
create sequence seq_dinner maxvalue 9999 cycle;

-- 실제 식당 데이터 삽입(삽입 데이터 수정)
insert into tbl_dinner values ( 'd' || to_char (sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), '온리밋', '서울 송파구 양재대로71길 19-13', '10:00', '19:00', '010-1111-1111', 'blackeagle10@icloud.com', 'y', '30', '111111111111', 'dinner11234', 'dinner11234@', 'y');
insert into tbl_dinner values ( 'd' || to_char (sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), '포유티', '서울 송파구 위례성대로18길 28-13', '11:00', '21:30', '010-2222-2222', 'blackeagle10@icloud.com', 'y', '30', '222222222222', 'dinner21234', 'dinner21234@', 'y');
insert into tbl_dinner values ( 'd' || to_char (sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), '오븐스프링', '서울 송파구 가락로 271', '10:00', '21:00', '010-3333-3333', 'blackeagle10@icloud.com', 'y', '30', '333333333333', 'dinner31234', 'dinner31234@', 'y');
insert into tbl_dinner values ( 'd' || to_char (sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), '바운더리프리', '서울 송파구 백제고분로48길 4-27', '10:00', '20:00', '010-4444-4444', 'blackeagle10@icloud.com', 'y', '30', '444444444444', 'dinner41234', 'dinner41234@', 'y');
insert into tbl_dinner values ( 'd' || to_char (sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), '청년감자탕', '서울 송파구 오금로19길 5', '10:10', '21:50', '010-5555-5555', 'blackeagle10@icloud.com', 'y', '30', '555555555555', 'dinner51234', 'dinner51234@', 'y');
insert into tbl_dinner values ( 'd' || to_char (sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), '교동국수', '서울 송파구 백제고분로27길 24', '11:30', '20:00', '010-6666-6666', 'blackeagle10@icloud.com', 'y', '30', '666666666666', 'dinner61234', 'dinner61234@', 'n');
insert into tbl_dinner values ( 'd' || to_char (sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), '서두산딤섬', '서울 송파구 올림픽로32길 18-23', '11:30', '22:00', '010-7777-7777', 'blackeagle10@icloud.com', 'y', '30', '777777777777', 'dinner71234', 'dinner71234@', 'n');
insert into tbl_dinner values ( 'd' || to_char (sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), '맛쟁이떡볶이', '서울 송파구 석촌호수로 134 1층', '11:00', '20:30', '010-8888-8888', 'blackeagle10@icloud.com', 'y', '30', '888888888888', 'dinner81234', 'dinner81234@', 'n');
insert into tbl_dinner values ( 'd' || to_char (sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), '송돈', '서울 송파구 가락로 280', '16:00', '22:30', '010-9999-9999', 'blackeagle10@icloud.com', 'y', '30', '999999999999', 'dinner91234', 'dinner91234@', 'n');

create table
  tbl_food (
    food_no varchar2 (11) primary key,
    food_name varchar2 (30) not null,
    food_nation varchar2 (30) not null,
    food_cat varchar2 (30) not null
  );

-- 'f' || to_char(sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0')
create sequence seq_food maxvalue 9999 cycle;

-- tbl_food 데이터
insert into tbl_food values ( 'f' || to_char (sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0'), '음식1', '한식', '육류');
insert into tbl_food values ( 'f' || to_char (sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0'), '음식2', '양식', '육류');
insert into tbl_food values ( 'f' || to_char (sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0'), '음식3', '일식', '해산물');
insert into tbl_food values ( 'f' || to_char (sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0'), '음식4', '일식', '육류');
insert into tbl_food values ( 'f' || to_char (sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0'), '음식5', '양식', '디저트');
insert into tbl_food values ( 'f' || to_char (sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0'), '음식6', '일식', '디저트');
insert into tbl_food values ( 'f' || to_char (sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0'), '음식7', '한식', '디저트');
insert into tbl_food values ( 'f' || to_char (sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0'), '음식8', '한식', '해산물');
insert into tbl_food values ( 'f' || to_char (sysdate, 'yymmdd') || lpad (seq_food.nextval, 4, '0'), '음식9', '양식', '해산물');

create table
  tbl_menu (
    dinner_no varchar2 (11) not null references tbl_dinner (dinner_no) on delete cascade,
    food_no varchar2 (11) not null references tbl_food (food_no) on delete cascade,
    price number not null,
    primary key (dinner_no, food_no) -- 프라이머리 키 두개
  );

-- tbl_menu 데이터
insert into tbl_menu values ((select dinner_no from tbl_dinner where dinner_no like '%1'), (select food_no from tbl_food where food_no like '%5'), 18000);
insert into tbl_menu values ((select dinner_no from tbl_dinner where dinner_no like '%2'), (select food_no from tbl_food where food_no like '%8'), 12000);
insert into tbl_menu values ((select dinner_no from tbl_dinner where dinner_no like '%3'), (select food_no from tbl_food where food_no like '%2'), 20000);
insert into tbl_menu values ((select dinner_no from tbl_dinner where dinner_no like '%4'), (select food_no from tbl_food where food_no like '%5'), 15000);
insert into tbl_menu values ((select dinner_no from tbl_dinner where dinner_no like '%5'), (select food_no from tbl_food where food_no like '%1'), 23000);

create table
  tbl_member (
    member_no varchar2 (11) primary key,
    member_id varchar2 (30) not null,
    member_pw varchar2 (60) not null,
    member_name varchar2 (30) not null,
    member_nick varchar2 (30) not null,
    member_phone char(13) not null,
    member_addr varchar2 (100) not null,
    member_gender char(1) not null check (member_gender in ('m', 'f')),
    member_email varchar2 (30) not null,
    enroll_date date default sysdate not null,
    adult_confirm char(1) default 'n' check (adult_confirm in ('y', 'n')),
    member_level number default 3 not null check (member_level in (1, 2, 3))
  );

-- 'm' || to_char(sysdate, 'yymmdd') || lpad (seq_member.nextval, 4, '0')
create sequence seq_member maxvalue 9999 cycle;

-- Inserting members
insert into tbl_member values ( 'm' || to_char (sysdate, 'yymmdd') || lpad (seq_member.nextval, 4, '0'), 'admin999', 'admin999@', '관리자 이름', '관리자 별명', '010-8645-5542', '경기도 용인시 기흥구', 'm', 'blackeagle10@icloud.com', sysdate, 'y', '1');
insert into tbl_member values ( 'm' || to_char (sysdate, 'yymmdd') || lpad (seq_member.nextval, 4, '0'), 'user11234', 'user11234@', '유저하나 이름', '유저하나 별명', '010-1111-1111', '유저하나 주소', 'f', 'blackeagle10@icloud.com', sysdate, 'n', '2');
insert into tbl_member values ( 'm' || to_char (sysdate, 'yymmdd') || lpad (seq_member.nextval, 4, '0'), 'user21234', 'user21234@', '유저둘 이름', '유저둘 별명', '010-2222-2222', '유저둘 주소', 'm', 'blackeagle10@icloud.com', sysdate, 'y', '3');

create table
  tbl_like (
    dinner_no varchar2 (11) not null references tbl_dinner (dinner_no) on delete cascade,
    member_no varchar2 (11) not null references tbl_member (member_no) on delete cascade,
    primary key (dinner_no, member_no) -- 프라이머리 키 두개
  );

-- 즐겨찾기
insert into tbl_like values ((select dinner_no from tbl_dinner where dinner_no like '%1'), (select member_no from tbl_member where member_no like '%2'));
insert into tbl_like values ((select dinner_no from tbl_dinner where dinner_no like '%2'), (select member_no from tbl_member where member_no like '%2'));
insert into tbl_like values ((select dinner_no from tbl_dinner where dinner_no like '%3'), (select member_no from tbl_member where member_no like '%3'));

create table
  tbl_review (
    review_no varchar2 (11) primary key,
    dinner_no varchar2 (11) references tbl_dinner (dinner_no) on delete cascade,
    member_no varchar2 (11) references tbl_member (member_no) on delete cascade,
    review_con varchar2 (2000),
    review_img blob,
    review_date date
  );

-- 'r' || to_char(sysdate, 'yymmdd') || lpad (seq_review.nextval, 4, '0')
create sequence seq_review maxvalue 9999 cycle;

create table
  tbl_book (
    book_no varchar2 (11) primary key,
    dinner_no varchar2 (11) references tbl_dinner (dinner_no) on delete cascade,
    member_no varchar2 (11) references tbl_member (member_no) on delete cascade,
    book_date date not null,
    book_time varchar2 (20) not null,
    book_cnt number not null
  );

-- 'b' || to_char(sysdate, 'yymmdd') || lpad (seq_book.nextval, 4, '0')
create sequence seq_book maxvalue 9999 cycle;

-- Insert into tbl_book
-- 2123123
insert into tbl_book values ( 'b' || to_char (sysdate, 'yymmdd') || lpad (seq_book.nextval, 4, '0'), (select dinner_no from tbl_dinner where dinner_no like '%1'), (select member_no from tbl_member where member_no like '%2'), to_date ('25/01/24', 'yy/mm/dd'), '1230', 4);
insert into tbl_book values ( 'b' || to_char (sysdate, 'yymmdd') || lpad (seq_book.nextval, 4, '0'), (select dinner_no from tbl_dinner where dinner_no like '%1'), (select member_no from tbl_member where member_no like '%1'), to_date ('25/01/25', 'yy/mm/dd'), '1230', 4);
insert into tbl_book values ( 'b' || to_char (sysdate, 'yymmdd') || lpad (seq_book.nextval, 4, '0'), (select dinner_no from tbl_dinner where dinner_no like '%1'), (select member_no from tbl_member where member_no like '%2'), to_date ('25/01/25', 'yy/mm/dd'), '1245', 4);
insert into tbl_book values ( 'b' || to_char (sysdate, 'yymmdd') || lpad (seq_book.nextval, 4, '0'), (select dinner_no from tbl_dinner where dinner_no like '%1'), (select member_no from tbl_member where member_no like '%3'), to_date ('25/01/25', 'yy/mm/dd'), '1800', 4);
insert into tbl_book values ( 'b' || to_char (sysdate, 'yymmdd') || lpad (seq_book.nextval, 4, '0'), (select dinner_no from tbl_dinner where dinner_no like '%1'), (select member_no from tbl_member where member_no like '%1'), to_date ('25/01/25', 'yy/mm/dd'), '1830', 4);
insert into tbl_book values ( 'b' || to_char (sysdate, 'yymmdd') || lpad (seq_book.nextval, 4, '0'), (select dinner_no from tbl_dinner where dinner_no like '%1'), (select member_no from tbl_member where member_no like '%2'), to_date ('25/01/25', 'yy/mm/dd'), '1900', 4);
insert into tbl_book values ( 'b' || to_char (sysdate, 'yymmdd') || lpad (seq_book.nextval, 4, '0'), (select dinner_no from tbl_dinner where dinner_no like '%1'), (select member_no from tbl_member where member_no like '%3'), to_date ('25/01/26', 'yy/mm/dd'), '1800', 4);

create table
  tbl_recommend (
    review_no varchar2 (11) references tbl_review (review_no) on delete cascade,
    member_no varchar2 (11) references tbl_member (member_no) on delete cascade,
    report char(1) default 'n' not null check (report in ('n', 'y')),
    primary key (review_no, member_no)
  );
  
  create table
    tbl_photo (
        photo_no varchar2 (11) primary key,
        dinner_no varchar2 (11) references tbl_dinner (dinner_no) on delete cascade,
        review_no varchar2 (11) references tbl_review (review_no) on delete cascade,
        photo_name varchar2 (300), -- 사용자가 업로드한 파일 명칭
        photo_path varchar2 (300) -- 서버에 중복된 파일명이 존재할때 각 파일을 구분하기 위한 관리용 파일명
    );
-- 'p' || to_char(sysdate, 'yymmdd') || lpad (seq_photo.nextval, 4, '0')
create sequence seq_photo maxvalue 9999 cycle;

insert into tbl_photo values ('p2411221001', (select dinner_no from tbl_dinner where dinner_no like '%1'), '', '온리밋.png','20241122091918414_05740.png'); 
insert into tbl_photo values ('p2411221002', (select dinner_no from tbl_dinner where dinner_no like '%2'), '', '포유티.png','20241122092018188_03447.png'); 
insert into tbl_photo values ('p2411221003', (select dinner_no from tbl_dinner where dinner_no like '%3'), '', '오븐스프링.png','20241122092102253_06170.png'); 
insert into tbl_photo values ('p2411221004', (select dinner_no from tbl_dinner where dinner_no like '%4'), '', '바운더리프리.png','20241122092136220_00680.png'); 
insert into tbl_photo values ('p2411221005', (select dinner_no from tbl_dinner where dinner_no like '%5'), '', '청년감자탕.png','20241122092155060_04843.png'); 

commit;
