SELECT *
FROM members;

DROP TABLE members;

--제약조건 조회
SELECT * 
FROM    ALL_CONSTRAINTS
WHERE    TABLE_NAME = 'notice';

----제약조건 비활성화/활성화
--ALTER TABLE members
--  DISABLE CONSTRAINT members_id_pk CASCADE
--  DISABLE CONSTRAINT members_phonenum_uk;
--  
--ALTER TABLE members
--  ENABLE CONSTRAINT members_id_pk 
--  ENABLE CONSTRAINT members_phonenum_uk;

-- 1. 회원 테이블
CREATE TABLE members (
   email          VARCHAR2(100),
   name          VARCHAR2(50)                     NOT NULL,
   password      VARCHAR2(50)                     NOT NULL,
   gender          VARCHAR2(50)                      NOT NULL,
   birthdate      DATE         DEFAULT SYSDATE    NOT NULL,
   phone_number   VARCHAR2(50)                     NOT NULL,
   location     VARCHAR2(100)                     NOT NULL,
   member_class   VARCHAR2(50)   DEFAULT '일반회원',
   kakaoacc_yn      VARCHAR2(50)   DEFAULT 'N'  NOT NULL,
   manner_temp    NUMBER(3,1)  DEFAULT 36.5,
   comment_count  NUMBER(7),                   
   join_count      NUMBER(7),                   
   host_count      NUMBER(7)                    
);
-- 제약조건 추가
ALTER TABLE members 
  ADD ( 
        CONSTRAINT members_email_pk         PRIMARY KEY (email),
        CONSTRAINT members_gender_ck        CHECK       (gender IN('M', 'F')),
        CONSTRAINT member_class_ck          CHECK       (member_class IN ('일반회원', '관리자회원')),
        CONSTRAINT members_kakaoacc_yn_ck   CHECK       (kakaoacc_yn IN('Y', 'N')),
        CONSTRAINT members_phonenum_uk      UNIQUE      (phone_number)
        );
--예시 데이터 추가        
INSERT INTO members (email, name, password, gender, birthdate, phone_number, location)
VALUES ('zi@nave121r11.com', '송진11호', '1111', 'M', '19940413', '010-9111-2422', '서울 노원구 띵띵동');


--2. 모임 테이블
SELECT *
FROM crew;

DROP TABLE crew;

CREATE TABLE crew (
    crew_id	VARCHAR2(50)		NOT NULL,
	title	VARCHAR2(50)		NOT NULL,
	crewdate	DATE	DEFAULT SYSDATE,
	member_count	NUMBER(7)		NOT NULL,
	crew_location	VARCHAR2(200)		NOT NULL,
	crewlevel	VARCHAR2(50)		NOT NULL,
	course_leng	NUMBER(7)		NOT NULL,
	course_intro	VARCHAR2(300)		NULL,
	weather_intro	VARCHAR2(300)		NULL,
	etc_intro	VARCHAR2(300)		NULL,
	description	VARCHAR2(4000)		NOT NULL,
	awaiter_count	NUMBER(7)			
);
-- sequence 생성
CREATE SEQUENCE crew_seq
INCREMENT BY 1;
DROP SEQUENCE crew_seq;
-- 제약조건 추가
ALTER TABLE crew
  ADD CONSTRAINT crew_id_pk   PRIMARY KEY(crew_id);
-- 예시 데이터 추가
INSERT INTO crew
VALUES (crew_seq.NEXTVAL, '두번째', 
TO_DATE('2020-3-4 오후 7:7', 'YYYY-MM-DD PM HH:MI', 'NLS_DATE_LANGUAGE = KOREAN'), 5, 
'서울시 도봉구 도봉동 무슨아파트 큰 공원', '건강한 러닝', 10, '좋은 코스입니다 아주 좋아 아주아주 좋아', '날씨는 맑음. 가끔 흐려요. 눈과 우박도 내려요.',
'기타 주절주절', '안녕하세요~ 우리 모임을 신청해주셔서감사합니다. 열심히 러닝하고 몸도 마음도 건강 튼튼. 비매너 사절. 러닝 외 다른 목적 사절. 적당한 운동은 건강에 아주 좋습니다', 3);

--8. 모임리스트
SELECT *
FROM crewlist;
DROP TABLE crewlist;

