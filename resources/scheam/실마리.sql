
-------------------------------------------------------------------------------------------
CREATE TABLE MOVIE_GENRE (
	MOVIE_GENRE_SEQ NUMBER primary key,
	MOVIE_GENRE	VARCHAR2(30)	 not NULL
);
-------------------------------------------------------------------------------------------
CREATE TABLE MOVIE (
	MOVIE_SEQ	NUMBER primary key ,
	MOVIE_GENRE_SEQ NUMBER not null,
	MOVIE_NAME	VARCHAR2(50)	 NOT NULL,
	MOVIE_DIRECTER	VARCHAR2(30)	 NOT NULL,
	RELEASE_DATE	DATE	NULL,
	REG_DATE	DATE DEFAULT SYSDATE	NOT NULL
);

alter table MOVIE ADD CONSTRAINT FK_MOVIE_GENRE_SEQ2 
foreign key (MOVE_GENRE_SEQ) references MOVIE_GENRE (MOVIE_GENRE_SEQ); -- fk ����

------------------------------------------------------------------------------------------------------

CREATE TABLE REVIEW (
	REVIEW_SEQ	NUMBER	NOT NULL primary key,
	MOVIE_SEQ	NUMBER	NOT NULL,
	USER_SEQ	NUMBER	NOT NULL,
	REVIEW	VARCHAR2(300)	NULL,
	SCORE	 NUMBER(1)	NULL,
	REG_DATE	DATE DEFAULT SYSDATE	NOT NULL,
    
    CONSTRAINT FK_MOVIE_SEQ foreign key (MOVIE_SEQ) 
    references MOVIE (MOVIE_SEQ),
    CONSTRAINT FK_USER_SEQ foreign key (USER_SEQ) 
    references users (USER_SEQ)
);

---------------------------------------------------------------------

CREATE TABLE DIPS (
	DIPS_SEQ NUMBER	NOT NULL,
	USER_SEQ	NUMBER	NOT NULL,
	MOVIE_SEQ	NUMBER	NOT NULL,
	REG_DATE	DATE DEFAULT SYSDATE	NOT NULL,
    
     CONSTRAINT FK_USER_SEQ_DIPS foreign key (USER_SEQ) 
    references users (USER_SEQ),
    CONSTRAINT FK_MOVIE_SEQ_DIPS foreign key (MOVIE_SEQ) 
    references MOVIE (MOVIE_SEQ)
);

alter table DIPS add constraint PK_DIPS 
primary key (DIPS_SEQ); -- pk ����
------------------------------------------------------------------
CREATE TABLE REVIEW_ETC (
	REVIEW_ETC_SEQ	NUMBER	primary key,
	USER_SEQ	NUMBER	NOT NULL,
	REVIEW_SEQ	NUMBER	NOT NULL,
	LIKE_DISLIKE NUMBER	NULL,
	REG_DATE	DATE	NOT NULL,
    
    CONSTRAINT FK_USER_SEQ_REVIEW_ETC foreign key (USER_SEQ) 
    references users (USER_SEQ),
    CONSTRAINT FK_REVIEW_SEQ_REVIEW_ETC foreign key (REVIEW_SEQ) 
    references review (REVIEW_SEQ)
);


-----------------------------------------------------------------
CREATE TABLE MOVIE_ACTOR (
	MOVIE_ACTOR_SEQ	NUMBER	primary key,
	MOVIE_SEQ	NUMBER	NOT NULL,
	ACTOR_NAME	VARCHAR2(30)	NOT NULL,
	ACTOR_ROLE	VARCHAR2(30)	NOT NULL,
    
    CONSTRAINT FK_MOVIE_SEQ_ACTOR foreign key (MOVIE_SEQ) 
    references MOVIE (MOVIE_SEQ)
);
---------------------------------------------------------------


