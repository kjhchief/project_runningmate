SELECT *
FROM mate;

DROP TABLE mate;

--제약조건 조회
SELECT * 
FROM    ALL_CONSTRAINTS
WHERE    TABLE_NAME = 'notice';

----제약조건 비활성화/활성화
--ALTER TABLE mate
--  DISABLE CONSTRAINT mate_id_pk CASCADE
--  DISABLE CONSTRAINT mate_phonenum_uk;
--  
--ALTER TABLE mate
--  ENABLE CONSTRAINT mate_id_pk 
--  ENABLE CONSTRAINT mate_phonenum_uk;

-- 1. 회원 테이블
CREATE TABLE mate (
   email          VARCHAR2(100),
   name          VARCHAR2(50)                     NOT NULL,
   password      VARCHAR2(50)                     NOT NULL,
   gender          VARCHAR2(50)                      NOT NULL,
   birthdate      DATE         DEFAULT SYSDATE    NOT NULL,
   phone_number   VARCHAR2(50)                     NOT NULL,
   location     VARCHAR2(100)                     NOT NULL,
   mate_class   VARCHAR2(50)   DEFAULT '일반회원',
   kakaoacc_yn      VARCHAR2(50)   DEFAULT 'N'  NOT NULL,
   manner_temp    NUMBER(3,1)  DEFAULT 36.5,
   comment_count  NUMBER(7),                   
   join_count      NUMBER(7),                   
   host_count      NUMBER(7)                    
);
-- 제약조건 추가
ALTER TABLE mate 
  ADD ( 
        CONSTRAINT mate_email_pk         PRIMARY KEY (email),
        CONSTRAINT mate_gender_ck        CHECK       (gender IN('M', 'F')),
        CONSTRAINT mate_class_ck          CHECK       (mate_class IN ('일반회원', '관리자회원')),
        CONSTRAINT mate_kakaoacc_yn_ck   CHECK       (kakaoacc_yn IN('Y', 'N')),
        CONSTRAINT mate_phonenum_uk      UNIQUE      (phone_number)
        );
--예시 데이터 추가        
INSERT INTO mate (email, name, password, gender, birthdate, phone_number, location)
VALUES ('kjh3@naver.com', '김재훈3', '1111', 'M', '19940413', '010-2921-2422', '서울시 중랑구 면목로1길 1-1');

--2. 모임 테이블
SELECT *
FROM crew
ORDER BY crew_id DESC;

DROP TABLE crew;

CREATE TABLE crew (
    crew_id	VARCHAR2(50)		NOT NULL,
	title	VARCHAR2(300)		NOT NULL,
	crewdate	DATE	DEFAULT SYSDATE,
	mate_count	NUMBER(7)		NOT NULL,
	crew_location	VARCHAR2(200)		NOT NULL,
    crew_location_dt	VARCHAR2(200)		,
	crewlevel	VARCHAR2(50)		NOT NULL,
	course_leng	NUMBER(7)		NOT NULL,
	course_intro	VARCHAR2(300)		NULL,
	weather_intro	VARCHAR2(300)		NULL,
	etc_intro	VARCHAR2(300)		,
	description	VARCHAR2(4000)		NOT NULL,
	awaiter_count	NUMBER(7)			
);
-- sequence 생성
CREATE SEQUENCE crew_seq
START WITH 1
INCREMENT BY 1;
DROP SEQUENCE crew_seq;
-- 제약조건 추가
ALTER TABLE crew
  ADD (
    CONSTRAINT crew_id_pk   PRIMARY KEY(crew_id),
    CONSTRAINT crew_crewlevel_ck CHECK (crewlevel IN('빨리 걷기', '천천히 러닝', '건강한 러닝', '고강도 러닝'))
  );

-- 예시 데이터 추가
INSERT INTO crew
VALUES (crew_seq.NEXTVAL, '끝났으니까 맛있는거 먹자~~', 
'23/4/19 09:11', 5, 
'서울시 강서구 가리봉동 무슨아파트 큰 공원','우리집 앞', '고강도 러닝', 10, '좋은 코스입니다 아주 좋아 아주아주 좋아', '날씨는 맑음. 가끔 흐려요. 눈과 우박도 내려요.',
'기타 주절주절', '안녕하세요~ 우리 모임을 신청해주셔서감사합니다. 열심히 러닝하고 몸도 마음도 건강 튼튼. 비매너 사절. 러닝 외 다른 목적 사절. 적당한 운동은 건강에 아주 좋습니다', 3);