CREATE TABLE crewlist (
	crewlist_id	VARCHAR2(50)		NOT NULL,
	types	VARCHAR2(50)		NOT NULL,
	email	VARCHAR2(100)		NOT NULL,
	crew_id	VARCHAR2(50)		NOT NULL
);
-- sequence 생성
CREATE SEQUENCE crewlist_seq
INCREMENT BY 1;
-- 제약조건 추가
ALTER TABLE crewlist
  ADD ( CONSTRAINT crewlist_id_pk   PRIMARY KEY(crewlist_id),
   CONSTRAINT crewlist_email_fk FOREIGN KEY(email) REFERENCES members(email),
   CONSTRAINT crewlist_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id),
   CONSTRAINT crewlist_types_ck CHECK (types IN('참여', '주최', '찜'))
   
   );
--예시 데이터 추가
INSERT INTO crewlist
VALUES (crewlist_seq.NEXTVAL, '참여', 'zi@nave121r11.com', 22);

--특정 회원이 참석하는 특정 모임
SELECT m.email, m.name, c.crew_id, l.types
FROM crewlist l JOIN crew c ON l.crew_id = c.crew_id
                JOIN members m ON l.email = m.email
WHERE c.crew_id = '22';


--3. 자주 묻는 질문 테이블
DROP TABLE faq;

CREATE TABLE faq (
	faq_id	VARCHAR2(50)		NOT NULL,
	category	VARCHAR2(50)		NULL,
	faq_title	VARCHAR2(50)		NOT NULL,
	faq_content	VARCHAR2(300)		NOT NULL,
	faq_date	DATE		NOT NULL,
	email	VARCHAR2(100)		NOT NULL
);
-- 제약조건 추가
ALTER TABLE faq
  ADD ( CONSTRAINT faq_id_pk   PRIMARY KEY(faq_id),
   CONSTRAINT email_fk FOREIGN KEY(email) REFERENCES members(email));

--4. 공지사항 테이블
DROP TABLE notice;

CREATE TABLE notice (
	notice_id	VARCHAR2(50)		NOT NULL,
	title	VARCHAR2(50)		NOT NULL,
	content	VARCHAR2(4000)		NOT NULL,
	notice_date	DATE		NOT NULL,
	Field	NUMBER(7)		NULL,
	email	VARCHAR2(100)		NOT NULL
);
-- 제약조건 추가
ALTER TABLE notice
  ADD ( CONSTRAINT notice_id_pk   PRIMARY KEY(notice_id),
   CONSTRAINT notice_email_fk FOREIGN KEY(email) REFERENCES members(email));

--5. 장소 테이블
DROP TABLE location;

CREATE TABLE location (
	location_id	VARCHAR2(50)		NOT NULL,
	adress	VARCHAR2(50)		NOT NULL,
	longitude	VARCHAR2(50)		NOT NULL,
	latitude	VARCHAR2(50)		NOT NULL,
	crew_id	VARCHAR2(50)		NOT NULL
);
-- 제약조건 추가
ALTER TABLE location
  ADD ( CONSTRAINT location_id_pk   PRIMARY KEY(location_id),
   CONSTRAINT location_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id));

--6. 개인후기(코멘트) 테이블
DROP TABLE review;

CREATE TABLE review (
	review_id	VARCHAR2(50)		NOT NULL,
	author	VARCHAR2(50)		NOT NULL,
	content	VARCHAR2(4000)		NOT NULL,
	writedate	DATE		NOT NULL,
	email	VARCHAR2(100)		NOT NULL
);

--7. 공지사항 댓글 테이블
DROP TABLE comment;

CREATE TABLE comment (
	comment_id	VARCHAR2(50)		NOT NULL,
	content	VARCHAR2(4000)		NOT NULL,
	notice_id	VARCHAR2(50)		NOT NULL,
	email	VARCHAR2(100)		NOT NULL
);
-- 제약조건 추가
ALTER TABLE comment
  ADD ( CONSTRAINT comment_id_pk   PRIMARY KEY(comment_id),
   CONSTRAINT comment_email_fk FOREIGN KEY(email) REFERENCES members(email),
   CONSTRAINT notice_id_fk FOREIGN KEY(notice_id) REFERENCES notice(notice_id));

--9. 사진
DROP TABLE photo;

CREATE TABLE photo (
	photo_id	VARCHAR2(50)		NOT NULL,
	name	VARCHAR2(50)		NOT NULL,
	type	VARCHAR2(50)		NOT NULL,
	crew_id	VARCHAR2(50)		NOT NULL
);
ALTER TABLE photo
  ADD ( CONSTRAINT photo_id_pk   PRIMARY KEY(photo_id),
   CONSTRAINT photo_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id));
