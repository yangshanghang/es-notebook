-- ----------------------------
-- Table structure for NBOOK_CATEGORY
-- ----------------------------
DROP TABLE IF EXISTS `NBOOK_CATEGORY`;
CREATE TABLE `NBOOK_CATEGORY` (
  `ID` varchar(32) NOT NULL COMMENT '唯一标识',
  `NC_NAME` varchar(100) NOT NULL COMMENT '笔记类型名称',
  `NC_ORDER_NUMBER` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目表';

-- ----------------------------
-- Table structure for NBOOK_COMMENT
-- ----------------------------
DROP TABLE IF EXISTS `NBOOK_COMMENT`;
CREATE TABLE `NBOOK_COMMENT` (
  `ID` varchar(32) NOT NULL COMMENT '唯一标识',
  `NC_USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `NC_NOTE_ID` varchar(32) NOT NULL COMMENT '笔记id',
  `NC_CONTENT` varchar(500) NOT NULL COMMENT '评论内容',
  `NC_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NC_OPERATE_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`ID`),
  KEY `IDX_NC_NOTE_ID` (`NC_NOTE_ID`) USING BTREE COMMENT '所属笔记索引',
  KEY `IDX_NC_USER_ID` (`NC_USER_ID`) USING BTREE COMMENT '用户ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Table structure for NBOOK_FEEDBACK
-- ----------------------------
DROP TABLE IF EXISTS `NBOOK_FEEDBACK`;
CREATE TABLE `NBOOK_FEEDBACK` (
  `ID` varchar(32) NOT NULL COMMENT '唯一标识',
  `NF_USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `NF_CONTENT` varchar(1000) NOT NULL COMMENT '反馈内容',
  `NF_CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见反馈表';

-- ----------------------------
-- Table structure for NBOOK_MY_FAVORITE
-- ----------------------------
DROP TABLE IF EXISTS `NBOOK_MY_FAVORITE`;
CREATE TABLE `NBOOK_MY_FAVORITE` (
  `ID` varchar(32) NOT NULL COMMENT '唯一标识',
  `NMF_USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `NMF_NOTE_ID` varchar(32) NOT NULL COMMENT '笔记id',
  `NMF_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NMF_OPERATE_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`ID`),
  KEY `IDX_NMF_USER_ID` (`NMF_USER_ID`) USING BTREE COMMENT '用户ID索引',
  KEY `IDX_NMF_NOTE_ID` (`NMF_NOTE_ID`) USING BTREE COMMENT '笔记ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的收藏表';

-- ----------------------------
-- Table structure for NBOOK_MY_FOCUS
-- ----------------------------
DROP TABLE IF EXISTS `NBOOK_MY_FOCUS`;
CREATE TABLE `NBOOK_MY_FOCUS` (
  `ID` varchar(32) NOT NULL COMMENT '唯一标识',
  `NMF_USER_ID` varchar(32) NOT NULL COMMENT '用户id',
  `NMF_CATEGORY_ID` varchar(32) NOT NULL COMMENT '类目id',
  `NMF_ORDER_NUMBER` int(2) DEFAULT NULL COMMENT '排序',
  `NMF_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NMF_OPERATE_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`ID`),
  KEY `IDX_NMF_CATEGORY_ID` (`NMF_CATEGORY_ID`) USING BTREE COMMENT '分类索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的关注表';

-- ----------------------------
-- Table structure for NBOOK_NOTE
-- ----------------------------
DROP TABLE IF EXISTS `NBOOK_NOTE`;
CREATE TABLE `NBOOK_NOTE` (
  `ID` varchar(32) NOT NULL COMMENT '唯一标识',
  `NN_TITLE` varchar(100) NOT NULL COMMENT '标题',
  `NN_SUMMARY` varchar(1000) NOT NULL COMMENT '摘要',
  `NN_CONTENT` longtext NOT NULL COMMENT '内容',
  `NN_SCALE_IMG_URL` varchar(200) DEFAULT NULL COMMENT '缩略图url',
  `NN_TYPE_ID` varchar(32) DEFAULT NULL COMMENT '笔记类型 唯一标识',
  `NN_ATTACHMENT_FLAG` char(1) NOT NULL COMMENT '附件标识 0:没有附件 1:有附件',
  `NN_AUTHOR` varchar(20) NOT NULL COMMENT '作者',
  `NN_USER_ID` varchar(32) DEFAULT NULL COMMENT '用户id（作者id）',
  `NN_OPERATOR` varchar(20) DEFAULT NULL COMMENT '操作者',
  `NN_PRAISE_COUNT` int(9) DEFAULT NULL COMMENT '点赞数',
  `NN_VISIBLE_STATUS` char(1) DEFAULT NULL COMMENT '可见状态  0：公开  1：仅自己可见',
  `NN_CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `NN_OPERATE_TIME` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`ID`),
  KEY `IDX_NN_TYPE_ID` (`NN_TYPE_ID`) USING BTREE COMMENT '分类索引',
  KEY `IDX_NN_TITLE` (`NN_TITLE`) USING BTREE COMMENT '标题索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记表';

-- ----------------------------
-- Table structure for NBOOK_USER
-- ----------------------------
DROP TABLE IF EXISTS `NBOOK_USER`;
CREATE TABLE `NBOOK_USER` (
  `ID` varchar(32) NOT NULL COMMENT '唯一标识',
  `NU_EMAIL` varchar(50) NOT NULL COMMENT '用户邮箱（账号）',
  `NU_PASSWORD` varchar(32) NOT NULL COMMENT '用户密码',
  `NU_REAL_NAME` varchar(20) NOT NULL COMMENT '真实姓名',
  `NU_QQ` varchar(20) NOT NULL COMMENT 'QQ号',
  `NU_ENCRYPT_SALT` varchar(32) NOT NULL COMMENT 'MD5加盐值',
  `NU_TYPE` char(1) NOT NULL COMMENT '用户类型  0：系统用户，  1：普通用户',
  `NU_STATUS` char(1) NOT NULL COMMENT '用户状态  0：锁定， 1：正常， 2：未激活',
  `NU_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `NU_OPERATE_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `NU_ACTIVATE_TIME` datetime DEFAULT NULL COMMENT '激活时间',
  `NU_PASSWORD_MODIFY_TIME` datetime DEFAULT NULL COMMENT '密码修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
