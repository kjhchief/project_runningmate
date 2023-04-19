SELECT *
FROM mate;

DROP TABLE mate;

--�������� ��ȸ
SELECT * 
FROM    ALL_CONSTRAINTS
WHERE    TABLE_NAME = 'notice';

----�������� ��Ȱ��ȭ/Ȱ��ȭ
--ALTER TABLE mate
--  DISABLE CONSTRAINT mate_id_pk CASCADE
--  DISABLE CONSTRAINT mate_phonenum_uk;
--  
--ALTER TABLE mate
--  ENABLE CONSTRAINT mate_id_pk 
--  ENABLE CONSTRAINT mate_phonenum_uk;

-- 1. ȸ�� ���̺�
CREATE TABLE mate (
   email          VARCHAR2(100),
   name          VARCHAR2(50)                     NOT NULL,
   password      VARCHAR2(50)                     NOT NULL,
   gender          VARCHAR2(50)                      NOT NULL,
   birthdate      DATE         DEFAULT SYSDATE    NOT NULL,
   phone_number   VARCHAR2(50)                     NOT NULL,
   location     VARCHAR2(100)                     NOT NULL,
   mate_class   VARCHAR2(50)   DEFAULT '�Ϲ�ȸ��',
   kakaoacc_yn      VARCHAR2(50)   DEFAULT 'N'  NOT NULL,
   manner_temp    NUMBER(3,1)  DEFAULT 36.5,
   comment_count  NUMBER(7),                   
   join_count      NUMBER(7),                   
   host_count      NUMBER(7)                    
);
-- �������� �߰�
ALTER TABLE mate 
  ADD ( 
        CONSTRAINT mate_email_pk         PRIMARY KEY (email),
        CONSTRAINT mate_gender_ck        CHECK       (gender IN('M', 'F')),
        CONSTRAINT mate_class_ck          CHECK       (mate_class IN ('�Ϲ�ȸ��', '������ȸ��')),
        CONSTRAINT mate_kakaoacc_yn_ck   CHECK       (kakaoacc_yn IN('Y', 'N')),
        CONSTRAINT mate_phonenum_uk      UNIQUE      (phone_number)
        );
--���� ������ �߰�        
INSERT INTO mate (email, name, password, gender, birthdate, phone_number, location)
VALUES ('kjh3@naver.com', '������3', '1111', 'M', '19940413', '010-2921-2422', '����� �߶��� ����1�� 1-1');

--2. ���� ���̺�
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
-- sequence ����
CREATE SEQUENCE crew_seq
START WITH 1
INCREMENT BY 1;
DROP SEQUENCE crew_seq;
-- �������� �߰�
ALTER TABLE crew
  ADD (
    CONSTRAINT crew_id_pk   PRIMARY KEY(crew_id),
    CONSTRAINT crew_crewlevel_ck CHECK (crewlevel IN('���� �ȱ�', 'õõ�� ����', '�ǰ��� ����', '���� ����'))
  );

-- ���� ������ �߰�
INSERT INTO crew
VALUES (crew_seq.NEXTVAL, '�������ϱ� ���ִ°� ����~~', 
'23/4/19 09:11', 5, 
'����� ������ �������� ��������Ʈ ū ����','�츮�� ��', '���� ����', 10, '���� �ڽ��Դϴ� ���� ���� ���־��� ����', '������ ����. ���� �����. ���� ��ڵ� ������.',
'��Ÿ ��������', '�ȳ��ϼ���~ �츮 ������ ��û���ּż������մϴ�. ������ �����ϰ� ���� ������ �ǰ� ưư. ��ų� ����. ���� �� �ٸ� ���� ����. ������ ��� �ǰ��� ���� �����ϴ�', 3);

SELECT crew_id, title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE crewdate BETWEEN TO_DATE(TO_CHAR(SYSDATE, 'MM/DD'), 'MM/DD') AND TO_DATE(TO_CHAR(SYSDATE+6, 'MM/DD'), 'MM/DD');
AND crewlevel = '���� ����';

SELECT crew_id, title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE TRUNC(crewdate) = TRUNC(SYSDATE + 3);

SELECT crew_id, title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE crewdate BETWEEN SYSDATE AND SYSDATE+6 AND crewlevel = '���� ����';

SELECT crew_id, title, crewdate, mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE crewdate BETWEEN TO_DATE('04_14', 'MM_DD') AND TO_DATE('04_17', 'MM_DD') AND crewlevel = '���� ����';



SELECT crew_id, crew_location, crew_location_dt, crewdate
FROM crew
WHERE crew_location LIKE '%����%';


DELETE FROM crew
WHERE crew_id = '211';

commit;

-- Ư�� ���� ����(select)
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

--8. ���Ӹ���Ʈ
SELECT *
FROM crewlist;

DROP TABLE crewlist;

CREATE TABLE crewlist (
	crewlist_id	VARCHAR2(50)		NOT NULL,
	types	VARCHAR2(50)		NOT NULL,
	email	VARCHAR2(100)		NOT NULL,
	crew_id	VARCHAR2(50)		NOT NULL
);
-- sequence ����
CREATE SEQUENCE crewlist_seq
START WITH 1
INCREMENT BY 1;
-- �������� �߰�
ALTER TABLE crewlist
  ADD ( CONSTRAINT crewlist_id_pk   PRIMARY KEY(crewlist_id),
   CONSTRAINT crewlist_email_fk FOREIGN KEY(email) REFERENCES mate(email),
   CONSTRAINT crewlist_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id),
   CONSTRAINT crewlist_types_ck CHECK (types IN('����', '����', '��'))
   );
