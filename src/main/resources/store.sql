DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES ('1', 'test', '$2a$10$S0ssvNzGpG1pxyXmfdGnNuLAmDIdWU4MV8GLgWxUAizOuch1fdrvG',  '2018-09-29 13:55:30', '2018-09-29 13:55:39');#username: test, password: zhu
INSERT INTO `ums_admin` VALUES ('3', 'admin', '$2a$10$S0ssvNzGpG1pxyXmfdGnNuLAmDIdWU4MV8GLgWxUAizOuch1fdrvG',  '2018-10-08 13:32:47', '2019-04-20 12:45:16');#username: admin, password: zhu