SELECT crew_id, title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE crewdate BETWEEN TO_DATE(TO_CHAR(SYSDATE, 'MM/DD'), 'MM/DD') AND TO_DATE(TO_CHAR(SYSDATE+6, 'MM/DD'), 'MM/DD');
AND crewlevel = '고강도 러닝';

SELECT crew_id, title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE TRUNC(crewdate) = TRUNC(SYSDATE + 3);

SELECT crew_id, title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE crewdate BETWEEN SYSDATE AND SYSDATE+6 AND crewlevel = '고강도 러닝';

SELECT crew_id, title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE crewdate BETWEEN TO_DATE('04_14', 'MM_DD') AND TO_DATE('04_17', 'MM_DD') AND crewlevel = '고강도 러닝';



SELECT crew_id, crew_location, crew_location_dt, crewdate
FROM crew
WHERE crew_location LIKE '%도봉%';


DELETE FROM crew
WHERE crew_id = '211';

commit;

-- 특정 모임 선택(select)
--SELECT title, crewdate, mate_count, crew_location, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
SELECT *
FROM crew
ORDER BY crew_id;


--WHERE crew_id = '61';
--SELECT title, crewdate, mate_count, crew_location, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
--FROM crew
--WHERE crew_id = 63;
SELECT crew_id, title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE crew_id = 139;

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
START WITH 1
INCREMENT BY 1;
-- 제약조건 추가
ALTER TABLE crewlist
  ADD ( CONSTRAINT crewlist_id_pk   PRIMARY KEY(crewlist_id),
   CONSTRAINT crewlist_email_fk FOREIGN KEY(email) REFERENCES mate(email),
   CONSTRAINT crewlist_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id),
   CONSTRAINT crewlist_types_ck CHECK (types IN('참여', '주최', '찜'))
   );
--예시 데이터 추가
--학원 컴터에서는 145로, 집에서는 50으로
INSERT INTO crewlist
VALUES (crewlist_seq.NEXTVAL, '참여', 'kjhhhh@naver.com', 50);
INSERT INTO crewlist
VALUES (crewlist_seq.NEXTVAL, '참여', 'kjh@naver.com', 50);
INSERT INTO crewlist
VALUES (crewlist_seq.NEXTVAL, '참여', 'kjh2@naver.com', 145);


--특정 회원들(목록)이 참석하는 특정 모임
SELECT m.email, m.name, c.crew_id, l.types
FROM crewlist l JOIN crew c ON l.crew_id = c.crew_id
                JOIN mate m ON l.email = m.email
WHERE c.crew_id = '145';
SELECT m.email, m.name, c.crew_id, l.types
FROM crewlist l JOIN crew c ON l.crew_id = c.crew_id
                JOIN mate m ON l.email = m.email
WHERE m.email = 'kjh@naver.com';
SELECT m.email, m.name, c.crew_id, l.types
FROM crewlist l JOIN crew c ON l.crew_id = c.crew_id
                JOIN mate m ON l.email = m.email
WHERE c.crew_id = '50';


--특정 회원이 참석하는 특정 모임
SELECT m.email, m.name, c.crew_id, l.types
FROM crewlist l JOIN crew c ON l.crew_id = c.crew_id
                JOIN mate m ON l.email = m.email
WHERE m.email = 'kjhhhh@naver.com';

SELECT *
FROM crewlist
WHERE crew_id = '145';
SELECT *
FROM crewlist
WHERE email = 'kjhhhh@naver.com';

DELETE FROM crewlist
WHERE email = 'kjhhhh@naver.com';

COMMIT;

--특정 모임에 참석중인 회원: 64번 모임에 참석중인 송진11호
SELECT m.name, m.email, c.title, c.crewdate, c.mate_count, c.crew_location, c.crewlevel, c.course_leng, c.course_intro, c.weather_intro, c.etc_intro, c.description, c.crew_id
	FROM crewlist l JOIN crew c ON l.crew_id = c.crew_id
	                JOIN mate m ON l.email = m.email
	WHERE c.crew_id = '64';
    
