------------------------------
--CLEANING BASE IF NECESSARY--
------------------------------
DECLARE
   does_not_exist   EXCEPTION;
   PRAGMA EXCEPTION_INIT (does_not_exist, -942);
BEGIN

   EXECUTE IMMEDIATE 'DROP TABLE USERS';

EXCEPTION
   WHEN does_not_exist
   THEN
      NULL;
END;
/

------------------------------
--CLEANING SEQUENCES IF NECESSARY--
------------------------------


DECLARE
   does_not_exist   EXCEPTION;
   PRAGMA EXCEPTION_INIT (does_not_exist, -02289);
BEGIN

   EXECUTE IMMEDIATE 'DROP SEQUENCE USERS_SEQ';

EXCEPTION
   WHEN does_not_exist
   THEN
      NULL;
END;
/
-------------------
--CREATING TABLES--
-------------------



CREATE TABLE USERS
(
  ID NUMBER(20) NOT NULL
, LOGIN VARCHAR2(50) NOT NULL
, FULL_NAME VARCHAR2(150)
, STATUS VARCHAR2(20)
, CURRENT_MANAGEMENT VARCHAR2(1)
, CONSTRAINT USERS_PK PRIMARY KEY
  (
    ID
  )
  ENABLE
);
CREATE SEQUENCE USERS_SEQ START WITH 1;
CREATE OR REPLACE TRIGGER USERS_TR
BEFORE INSERT ON USERS
FOR EACH ROW

BEGIN
  SELECT USERS_SEQ.NEXTVAL
  INTO   :NEW.ID
  FROM   dual;
END;
/
-------------
--ADD USERS--
-------------

INSERT INTO USERS (LOGIN, FULL_NAME, STATUS, CURRENT_MANAGEMENT) VALUES ('ADMIN', 'JOSE ADMIN', 'ACTIVE', 'Y');
INSERT INTO USERS (LOGIN, FULL_NAME, STATUS, CURRENT_MANAGEMENT) VALUES ('ALMOST_ADMIN', 'ADMIN JR', 'ACTIVE', 'Y');