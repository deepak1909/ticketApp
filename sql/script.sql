-- Creates User table --

CREATE TABLE [dbo].[users](
[id] [numeric](19, 0) IDENTITY(1, 1) PRIMARY KEY NOT NULL,
[create_dt_tm] [datetime] NOT NULL,
[create_user_id] [numeric](19, 0) NOT NULL,
[update_dt_tm] [datetime] NULL,
[update_user_id] [numeric](19, 0) NULL,
[firstName] varchar(50) NOT NULL,
[lastname] varchar(50) NOT NULL,
[email] varchar(50) NOT NULL,
[password] varchar(50) NOT NULL,
[delete_flag] [tinyint] NOT NULL default(0)
)
GO

CREATE INDEX user_first_name on [users] (firstname, lastname)
GO

INSERT INTO [dbo].[users] (create_dt_tm, create_user_id, firstName, lastname, email, password, delete_flag) VALUES (getDate(), 1, 'admin1', 'admin2', 'admin@redmart.com', 'admin', 0)
GO
INSERT INTO [dbo].[users] (create_dt_tm, create_user_id, firstName, lastname, email, password, delete_flag) VALUES (getDate(), 1, 'user1', 'user2', 'user@redmart.com', 'user', 0)
GO
INSERT INTO [dbo].[users] (create_dt_tm, create_user_id, firstName, lastname, email, password, delete_flag) VALUES (getDate(), 1, 'abc1', 'abc2', 'abc@redmart.com', 'abc', 0)
GO
INSERT INTO [dbo].[users] (create_dt_tm, create_user_id, firstName, lastname, email, password, delete_flag) VALUES (getDate(), 1, 'xyz1', 'xyz2', 'xyz@redmart.com', 'xyz', 0)
GO
INSERT INTO [dbo].[users] (create_dt_tm, create_user_id, firstName, lastname, email, password, delete_flag) VALUES (getDate(), 1, 'pqr1', 'pqr2', 'pqr@redmart.com', 'pqr', 0)
GO

-- Creates Customer table --

CREATE TABLE [dbo].[customer](
[id] [numeric](19, 0) IDENTITY(1, 1) PRIMARY KEY NOT NULL,
[create_dt_tm] [datetime] NOT NULL,
[create_user_id] [numeric](19, 0) NOT NULL,
[update_dt_tm] [datetime] NULL,
[update_user_id] [numeric](19, 0) NULL,
[firstName] varchar(50) NOT NULL,
[lastname] varchar(50) NOT NULL,
[email] varchar(50) NOT NULL,
[phone] varchar(20) NOT NULL,
[address1] varchar(50) NOT NULL,
[address2] varchar(50) NULL,
[city] varchar(50) NOT NULL,
[state] varchar(50) NOT NULL,
[zip] varchar(15) NOT NULL,
[delete_flag] [tinyint] NOT NULL default(0)
)
GO

CREATE INDEX customer_first_name on [customer] (firstname, lastname)
GO


-- Creates Ticket table --

CREATE TABLE [dbo].[ticket](
[id] [numeric](19, 0) IDENTITY(1, 1) PRIMARY KEY NOT NULL,
[create_dt_tm] [datetime] NOT NULL,
[create_user_id] [numeric](19, 0) NOT NULL,
[update_dt_tm] [datetime] NULL,
[update_user_id] [numeric](19, 0) NULL,
[title] varchar(100) NOT NULL,
[reported_by_id] [numeric](19, 0) NOT NULL,
[assigned_to_id] [numeric](19, 0) NULL,
[comment] varchar(255) NULL,
[status] varchar(10) NOT NULL,
[delete_flag] [tinyint] NOT NULL default(0)
)
GO

CREATE INDEX ticket_index on [ticket] (reported_by_id, assigned_to_id)
GO