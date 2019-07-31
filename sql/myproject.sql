drop table ACCOUNT;
CREATE TABLE ACCOUNT (
  ID  INT  NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(30) DEFAULT NULL,
  MONEY DOUBLE DEFAULT NULL,
  CREATE_DATE TIMESTAMP DEFAULT NULL DEFAULT NOW(),
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
-- -----------------------------------------------------
CREATE TABLE T_user_info(
  user_id BIGINT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(100),
  user_pwd VARCHAR(100),
  update_time TIMESTAMP,
  PRIMARY KEY (user_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -------------------------------------------------------------
CREATE TABLE users(
  username VARCHAR(100) NOT NULL PRIMARY KEY,
  PASSWORD VARCHAR(100) NOT NULL,
  enabled BOOLEAN NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

SELECT * FROM users;

CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX ix_auth_username ON authorities (username,authority);

INSERT INTO users(username,PASSWORD,enabled) VALUES('admin','admin',TRUE);
INSERT INTO users(username,PASSWORD,enabled) VALUES('user','user',TRUE);

INSERT INTO authorities(username,authority) VALUES('admin','ROLE_ADMIN');
INSERT INTO authorities(username,authority) VALUES('admin','ROLE_USER');
INSERT INTO authorities(username,authority) VALUES('user','ROLE_USER');

DELETE FROM authorities WHERE username = 'user';

SELECT username,authority FROM authorities WHERE username = 'admin';

-- ---------------------------------------------------------------------------


-- 角色
CREATE TABLE role(
  id BIGINT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(50),
  descn VARCHAR(200),
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
ALTER TABLE role ADD CONSTRAINT pk_role PRIMARY KEY(id);


-- 用户
CREATE TABLE USER(
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50),
  PASSWORD VARCHAR(50),
  STATUS INTEGER,
  descn VARCHAR(200),
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
ALTER TABLE USER ADD CONSTRAINT pk_user PRIMARY KEY(id);


-- 用户角色连接表
CREATE TABLE user_role(
  user_id BIGINT,
  role_id BIGINT
);
-- 资源
CREATE TABLE resc(
  id BIGINT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(50),
  res_type VARCHAR(50),
  res_string VARCHAR(200),
  priority INTEGER,
  descn VARCHAR(200),
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- 资源角色连接表
CREATE TABLE resc_role(
  resc_id BIGINT,
  role_id BIGINT
);
ALTER TABLE resc_role ADD CONSTRAINT pk_resc_role PRIMARY KEY(resc_id, role_id);
ALTER TABLE resc_role ADD CONSTRAINT fk_resc_role_resc FOREIGN KEY(resc_id) REFERENCES resc(id);
ALTER TABLE resc_role ADD CONSTRAINT fk_resc_role_role FOREIGN KEY(role_id) REFERENCES role(id);
-- ---------------------------------------------------------------------------------------------------

INSERT INTO USER(id,username,PASSWORD,STATUS,descn) VALUES(1,'admin','admin',1,'管理员');
INSERT INTO USER(id,username,PASSWORD,STATUS,descn) VALUES(2,'user','user',1,'用户');

INSERT INTO role(id,NAME,descn) VALUES(1,'ROLE_ADMIN','管理员角色');
INSERT INTO role(id,NAME,descn) VALUES(2,'ROLE_USER','用户角色');
INSERT INTO user_role(user_id,role_id) VALUES(1,1);
INSERT INTO user_role(user_id,role_id) VALUES(1,2);
INSERT INTO user_role(user_id,role_id) VALUES(2,2);

INSERT INTO resc(id,NAME,res_type,res_string,priority,descn) VALUES(1,'','URL','/admin.jsp',1,'');
INSERT INTO resc(id,NAME,res_type,res_string,priority,descn) VALUES(2,'','URL','/**',2,'');

INSERT INTO resc_role(resc_id,role_id) VALUES(1,1);
INSERT INTO resc_role(resc_id,role_id) VALUES(2,1);
INSERT INTO resc_role(resc_id,role_id) VALUES(2,2);

-- --------------------------------------------------------------------------------------------

CREATE TABLE `t_job_log` (
  `job_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `job_start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `job_status` varchar(20) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`job_log_id`),
  UNIQUE KEY `job_id` (`job_id`,`group_id`,`job_start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



CREATE TABLE `t_job_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_interval` int(11) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `t_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `job_name` varchar(50) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `t_api_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',

  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='第三方映射表';












