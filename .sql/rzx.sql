/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.84
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 192.168.0.84:3306
 Source Schema         : ryx

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 28/09/2021 15:39:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rzx_score_record
-- ----------------------------
DROP TABLE IF EXISTS `rzx_score_record`;
CREATE TABLE `rzx_score_record`  (
  `score_record_id` 	varchar(32) NOT NULL,
  `user_info_id` 		varchar(32) DEFAULT NULL COMMENT '用户id',
  `user_id` 			varchar(32) DEFAULT NULL COMMENT '渠道来源用户id',
  `in_source` 			varchar(1)  DEFAULT NULL COMMENT '渠道来源(1-任货行 2-任意行 3-任通行)',
  `source_id` 			varchar(32) DEFAULT NULL COMMENT '来源id',
  `source_type` 		varchar(2)  DEFAULT NULL COMMENT '来源类型 1-积分消费 2-渠道积分转换',
  `score` 				int(10) 	DEFAULT NULL COMMENT '积分',
  `surplus_score` 		int(10) 	DEFAULT NULL COMMENT '当前剩余积分',
  `status` 				varchar(1) 	DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by`           varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by`           varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (score_record_id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行渠道用户积分流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_user_info
-- ----------------------------
DROP TABLE IF EXISTS `rzx_user_info`;
CREATE TABLE `rzx_user_info`  (
  `user_info_id` 		varchar(32) NOT NULL,
  `user_id` 			varchar(32) DEFAULT NULL COMMENT '渠道来源用户id 各渠道用户openId',
  `user_type` 			varchar(1) DEFAULT NULL COMMENT '渠道用户类型(0-代理分销用户 1-普通用户 )',
  `source` 				varchar(1)  DEFAULT NULL COMMENT '渠道来源(1-任货行 2-任意行 3-任通行)',
  `status` 				varchar(1) 	DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `score` 				int(10) 	DEFAULT NULL COMMENT '积分',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by`           varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by`           varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_info_id`) USING BTREE,
  UNIQUE KEY `user_source` (`user_id`,`user_type`,`source`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行渠道用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_com_order_info
-- ----------------------------
DROP TABLE IF EXISTS `rzx_com_order_info`;
CREATE TABLE `rzx_com_order_info`  (
  `comsalesorder_id` 	varchar(32) NOT NULL,
  `com_order_id` 		varchar(32) DEFAULT NULL COMMENT '主订单号',
  `user_info_id` 		varchar(32) DEFAULT NULL COMMENT '用户id',
  `total_amount` 		varchar(16) DEFAULT NULL COMMENT '订单总额',
  `products` 			text 					 COMMENT '商品明细',
  `status` 				varchar(1) DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by`           varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by`           varchar(32) DEFAULT NULL COMMENT '更新人',
  `order_nums` 			text 	 				COMMENT '子订单处理结果',
  PRIMARY KEY (`comsalesorder_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行 主订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_order_info
-- ----------------------------
DROP TABLE IF EXISTS `rzx_order_info`;
CREATE TABLE `rzx_order_info`  (
  `salesorder_id` 		varchar(32) NOT NULL,
  `order_num` 			varchar(32) DEFAULT NULL COMMENT '商城订单号',
  `order_id` 			varchar(32) DEFAULT NULL COMMENT '销售订单号',
  `order_key` 			varchar(50) DEFAULT NULL COMMENT '外部订单号',
  `order_shipment_fee`  varchar(10) DEFAULT NULL COMMENT '快递费用',
  `saleman_id` 			varchar(32) DEFAULT NULL COMMENT '用户id',
  `user_info_id` 		varchar(32) DEFAULT NULL COMMENT '任智行渠道用户id',
  `giftpackage_id` 		varchar(32) DEFAULT NULL COMMENT '礼包ID',
  `commodity_code` 		varchar(32) DEFAULT NULL COMMENT '商品编码',
  `couponsinfo_id` 		varchar(32) DEFAULT NULL COMMENT '券号',
  `init_total_amount` 	varchar(16) DEFAULT NULL COMMENT '初始价格',
  `sale_total_amount` 	varchar(16) DEFAULT NULL COMMENT '出售价格（售价）',
  `pay_way` 			varchar(2) 	DEFAULT NULL COMMENT '支付方式(0-云卓微信支付)',
  `out_trade_id` 		varchar(50) DEFAULT NULL COMMENT '支付外部流水号',
  `notifyjson` 			text  		COMMENT '支付响应内容',
  `qr_code` 			text 		COMMENT 'C扫B订单支付二维码',
  `order_status` 		varchar(2)   DEFAULT NULL COMMENT '订单状态(0-未支付 1-已支付 2-支付失败 3-支付中 4-订单取消 9-订单创建失败)',
  `sale_time` 			varchar(30)  DEFAULT NULL COMMENT '支付时间',
  `receiveaddress_id` 	varchar(32)  DEFAULT NULL COMMENT '收货地址唯一ID',
  `remark` 				varchar(100) DEFAULT NULL COMMENT '订单备注信息',
  `order_provid` 		varchar(2)   DEFAULT NULL COMMENT '供应商（1-云中鹤，2-百汇）',
  `error_code` 			varchar(20)  DEFAULT NULL COMMENT '供应商错误码',
  `error_message` 		varchar(100) DEFAULT NULL COMMENT '供应商错误信息',
  `status` 				varchar(1) 	DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` 			varchar(32)  DEFAULT NULL COMMENT '创建人',  
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` 			varchar(32)  DEFAULT NULL COMMENT '更新人',
  `brokerage` 			varchar(15)  DEFAULT NULL COMMENT '佣金',
  `qr_bill_url` 		varchar(225) DEFAULT NULL COMMENT '发票获取二维码',
  `bill_record_id` 		varchar(32)  DEFAULT NULL COMMENT '开票信息ID',
  `pay_end_time` 		varchar(30)  DEFAULT NULL COMMENT '支付有效截至时间',
  `in_source` 			varchar(1)	DEFAULT NULL COMMENT '来源(1-任货行 2-任意行 3-任通行)',
  `order_type` 			varchar(1) 	DEFAULT NULL COMMENT '订单类型 1-礼包 2-商品订单',
  `com_order_id` 		varchar(32)	DEFAULT NULL COMMENT '主订单号',
  `pay_type` 			varchar(1)	DEFAULT NULL COMMENT '结算方式1-现金 2-现金+积分 3-积分',
  `pay_score` 			varchar(20)  DEFAULT NULL COMMENT '支付积分',
  `express_code` 		varchar(50)  DEFAULT NULL COMMENT '运单号',
  `com_price` 			varchar(10)  DEFAULT NULL COMMENT '商品单价',
  `com_count` 			varchar(10)  DEFAULT NULL COMMENT '商品数量',
  `notics_status`       varchar(1)   DEFAULT NULL COMMENT '商城订单同步结果 0-同步失败 1同步成功',
  `check_status`        varchar(2)   DEFAULT NULL COMMENT '核销状态(0-未核销 1-已核销)',
  PRIMARY KEY (`salesorder_id`) USING BTREE,
  INDEX `order_id_index`(`order_id`) USING BTREE,
  INDEX `saleman_id_index`(`saleman_id`) USING BTREE,
  INDEX `giftpackage_id_index`(`giftpackage_id`) USING BTREE,
  INDEX `commodity_code_index`(`commodity_code`) USING BTREE,
  INDEX `couponsinfo_id_index`(`couponsinfo_id`) USING BTREE,
  INDEX `receiveaddress_id_index`(`receiveaddress_id`) USING BTREE,
  INDEX `bill_record_id_index`(`bill_record_id`) USING BTREE,
  INDEX `create_time_index`(`create_time`) USING BTREE,
  INDEX `sale_time_index`(`sale_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行 销售订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_gift_package_config
-- ----------------------------
DROP TABLE IF EXISTS `rzx_gift_package_config`;
CREATE TABLE `rzx_gift_package_config`  (
  `giftpackage_id` 			varchar(32) NOT NULL,
  `name` 					varchar(32) DEFAULT NULL COMMENT '礼包名称',
  `gift_explain` 			varchar(32) DEFAULT NULL COMMENT '礼包描述',
  `init_amount` 			varchar(16) DEFAULT NULL COMMENT '礼包原价',
  `sale_amount` 			varchar(16) DEFAULT NULL COMMENT '礼包售价',
  `type` 					varchar(2)  DEFAULT NULL COMMENT '礼包类型（0-单品 1-多选一）',
  `status` 					varchar(1) 	DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `create_time` 			datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by`               varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` 			datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by`               varchar(32) DEFAULT NULL COMMENT '更新人',
  `sallout_count` 			varchar(20) DEFAULT NULL COMMENT '已售份数',
  `product_order` 			int(5) 		DEFAULT NULL COMMENT '礼包排序',
  `product_logo` 			varchar(255) DEFAULT NULL COMMENT '礼包封面',
  `one_level_integral` 		varchar(30) DEFAULT NULL COMMENT '一级分配积分',
  `two_level_integral` 		varchar(30) DEFAULT NULL COMMENT '二级分配积分',
  `three_level_integral`	varchar(30) DEFAULT NULL COMMENT '三级分配积分',
  `platform_subsidy_amt`	varchar(10) DEFAULT NULL COMMENT '平台补贴佣金总比例',
  `expense_policy` 			text 		COMMENT '费用政策',
  `mart_rate` 				varchar(20) DEFAULT NULL COMMENT '市场费用率',
  `promote_rate` 			varchar(20) DEFAULT NULL COMMENT '推广费用率',
  `manage_rate` 			varchar(20) DEFAULT NULL COMMENT '管理费用率',
  `share_rate` 				varchar(20) DEFAULT NULL COMMENT '可分润推广费用率',
  `it_rate` 				varchar(20) DEFAULT NULL COMMENT '技术服务费用率',
  `a_share_rate` 			varchar(20) DEFAULT NULL COMMENT '甲方分润占比',
  `b_share_rate` 			varchar(20) DEFAULT NULL COMMENT '已方分润占比',
  `mart_type` 				varchar(2)  DEFAULT NULL COMMENT '市场费类型 0-百分比 1-绝对值',
  `product_tax_rate` 		varchar(20) DEFAULT NULL COMMENT '产品税率',
  `mart_value` 				varchar(20) DEFAULT NULL COMMENT '市场费绝对值',
  `partner` 				varchar(2) DEFAULT NULL COMMENT '所属合作商(0-广西交通厅)',
  PRIMARY KEY (`giftpackage_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行 礼包配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_receive_address_info
-- ----------------------------
DROP TABLE IF EXISTS `rzx_receive_address_info`;
CREATE TABLE `rzx_receive_address_info`  (
  `receiveaddress_id` 	varchar(32)  NOT NULL,
  `saleman_id` 			varchar(32)  DEFAULT NULL COMMENT '用户id',
  `province` 			varchar(20)  DEFAULT NULL COMMENT '省, 区域信息ID',
  `province_name` 		varchar(20)  DEFAULT NULL COMMENT '省',
  `city` 				varchar(20)  DEFAULT NULL COMMENT '市, 区域信息ID',
  `city_name` 			varchar(20)  DEFAULT NULL COMMENT '市',
  `county` 				varchar(20)  DEFAULT NULL COMMENT '区/县, 区域信息ID',
  `county_name` 		varchar(20)  DEFAULT NULL COMMENT '区/县',
  `town` 				varchar(20)  DEFAULT NULL COMMENT '乡/镇, 区域信息ID',
  `town_name` 			varchar(20)  DEFAULT NULL COMMENT '乡/镇',
  `receive_name` 		varchar(50)  DEFAULT NULL COMMENT '收货人姓名',
  `receive_phone` 		varchar(20)  DEFAULT NULL COMMENT '收货人手机',
  `receive_address` 	varchar(200) DEFAULT NULL COMMENT '收货地址',
  `status` 				varchar(1) 	DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by`           varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by`           varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`receiveaddress_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行 收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_bill_record
-- ----------------------------
DROP TABLE IF EXISTS `rzx_bill_record`;
CREATE TABLE `rzx_bill_record`  (
  `bill_record_id` 		varchar(32) NOT NULL,
  `bill_status` 		varchar(2)  DEFAULT NULL COMMENT '是否已开票(0-否 1-是)',
  `type` 				varchar(1)  DEFAULT NULL COMMENT '开票类型（0-个人 1-企业）',
  `up_name` 			varchar(80) DEFAULT NULL COMMENT '抬头名称',
  `email` 				varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `tax_number` 			varchar(64) DEFAULT NULL COMMENT '税号',
  `company_address` 	varchar(80) DEFAULT NULL COMMENT '单位地址',
  `company_phone` 		varchar(30) DEFAULT NULL COMMENT '公司电话号码',
  `open_bank` 			varchar(50) DEFAULT NULL COMMENT '开户银行',
  `account_bank` 		varchar(50) DEFAULT NULL COMMENT '银行账户',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by`           varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by`           varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`bill_record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行 开票信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_commodity_config
-- ----------------------------
DROP TABLE IF EXISTS `rzx_commodity_config`;
CREATE TABLE `rzx_commodity_config`  (
  `commodityconfig_id` 	varchar(32) NOT NULL,
  `commodity_code` 		varchar(32) DEFAULT NULL COMMENT '商品编码',
  `commodity_name` 		varchar(200) DEFAULT NULL COMMENT '商品名称',
  `brand` 				varchar(80) DEFAULT NULL COMMENT '品牌',
  `model` 				varchar(200) DEFAULT NULL COMMENT '商品型号',
  `thumbnail_image` 	text 		COMMENT '商品列表缩略图',
  `image` 				text 		COMMENT '商品详细图片',
  `image_urls` 			text 		COMMENT '商品轮播图片',
  `commodity_explain` 	text 		COMMENT '商品描述',
  `amount` 				varchar(16) DEFAULT NULL COMMENT '商品价格 (=成本价+成本价*溢价比例)',
  `provid` 				varchar(2)  DEFAULT NULL COMMENT '提供方（1-云中鹤，2-百汇）',
  `status` 				varchar(1) 	DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by`           varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by`           varchar(32) DEFAULT NULL COMMENT '更新人',
  `product_cate`		varchar(32) DEFAULT NULL COMMENT '商品分类',
  `product_place` 		varchar(80) DEFAULT NULL COMMENT '商品产地',
  `sale_status` 		varchar(20) DEFAULT NULL COMMENT '商品状态 1上架销售中, 0下架)',
  `market_price` 		varchar(16) DEFAULT NULL COMMENT '商品市场价',
  `retail_amount` 		varchar(16) DEFAULT NULL COMMENT '协议价格',
  `is_to_return` 		varchar(10) DEFAULT NULL COMMENT '是否支持7天无理由退货',
  `giftpackage_id` 		varchar(32) DEFAULT NULL COMMENT '所属礼包ID',
  `choose_flag` 		varchar(1)  DEFAULT NULL COMMENT '是否选品库商品(1-是)',
  `weight` 				varchar(20) DEFAULT NULL COMMENT '商品重量',
  `size` 				varchar(30) DEFAULT NULL COMMENT '商品尺寸',
  `type_id` 			varchar(2)  DEFAULT NULL COMMENT '商品属性 0-自营实物 1-电子卡券 2-直充话费 3-京东电商',
  `provid_source` 		varchar(20) DEFAULT NULL COMMENT '供应商商品来源 1：自营，2：卡券，3：直充，4：京东，5：严选，6：天猫   21:供应商',
  `rate` 				varchar(20) DEFAULT NULL COMMENT '溢价比例%',
  PRIMARY KEY (`commodityconfig_id`) USING BTREE,
  INDEX `giftpackage_id_index`(`giftpackage_id`) USING BTREE,
  INDEX `commodity_code_index`(`commodity_code`) USING BTREE,
  INDEX `commodity_name_index`(`commodity_name`) USING BTREE,
  INDEX `update_time_index`(`update_time`) 		 USING BTREE,
  INDEX `product_cate_provid_index`(`product_cate`, `provid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任智行 商品配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_commodity_class
-- ----------------------------
DROP TABLE IF EXISTS `rzx_commodity_class`;
CREATE TABLE `rzx_commodity_class`  (
  `commodityclass_id` 	varchar(32) NOT NULL,
  `code` 				varchar(20) DEFAULT NULL COMMENT '分类ID',
  `name` 				varchar(60) DEFAULT NULL COMMENT '分类名称',
  `level` 				varchar(10) DEFAULT NULL COMMENT '层级',
  `provid` 				varchar(2)  DEFAULT NULL COMMENT '提供方（1-云中鹤）',
  `parentid` 			varchar(20) DEFAULT NULL COMMENT '分类父ID',
  `status` 				varchar(1) 	DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by`           varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by`           varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`commodityclass_id`) USING BTREE,
  UNIQUE INDEX `provid`(`provid`, `code`) USING BTREE,
  INDEX `code_index`(`code`) USING BTREE,
  INDEX `parentid_index`(`parentid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行 商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_coupons_info
-- ----------------------------
DROP TABLE IF EXISTS `rzx_coupons_info`;
CREATE TABLE `rzx_coupons_info`  (
  `couponsinfo_id` 		varchar(32) NOT NULL,
  `batch_no` 			varchar(32) DEFAULT NULL COMMENT '券批次号',
  `coupons_pwd` 		varchar(32) DEFAULT NULL COMMENT '密码',
  `sale_status` 		varchar(2) DEFAULT NULL COMMENT '销售状态(0-未销售 1-已销售)',
  `check_status` 		varchar(2) DEFAULT NULL COMMENT '核销状态(0-未核销 1-已核销)',
  `check_time` 			varchar(30) DEFAULT NULL COMMENT '核销时间',
  `status` 				varchar(1) 	DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` 			varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` 			varchar(50) DEFAULT NULL COMMENT '更新人',
  `coupons_qrcode`  	varchar(200) DEFAULT NULL COMMENT '券号二维码',
  `in_source` 			varchar(1) DEFAULT NULL COMMENT '来源(1-任货行 2-任意行 3-任通行)',
  `type` 				varchar(1) DEFAULT NULL COMMENT '1-虚拟 2-实物',
  PRIMARY KEY (`couponsinfo_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行 券信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rzx_provid_address
-- ----------------------------
DROP TABLE IF EXISTS `rzx_provid_address`;
CREATE TABLE `rzx_provid_address`  (
  `providaddress_id` 	varchar(32) NOT NULL,
  `id` 					varchar(20) DEFAULT NULL COMMENT '地址ID',
  `name` 				varchar(40) DEFAULT NULL COMMENT '地址名称',
  `parent_id` 			varchar(20) DEFAULT NULL COMMENT '上级ID',
  `type` 				varchar(2) DEFAULT NULL COMMENT '地址类型1(省),2(市), 3(区/县), 4(乡/镇)',
  `provid` 				varchar(1) DEFAULT NULL COMMENT '供应商（1-云中鹤，2-百汇）',
  `status` 				varchar(1) DEFAULT '1' COMMENT '状态(1-有效 0-无效)',
  `create_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by`           varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` 		datetime 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by`           varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`providaddress_id`) USING BTREE,
  UNIQUE INDEX `id_provid`(`id`, `provid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任智行 供应商地址信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
