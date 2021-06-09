DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  'role' int comment '1 管理员，0 用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES ('1', 'test', '$2a$10$S0ssvNzGpG1pxyXmfdGnNuLAmDIdWU4MV8GLgWxUAizOuch1fdrvG', '1', '2018-09-29 13:55:30', '2018-09-29 13:55:39');#username: test, password: zhu
INSERT INTO `ums_admin` VALUES ('3', 'admin', '$2a$10$S0ssvNzGpG1pxyXmfdGnNuLAmDIdWU4MV8GLgWxUAizOuch1fdrvG', '1', '2018-10-08 13:32:47', '2019-04-20 12:45:16');#username: admin, password: zhu

DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `product_category_id` bigint(20) DEFAULT NULL,
                               `name` varchar(64) NOT NULL,
                               `images` MEDIUMBLOB DEFAULT NULL,
                               `price` decimal(10,2) DEFAULT NULL,
                               `description` text COMMENT '商品描述',
                               `stock` int(11) DEFAULT NULL COMMENT '库存',
                               `keywords` varchar(255) DEFAULT NULL,
                               `product_category_name` varchar(255) DEFAULT NULL COMMENT '商品分类名称',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='商品信息';

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `user_id` bigint(20) DEFAULT NULL,
                               `address` varchar(64) NOT NULL,
                               `city` varchar(64) NOT NULL,
                               `state` varchar(64) NOT NULL,
                               `country` varchar(64) NOT NULL,
                               `Zipcode` int(5) ,
                               `phone_number` int(11) ,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='地址信息';