SELECT m.name, m.email, c.title, c.crew_id
	FROM crewlist l JOIN crew c ON l.crew_id = c.crew_id
	                JOIN mate m ON l.email = m.email
	WHERE c.crew_id = '64';



--3. 자주 묻는 질문 테이블
CREATE TABLE question (
   question_id   NUMBER(4),
   question_title   VARCHAR2(50),  
   question_content   VARCHAR2(4000),    
   category   VARCHAR2(50)
);

create sequence question_seq; 

--4. 공지사항 테이블
DROP TABLE notice;

CREATE TABLE notice (
   notice_id     NUMBER(4) NOT NULL,
   notice_title   VARCHAR2(300),  
   notice_content   VARCHAR2(4000),    
   notice_date   DATE DEFAULT SYSDATE,
   notice_hit   NUMBER(4) DEFAULT 0
);
-- 제약조건 추가
   
create sequence notice_seq;

INSERT INTO notice
VALUES (notice_seq.NEXTVAL, '공지사항', '내요오오오오오오오오옹',SYSDATE, 0);

commit;


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
-- 제약조건 추가
ALTER TABLE review
  ADD ( 
        CONSTRAINT review_id_pk   PRIMARY KEY(review_id),
        CONSTRAINT review_email_fk FOREIGN KEY(email) REFERENCES mate(email)
        );

--7. 공지사항 댓글 테이블
DROP TABLE reply;

 CREATE TABLE reply (
   reply_id         NUMBER(4)         PRIMARY KEY,
   email            VARCHAR2(100),    -- 이메일 칼럼 추가
   reply_content    VARCHAR2(4000),
   reply_date       DATE              DEFAULT SYSDATE,
   reply_notice_id  NUMBER(4),
   CONSTRAINT reply_mate_fk FOREIGN KEY (email) REFERENCES mate(email) ON DELETE CASCADE );  

create sequence reply_seq; 

--9. 사진
SELECT *
FROM photo;
DROP TABLE photo;

CREATE TABLE photo (
	photo_id	VARCHAR2(50)		NOT NULL,
	photo_name	VARCHAR2(4000)		NOT NULL,
	type	VARCHAR2(50)		,
	crew_id	VARCHAR2(50)		NOT NULL
);
--제약조건 설정
ALTER TABLE photo
  ADD ( CONSTRAINT photo_id_pk   PRIMARY KEY(photo_id),
   CONSTRAINT photo_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id));
-- sequence 생성
CREATE SEQUENCE photo_seq
START WITH 1
INCREMENT BY 1;

--사진 insert
INSERT INTO photo (photo_id, photo_name, crew_id)
VALUES (photo_seq.NEXTVAL, 'pic02', '139');
   
--사진정보 포함 추가
INSERT ALL
    INTO crew VALUES(crew_id, title, crewdate, mate_count, crew_location, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description, awaiter_count)
    INTO photo VALUES(name, type);

-- 크루+사진 테이블 셀렉트(화면에 쏘기)
SELECT c.crew_id, c.title, c.crewdate, c.mate_count, c.crew_location, c.crew_location_dt, c.crewlevel, c.course_leng, c.course_intro, c.weather_intro, c.etc_intro, c.description, c.awaiter_count, p.photo_id, p.name
		FROM crew c
		JOIN photo p ON c.crew_id = p.crew_id
		WHERE c.crew_id = 126;

SELECT title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
		FROM crew
		WHERE crew_id = #{value};

select photo_id, photo_name, crew_id
from photo
WHERE crew_id='135';


select * 
from crew
ORDER BY crew_id DESC;

SELECT crew_id
FROM crew
WHERE title = '테스트 모임...';

select photo_id, photo_name, crew_id
from photo
ORDER BY crew_id DESC;
WHERE crew_id =  '143';

commit;

DELETE FROM photo 
WHERE photo_id = 108;

--04월 17일 월요일

SELECT crew_id, title, TO_CHAR(crewdate, 'HH24:SS'), mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE TRUNC(crewdate) = TRUNC(SYSDATE + 0);
SELECT TO_CHAR(crewdate, 'MM"월" DD"일" DAY')
FROM crew
WHERE TRUNC(crewdate) = TRUNC(SYSDATE + 0);




