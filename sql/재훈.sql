SELECT *
FROM crew;

DROP TABLE members;

--�������� ��ȸ
SELECT * 
FROM    ALL_CONSTRAINTS
WHERE    TABLE_NAME = 'notice';

--�������� ��Ȱ��ȭ/Ȱ��ȭ
ALTER TABLE members
  DISABLE CONSTRAINT members_id_pk CASCADE
  DISABLE CONSTRAINT members_phonenum_uk;
  
ALTER TABLE members
  ENABLE CONSTRAINT members_id_pk 
  ENABLE CONSTRAINT members_phonenum_uk;

-- 1. ȸ�� ���̺�
CREATE TABLE members (
	member_id	VARCHAR2(20)		NOT NULL,
	name	VARCHAR2(30)		NOT NULL,
	password	VARCHAR2(20)		NOT NULL,
	gender	VARCHAR2(10)		NOT NULL,
	birthdate	DATE	DEFAULT SYSDATE	NOT NULL,
	phone_number	VARCHAR2(20)		NOT NULL,
	myarea	VARCHAR2(20)		NOT NULL,
	member_class	VARCHAR2(20)		NOT NULL,
	kakaoacc_yn	VARCHAR2(20)		NOT NULL,
	rating_temp	NUMBER(7)		NOT NULL,
	comment_count	NUMBER(7)		NULL,
	join_count	NUMBER(7)		NULL,
	host_count	NUMBER(7)		NULL
);
-- �������� �߰�
ALTER TABLE members
  ADD ( CONSTRAINT members_id_pk   PRIMARY KEY(member_id),
        CONSTRAINT members_phonenum_uk  UNIQUE (phone_number));
--���� ������ �߰�        
INSERT INTO members
VALUES ('kjh', '������', '0000', 'M', SYSDATE,'010-0000-1111', '����', '�Ϲ�ȸ��', '�Ϲݰ���', 36, 10, 10, 10);


--2. ���� ���̺�
DROP TABLE crew;

CREATE TABLE crew (
    crew_id	VARCHAR2(20)		NOT NULL,
	title	VARCHAR2(30)		NOT NULL,
	crewdate	DATE	DEFAULT SYSDATE,
	member_count	NUMBER(7)		NOT NULL,
	crew_location	VARCHAR2(200)		NOT NULL,
	crewlevel	VARCHAR2(30)		NOT NULL,
	course_leng	NUMBER(7)		NOT NULL,
	course_intro	VARCHAR2(300)		NULL,
	weather_intro	VARCHAR2(300)		NULL,
	etc_intro	VARCHAR2(300)		NULL,
	description	VARCHAR2(4000)		NOT NULL,
	awaiter_count	NUMBER(7)			
);
-- sequence ����
CREATE SEQUENCE crew_seq;
-- �������� �߰�
ALTER TABLE crew
  ADD CONSTRAINT crew_id_pk   PRIMARY KEY(crew_id);
-- ���� ������ �߰�
INSERT INTO crew
VALUES (crew_seq.NEXTVAL, '��մ� ����', 
TO_DATE('2023/4/1 15:20:11', 'YYYY/MM/DD HH24:MI:SS'), 5, 
'����� ������ ������ ��������Ʈ ū ����', '�ǰ��� ����', 10, '���� �ڽ��Դϴ� ���� ���� ���־��� ����', '������ ����. ���� �����. ���� ��ڵ� ������.',
'��Ÿ ��������', '�ȳ��ϼ���~ �츮 ������ ��û���ּż������մϴ�. ������ �����ϰ� ���� ������ �ǰ� ưư. ��ų� ����. ���� �� �ٸ� ���� ����. ������ ��� �ǰ��� ���� �����ϴ�', 3);




--3. ���� ���� ���� ���̺�
DROP TABLE faq;