--���� ������ �߰�
--�п� ���Ϳ����� 145��, �������� 50����
INSERT INTO crewlist
VALUES (crewlist_seq.NEXTVAL, '����', 'kjhhhh@naver.com', 50);
INSERT INTO crewlist
VALUES (crewlist_seq.NEXTVAL, '����', 'kjh@naver.com', 50);
INSERT INTO crewlist
VALUES (crewlist_seq.NEXTVAL, '����', 'kjh2@naver.com', 145);


--Ư�� ȸ����(���)�� �����ϴ� Ư�� ����
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


--Ư�� ȸ���� �����ϴ� Ư�� ����
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

--Ư�� ���ӿ� �������� ȸ��: 64�� ���ӿ� �������� ����11ȣ
SELECT m.name, m.email, c.title, c.crewdate, c.mate_count, c.crew_location, c.crewlevel, c.course_leng, c.course_intro, c.weather_intro, c.etc_intro, c.description, c.crew_id
	FROM crewlist l JOIN crew c ON l.crew_id = c.crew_id
	                JOIN mate m ON l.email = m.email
	WHERE c.crew_id = '64';
    
SELECT m.name, m.email, c.title, c.crew_id
	FROM crewlist l JOIN crew c ON l.crew_id = c.crew_id
	                JOIN mate m ON l.email = m.email
	WHERE c.crew_id = '64';



--3. ���� ���� ���� ���̺�
CREATE TABLE question (
   question_id   NUMBER(4),
   question_title   VARCHAR2(50),  
   question_content   VARCHAR2(4000),    
   category   VARCHAR2(50)
);

create sequence question_seq; 

--4. �������� ���̺�
DROP TABLE notice;

CREATE TABLE notice (
   notice_id     NUMBER(4) NOT NULL,
   notice_title   VARCHAR2(300),  
   notice_content   VARCHAR2(4000),    
   notice_date   DATE DEFAULT SYSDATE,
   notice_hit   NUMBER(4) DEFAULT 0
);
-- �������� �߰�
   
create sequence notice_seq;

INSERT INTO notice
VALUES (notice_seq.NEXTVAL, '��������', '���������������������',SYSDATE, 0);

commit;


--5. ��� ���̺�
DROP TABLE location;

CREATE TABLE location (
	location_id	VARCHAR2(50)		NOT NULL,
	adress	VARCHAR2(50)		NOT NULL,
	longitude	VARCHAR2(50)		NOT NULL,
	latitude	VARCHAR2(50)		NOT NULL,
	crew_id	VARCHAR2(50)		NOT NULL
);
-- �������� �߰�
ALTER TABLE location
  ADD ( CONSTRAINT location_id_pk   PRIMARY KEY(location_id),
   CONSTRAINT location_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id));

--6. �����ı�(�ڸ�Ʈ) ���̺�
DROP TABLE review;

CREATE TABLE review (
	review_id	VARCHAR2(50)		NOT NULL,
	author	VARCHAR2(50)		NOT NULL,
	content	VARCHAR2(4000)		NOT NULL,
	writedate	DATE		NOT NULL,
	email	VARCHAR2(100)		NOT NULL
);
-- �������� �߰�
ALTER TABLE review
  ADD ( 
        CONSTRAINT review_id_pk   PRIMARY KEY(review_id),
        CONSTRAINT review_email_fk FOREIGN KEY(email) REFERENCES mate(email)
        );

--7. �������� ��� ���̺�
DROP TABLE reply;

 CREATE TABLE reply (
   reply_id         NUMBER(4)         PRIMARY KEY,
   email            VARCHAR2(100),    -- �̸��� Į�� �߰�
   reply_content    VARCHAR2(4000),
   reply_date       DATE              DEFAULT SYSDATE,
   reply_notice_id  NUMBER(4),
   CONSTRAINT reply_mate_fk FOREIGN KEY (email) REFERENCES mate(email) ON DELETE CASCADE );  

create sequence reply_seq; 

--9. ����
SELECT *
FROM photo;
DROP TABLE photo;

CREATE TABLE photo (
	photo_id	VARCHAR2(50)		NOT NULL,
	photo_name	VARCHAR2(4000)		NOT NULL,
	type	VARCHAR2(50)		,
	crew_id	VARCHAR2(50)		NOT NULL
);
--�������� ����
ALTER TABLE photo
  ADD ( CONSTRAINT photo_id_pk   PRIMARY KEY(photo_id),
   CONSTRAINT photo_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id));
-- sequence ����
CREATE SEQUENCE photo_seq
START WITH 1
INCREMENT BY 1;

--���� insert
INSERT INTO photo (photo_id, photo_name, crew_id)
VALUES (photo_seq.NEXTVAL, 'pic02', '139');
   
--�������� ���� �߰�
INSERT ALL
    INTO crew VALUES(crew_id, title, crewdate, mate_count, crew_location, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description, awaiter_count)
    INTO photo VALUES(name, type);

-- ũ��+���� ���̺� ����Ʈ(ȭ�鿡 ���)
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
WHERE title = '�׽�Ʈ ����...';

select photo_id, photo_name, crew_id
from photo
ORDER BY crew_id DESC;
WHERE crew_id =  '143';

commit;

DELETE FROM photo 
WHERE photo_id = 108;

--04�� 17�� ������

SELECT crew_id, title, TO_CHAR(crewdate, 'HH24:SS'), mate_count, crew_location, crew_location_dt, crewlevel, course_leng, course_intro, weather_intro, etc_intro, description
FROM crew
WHERE TRUNC(crewdate) = TRUNC(SYSDATE + 0);
SELECT TO_CHAR(crewdate, 'MM"��" DD"��" DAY')
FROM crew
WHERE TRUNC(crewdate) = TRUNC(SYSDATE + 0);




