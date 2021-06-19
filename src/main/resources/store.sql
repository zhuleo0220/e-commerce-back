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

DROP TABLE IF EXISTS `pms_product_category`;
CREATE TABLE `pms_product_category` (
                                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                        `parent_id` bigint(20) DEFAULT NULL COMMENT '上机分类的编号：0表示一级分类',
                                        `name` varchar(64) DEFAULT NULL,
                                        `level` int(1) DEFAULT NULL COMMENT '分类级别：0->1级；1->2级',
                                        `product_count` int(11) DEFAULT NULL,
                                        `keywords` varchar(255) DEFAULT NULL,
                                        `description` text COMMENT '描述',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='产品分类';
DROP TABLE IF EXISTS `oms_cart_item`;
CREATE TABLE `oms_cart_item` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `product_id` bigint(20) DEFAULT NULL,
                                 `member_id` bigint(20) DEFAULT NULL,
                                 `order_id` bigint(20) DEFAULT NULL,
                                 `quantity` int(11) DEFAULT NULL COMMENT '购买数量',
                                 `price` decimal(10,2) DEFAULT NULL COMMENT '添加到购物车的价格',
                                 `product_name` varchar(500) DEFAULT NULL COMMENT '商品名称',
                                 `subtotal` decimal(10,2) DEFAULT NULL ,
                                 `delete_status` int(20) DEFAULT NULL,

                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='购物车表';
DROP TABLE IF EXISTS `member_collection`;
CREATE TABLE `member_collection` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `product_id` bigint(20) DEFAULT NULL,
                                 `member_id` bigint(20) DEFAULT NULL,


                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ;
DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
                             `member_id` bigint(20) NOT NULL,
                             `create_time` datetime DEFAULT NULL COMMENT '提交时间',
                             `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
                             `note` varchar(250) DEFAULT NULL,
                             `status` int(1) DEFAULT NULL COMMENT '0:ordercreated,1:paied,2:delivered,3:received',
                             `confirm_status` int(1) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
                             `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
                             `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
                             `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='订单表';
