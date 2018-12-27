create table TICTACTOE_GAME(
	`ID` bigint(20) NOT NULL,
	`CREATED_AT` datetime(3) NOT NULL,
	`LAST_MODIFIED` datetime(3) NOT NULL,
	`DESCRIPTION` varchar(255) DEFAULT NULL,
	`STATUS_ID` bigint(20) NOT NULL,
	constraint `PK_GAME` primary key(`ID`),
	constraint `name` foreign key(`STATUS_ID`) references GAME_STATUS(`ID`)
);


create table tictactoe.TICTACTOE_FIELD(
	`ID` bigint(20) NOT NULL,
	`CREATED_AT` datetime(3) NOT NULL,
	`LAST_MODIFIED` datetime(3) NOT NULL,
	`FIELD_ID` bigint(20) DEFAULT NULL,
	`GAME_ID` bigint(20) DEFAULT NULL, 
	`VALUE` varchar(255) DEFAULT NULL, 
	primary key(`ID`)
);




create table SEQ_GAME (next_val bigint) engine=MyISAM;
insert into SEQ_GAME values ( 1 );
create table SEQ_TICTACTOE_FIELD (next_val bigint) engine=MyISAM;
insert into SEQ_TICTACTOE_FIELD values ( 1 );