CREATE TABLE faq (
	faq_id	VARCHAR2(20)		NOT NULL,
	category	VARCHAR2(20)		NULL,
	faq_title	VARCHAR2(30)		NOT NULL,
	faq_content	VARCHAR2(300)		NOT NULL,
	faq_date	DATE		NOT NULL,
	member_id	VARCHAR2(20)		NOT NULL
);
-- �������� �߰�
ALTER TABLE faq
  ADD ( CONSTRAINT faq_id_pk   PRIMARY KEY(faq_id),
   CONSTRAINT member_id_fk FOREIGN KEY(member_id) REFERENCES members(member_id));

--4. �������� ���̺�
DROP TABLE notice;

CREATE TABLE notice (
	notice_id	VARCHAR2(20)		NOT NULL,
	title	VARCHAR2(30)		NOT NULL,
	content	VARCHAR2(4000)		NOT NULL,
	notice_date	DATE		NOT NULL,
	Field	NUMBER(7)		NULL,
	member_id	VARCHAR2(20)		NOT NULL
);
-- �������� �߰�
ALTER TABLE notice
  ADD ( CONSTRAINT notice_id_pk   PRIMARY KEY(notice_id),
   CONSTRAINT notice_member_id_fk FOREIGN KEY(member_id) REFERENCES members(member_id));

--5. ��� ���̺�
DROP TABLE location;

CREATE TABLE location (
	location_id	VARCHAR2(20)		NOT NULL,
	adress	VARCHAR2(20)		NOT NULL,
	longitude	VARCHAR2(20)		NOT NULL,
	latitude	VARCHAR2(20)		NOT NULL,
	crew_id	VARCHAR2(20)		NOT NULL
);
-- �������� �߰�
ALTER TABLE location
  ADD ( CONSTRAINT location_id_pk   PRIMARY KEY(location_id),
   CONSTRAINT location_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id));

--6. �����ı�(�ڸ�Ʈ) ���̺�
DROP TABLE review;

CREATE TABLE review (
	review_id	VARCHAR2(20)		NOT NULL,
	author	VARCHAR2(20)		NOT NULL,
	content	VARCHAR2(4000)		NOT NULL,
	writedate	DATE		NOT NULL,
	member_id	VARCHAR2(20)		NOT NULL
);

--7. �������� ��� ���̺�
DROP TABLE comment;

CREATE TABLE comment (
	comment_id	VARCHAR2(20)		NOT NULL,
	content	VARCHAR2(4000)		NOT NULL,
	notice_id	VARCHAR2(20)		NOT NULL,
	member_id	VARCHAR2(20)		NOT NULL
);
-- �������� �߰�
ALTER TABLE comment
  ADD ( CONSTRAINT comment_id_pk   PRIMARY KEY(comment_id),
   CONSTRAINT comment_member_id_fk FOREIGN KEY(member_id) REFERENCES members(member_id),
   CONSTRAINT notice_id_fk FOREIGN KEY(notice_id) REFERENCES notice(notice_id));

--8. ���Ӹ���Ʈ
DROP TABLE crewlist;

CREATE TABLE crewlist (
	crewlist_id	VARCHAR2(20)		NOT NULL,
	types	VARCHAR2(20)		NOT NULL,
	member_id	VARCHAR2(20)		NOT NULL,
	crew_id	VARCHAR2(20)		NOT NULL
);
-- �������� �߰�
ALTER TABLE crewlist
  ADD ( CONSTRAINT crewlist_id_pk   PRIMARY KEY(crewlist_id),
   CONSTRAINT crewlist_member_id_fk FOREIGN KEY(member_id) REFERENCES members(member_id),
   CONSTRAINT crewlist_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id));

--9. ����
DROP TABLE photo;

CREATE TABLE photo (
	photo_id	VARCHAR2(20)		NOT NULL,
	name	VARCHAR2(20)		NOT NULL,
	type	VARCHAR2(20)		NOT NULL,
	crew_id	VARCHAR2(20)		NOT NULL
);
ALTER TABLE photo
  ADD ( CONSTRAINT photo_id_pk   PRIMARY KEY(photo_id),
   CONSTRAINT photo_crew_id_fk FOREIGN KEY(crew_id) REFERENCES crew(crew_id));
