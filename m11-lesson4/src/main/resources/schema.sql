-- HSQL Schema
--drop table if exists acl_entry;
--drop table if exists acl_object_identity; 
--drop table if exists acl_class;   
--drop table if exists acl_sid;
--
--create table acl_sid (
--  id bigint generated by default as identity(start with 100) not null primary key,
--  principal boolean not null,
--  sid varchar_ignorecase(100) not null,
--  constraint unique_uk_1 unique(sid,principal) );
--
--create table acl_class (
--  id bigint generated by default as identity(start with 100) not null primary key,
--  class varchar_ignorecase(100) not null,
--  constraint unique_uk_2 unique(class) );
--
--create table acl_object_identity (
--  id bigint generated by default as identity(start with 100) not null primary key,
--  object_id_class bigint not null,
--  object_id_identity bigint not null,
--  parent_object bigint,
--  owner_sid bigint not null,
--  entries_inheriting boolean not null,
--  constraint unique_uk_3 unique(object_id_class,object_id_identity),
--  constraint foreign_fk_1 foreign key(parent_object)references acl_object_identity(id),
--  constraint foreign_fk_2 foreign key(object_id_class)references acl_class(id),
--  constraint foreign_fk_3 foreign key(owner_sid)references acl_sid(id) );
--  
--create table acl_entry (
--  id bigint generated by default as identity(start with 100) not null primary key,
--  acl_object_identity bigint not null,ace_order int not null,sid bigint not null,
--  mask integer not null,granting boolean not null,audit_success boolean not null,
--  audit_failure boolean not null,
--  constraint unique_uk_4 unique(acl_object_identity,ace_order),
--  constraint foreign_fk_4 foreign key(acl_object_identity)
--      references acl_object_identity(id),
--  constraint foreign_fk_5 foreign key(sid) references acl_sid(id) );

--
--

-- MySQL Schema
USE lss114;

DROP TABLE IF EXISTS Possession;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS acl_entry;
DROP TABLE IF EXISTS acl_object_identity; 
DROP TABLE IF EXISTS acl_class;   
DROP TABLE IF EXISTS acl_sid;


CREATE TABLE acl_sid (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    principal BOOLEAN NOT NULL,
    sid VARCHAR(100) NOT NULL,
    UNIQUE KEY unique_acl_sid (sid, principal)
) ENGINE=InnoDB;

CREATE TABLE acl_class (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class VARCHAR(100) NOT NULL,
    UNIQUE KEY uk_acl_class (class)
) ENGINE=InnoDB;

CREATE TABLE acl_object_identity (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    object_id_class BIGINT UNSIGNED NOT NULL,
    object_id_identity BIGINT NOT NULL,
    parent_object BIGINT UNSIGNED,
    owner_sid BIGINT UNSIGNED,
    entries_inheriting BOOLEAN NOT NULL,
    UNIQUE KEY uk_acl_object_identity (object_id_class, object_id_identity),
    CONSTRAINT fk_acl_object_identity_parent FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_object_identity_class FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
    CONSTRAINT fk_acl_object_identity_owner FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
) ENGINE=InnoDB;

CREATE TABLE acl_entry (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acl_object_identity BIGINT UNSIGNED NOT NULL,
    ace_order INTEGER NOT NULL,
    sid BIGINT UNSIGNED NOT NULL,
    mask INTEGER UNSIGNED NOT NULL,
    granting BOOLEAN NOT NULL,
    audit_success BOOLEAN NOT NULL,
    audit_failure BOOLEAN NOT NULL,
    UNIQUE KEY unique_acl_entry (acl_object_identity, ace_order),
    CONSTRAINT fk_acl_entry_object FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_entry_acl FOREIGN KEY (sid) REFERENCES acl_sid (id)
) ENGINE=InnoDB;

--

CREATE TABLE User (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL
) ENGINE=InnoDB;

CREATE TABLE Possession (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
  owner_id bigint(20) NOT NULL,
  CONSTRAINT fk_possession_owner FOREIGN KEY (owner_id) REFERENCES User (id)
) ENGINE=InnoDB;

