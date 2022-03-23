/*
Navicat MySQL Data Transfer

Source Server         : abc
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : final_term_proj

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2019-12-08 06:54:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=euckr;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', 'Libya');
INSERT INTO `address` VALUES ('2', 'Egypt');
INSERT INTO `address` VALUES ('3', 'Tunisia');
INSERT INTO `address` VALUES ('4', 'Algeria');
INSERT INTO `address` VALUES ('5', 'Morocco');
INSERT INTO `address` VALUES ('6', 'Western Sahara');
INSERT INTO `address` VALUES ('7', 'Mauritania');
INSERT INTO `address` VALUES ('8', 'Senegal');
INSERT INTO `address` VALUES ('9', 'The Gambia');
INSERT INTO `address` VALUES ('10', 'Guinea-Bissau');
INSERT INTO `address` VALUES ('11', 'Guinea');
INSERT INTO `address` VALUES ('12', 'Sierra Leone');
INSERT INTO `address` VALUES ('13', 'Liberia');
INSERT INTO `address` VALUES ('14', 'Cote dlvoire');
INSERT INTO `address` VALUES ('15', 'Burkina Faso');
INSERT INTO `address` VALUES ('16', 'Benin');
INSERT INTO `address` VALUES ('17', 'Togo');
INSERT INTO `address` VALUES ('18', 'Mali');
INSERT INTO `address` VALUES ('19', 'Algeria');
INSERT INTO `address` VALUES ('20', 'Niger');
INSERT INTO `address` VALUES ('21', 'Libya');
INSERT INTO `address` VALUES ('22', 'Sudan');
INSERT INTO `address` VALUES ('23', 'Eritrea');
INSERT INTO `address` VALUES ('24', 'Chad');
INSERT INTO `address` VALUES ('25', 'Ethiopia');
INSERT INTO `address` VALUES ('26', 'Somalia');
INSERT INTO `address` VALUES ('27', 'Kenya');
INSERT INTO `address` VALUES ('28', 'Uganda');
INSERT INTO `address` VALUES ('29', 'Tanzania');
INSERT INTO `address` VALUES ('30', 'Malawi');
INSERT INTO `address` VALUES ('31', 'Mozambique');
INSERT INTO `address` VALUES ('32', 'Madagascar');
INSERT INTO `address` VALUES ('33', 'Zimbabwe');
INSERT INTO `address` VALUES ('34', 'Botswana');
INSERT INTO `address` VALUES ('35', 'Pretoria');
INSERT INTO `address` VALUES ('36', 'Eswatini');
INSERT INTO `address` VALUES ('37', 'Lesotho');
INSERT INTO `address` VALUES ('38', 'Durban');
INSERT INTO `address` VALUES ('39', 'South Africa');
INSERT INTO `address` VALUES ('40', 'Cape Town');
INSERT INTO `address` VALUES ('41', 'Namibia');
INSERT INTO `address` VALUES ('42', 'Angola');
INSERT INTO `address` VALUES ('43', 'DRC');
INSERT INTO `address` VALUES ('44', 'Rwanda Burundi');
INSERT INTO `address` VALUES ('45', 'CAR');
INSERT INTO `address` VALUES ('46', 'Cameroon');
INSERT INTO `address` VALUES ('47', 'Lagos');
INSERT INTO `address` VALUES ('48', 'Yeoncheon');
INSERT INTO `address` VALUES ('49', 'Cheolwon');
INSERT INTO `address` VALUES ('50', 'Sokcho');
INSERT INTO `address` VALUES ('51', 'YangYang');
INSERT INTO `address` VALUES ('52', 'Gimpo');
INSERT INTO `address` VALUES ('53', 'Seoul');
INSERT INTO `address` VALUES ('54', 'Gangneung');
INSERT INTO `address` VALUES ('55', 'Taebaek');
INSERT INTO `address` VALUES ('56', 'Incheon');
INSERT INTO `address` VALUES ('57', 'Yongin');
INSERT INTO `address` VALUES ('58', 'Wonju');
INSERT INTO `address` VALUES ('59', 'Yeongju');
INSERT INTO `address` VALUES ('60', 'Uljin');
INSERT INTO `address` VALUES ('61', 'Goesan');
INSERT INTO `address` VALUES ('62', 'Andong');
INSERT INTO `address` VALUES ('63', 'Daejeon');
INSERT INTO `address` VALUES ('64', 'Gongju');
INSERT INTO `address` VALUES ('65', 'Jeonju');
INSERT INTO `address` VALUES ('66', 'Daegu');
INSERT INTO `address` VALUES ('67', 'Pohang');
INSERT INTO `address` VALUES ('68', 'Ulsan');
INSERT INTO `address` VALUES ('69', 'Cheongdo');
INSERT INTO `address` VALUES ('70', 'Gimhae');
INSERT INTO `address` VALUES ('71', 'Busan');
INSERT INTO `address` VALUES ('72', 'Gurye');
INSERT INTO `address` VALUES ('73', 'Gwangyang');
INSERT INTO `address` VALUES ('74', 'Yeosu');
INSERT INTO `address` VALUES ('75', 'Goheung');
INSERT INTO `address` VALUES ('76', 'Gwangju');
INSERT INTO `address` VALUES ('77', 'Hampyeong');
INSERT INTO `address` VALUES ('78', 'Sinan');
INSERT INTO `address` VALUES ('79', 'Jindo');

-- ----------------------------
-- Table structure for `children`
-- ----------------------------
DROP TABLE IF EXISTS `children`;
CREATE TABLE `children` (
  `children_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `age` char(3) NOT NULL,
  `sex` char(1) NOT NULL,
  `interest_id` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`children_id`),
  KEY `interest_id` (`interest_id`),
  KEY `address_id` (`address_id`),
  CONSTRAINT `children_ibfk_2` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `children_ibfk_1` FOREIGN KEY (`interest_id`) REFERENCES `interest` (`interest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=euckr;

-- ----------------------------
-- Records of children
-- ----------------------------
INSERT INTO `children` VALUES ('1', 'Tekamo', '7', 'F', '3', '1');
INSERT INTO `children` VALUES ('2', 'Simen', '7', 'F', '3', '3');
INSERT INTO `children` VALUES ('3', 'Kuna', '16', 'F', '2', '16');
INSERT INTO `children` VALUES ('4', 'Haila', '13', 'M', '7', '22');
INSERT INTO `children` VALUES ('5', 'Kurama', '12', 'M', '11', '41');
INSERT INTO `children` VALUES ('6', 'Mumuni', '12', 'F', '9', '17');
INSERT INTO `children` VALUES ('7', 'Hamadu', '7', 'M', '13', '9');
INSERT INTO `children` VALUES ('8', 'Giba', '6', 'M', '22', '3');
INSERT INTO `children` VALUES ('9', 'Balral', '7', 'F', '19', '3');
INSERT INTO `children` VALUES ('10', 'Maru', '8', 'M', '27', '16');
INSERT INTO `children` VALUES ('11', 'Apisu', '8', 'F', '21', '33');
INSERT INTO `children` VALUES ('12', 'Adamu', '6', 'M', '30', '37');
INSERT INTO `children` VALUES ('13', 'Mahamadu', '11', 'M', '20', '39');
INSERT INTO `children` VALUES ('14', 'Flan', '11', 'M', '3', '39');
INSERT INTO `children` VALUES ('15', 'Ampia', '16', 'F', '8', '39');
INSERT INTO `children` VALUES ('16', 'Kuruba', '17', 'M', '7', '39');
INSERT INTO `children` VALUES ('17', 'Haradi', '9', 'F', '16', '37');
INSERT INTO `children` VALUES ('18', 'Pakoni', '9', 'F', '16', '37');
INSERT INTO `children` VALUES ('19', 'Hallima', '10', 'M', '30', '16');
INSERT INTO `children` VALUES ('20', 'Erald', '15', 'M', '13', '11');

-- ----------------------------
-- Table structure for `institution`
-- ----------------------------
DROP TABLE IF EXISTS `institution`;
CREATE TABLE `institution` (
  `institution_id` int(11) NOT NULL AUTO_INCREMENT,
  `institution_name` varchar(20) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`institution_id`),
  KEY `address_id` (`address_id`),
  CONSTRAINT `institution_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=euckr;

-- ----------------------------
-- Records of institution
-- ----------------------------
INSERT INTO `institution` VALUES ('1', '유니세프연천', '070-817-1510', '48');
INSERT INTO `institution` VALUES ('2', '유니세프철원', '070-357-1510', '49');
INSERT INTO `institution` VALUES ('3', '유니세프속초', '070-647-1510', '50');
INSERT INTO `institution` VALUES ('4', '유니세프양양', '070-354-1510', '51');
INSERT INTO `institution` VALUES ('5', '유니세프김포', '070-154-1510', '52');
INSERT INTO `institution` VALUES ('6', '유니세프서울', '070-185-1510', '53');
INSERT INTO `institution` VALUES ('7', '유니세프강릉', '070-917-1510', '54');
INSERT INTO `institution` VALUES ('8', '유니세프인천', '070-225-1510', '56');
INSERT INTO `institution` VALUES ('9', '유니세프용인', '070-666-1510', '57');
INSERT INTO `institution` VALUES ('10', '유니세프원주', '070-147-1510', '58');
INSERT INTO `institution` VALUES ('11', '유니세프태백', '070-159-1510', '55');
INSERT INTO `institution` VALUES ('12', '유니세프영주', '070-753-1510', '59');
INSERT INTO `institution` VALUES ('13', '유니세프울진', '070-456-1510', '60');
INSERT INTO `institution` VALUES ('14', '유니세프안동', '070-332-1510', '62');
INSERT INTO `institution` VALUES ('15', '유니세프괴산', '070-048-1510', '61');
INSERT INTO `institution` VALUES ('16', '유니세프대전', '070-098-1510', '63');
INSERT INTO `institution` VALUES ('17', '유니세프공주', '070-688-1510', '64');
INSERT INTO `institution` VALUES ('18', '유니세프대구', '070-988-1510', '66');
INSERT INTO `institution` VALUES ('19', '유니세프포항', '070-158-1510', '67');
INSERT INTO `institution` VALUES ('20', '유니세프전주', '070-157-1510', '65');
INSERT INTO `institution` VALUES ('21', '유니세프청도', '070-315-1510', '69');
INSERT INTO `institution` VALUES ('22', '유니세프울산', '070-316-1510', '68');
INSERT INTO `institution` VALUES ('23', '유니세프김해', '070-318-1510', '70');
INSERT INTO `institution` VALUES ('24', '유니세프부산', '070-415-1510', '71');
INSERT INTO `institution` VALUES ('25', '유니세프구례', '070-416-1510', '72');
INSERT INTO `institution` VALUES ('26', '유니세프광양', '070-715-1510', '73');
INSERT INTO `institution` VALUES ('27', '유니세프여수', '070-716-1510', '74');
INSERT INTO `institution` VALUES ('28', '유니세프광주', '070-815-1510', '76');
INSERT INTO `institution` VALUES ('29', '유니세프함평', '070-816-1510', '77');
INSERT INTO `institution` VALUES ('30', '유니세프고흥', '070-870-1510', '75');
INSERT INTO `institution` VALUES ('31', '유니세프신안', '070-910-1510', '78');
INSERT INTO `institution` VALUES ('32', '유니세프진도', '070-130-1510', '79');

-- ----------------------------
-- Table structure for `interest`
-- ----------------------------
DROP TABLE IF EXISTS `interest`;
CREATE TABLE `interest` (
  `interest_id` int(11) NOT NULL AUTO_INCREMENT,
  `interest` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`interest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=euckr;

-- ----------------------------
-- Records of interest
-- ----------------------------
INSERT INTO `interest` VALUES ('1', 'fishing');
INSERT INTO `interest` VALUES ('2', 'acting');
INSERT INTO `interest` VALUES ('3', 'dancing');
INSERT INTO `interest` VALUES ('4', 'singing');
INSERT INTO `interest` VALUES ('5', 'kayaking');
INSERT INTO `interest` VALUES ('6', 'yachting');
INSERT INTO `interest` VALUES ('7', 'collecting books');
INSERT INTO `interest` VALUES ('8', 'reading books');
INSERT INTO `interest` VALUES ('9', 'soccer');
INSERT INTO `interest` VALUES ('10', 'baseball');
INSERT INTO `interest` VALUES ('11', 'basketball');
INSERT INTO `interest` VALUES ('12', 'football');
INSERT INTO `interest` VALUES ('13', 'table tenis');
INSERT INTO `interest` VALUES ('14', 'bedminton');
INSERT INTO `interest` VALUES ('15', 'handball');
INSERT INTO `interest` VALUES ('16', 'swimming');
INSERT INTO `interest` VALUES ('17', 'drawing');
INSERT INTO `interest` VALUES ('18', 'game');
INSERT INTO `interest` VALUES ('19', 'listening music');
INSERT INTO `interest` VALUES ('20', 'hide and sick');
INSERT INTO `interest` VALUES ('21', 'guitar');
INSERT INTO `interest` VALUES ('22', 'drum');
INSERT INTO `interest` VALUES ('23', 'kalimba');
INSERT INTO `interest` VALUES ('24', 'cycling');
INSERT INTO `interest` VALUES ('25', 'marathon');
INSERT INTO `interest` VALUES ('26', 'running');
INSERT INTO `interest` VALUES ('27', 'computer');
INSERT INTO `interest` VALUES ('28', 'take a picture');
INSERT INTO `interest` VALUES ('29', 'drawing landscape');
INSERT INTO `interest` VALUES ('30', 'collecting');

-- ----------------------------
-- Table structure for `sponsor`
-- ----------------------------
DROP TABLE IF EXISTS `sponsor`;
CREATE TABLE `sponsor` (
  `sponsor_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `age` char(3) NOT NULL,
  `sex` char(1) NOT NULL,
  `mail_address` varchar(20) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `donated_money` int(11) DEFAULT NULL,
  `children_id` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `institution_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sponsor_id`),
  KEY `children_id` (`children_id`),
  KEY `address_id` (`address_id`),
  KEY `institution_id` (`institution_id`),
  CONSTRAINT `sponsor_ibfk_3` FOREIGN KEY (`institution_id`) REFERENCES `institution` (`institution_id`),
  CONSTRAINT `sponsor_ibfk_1` FOREIGN KEY (`children_id`) REFERENCES `children` (`children_id`),
  CONSTRAINT `sponsor_ibfk_2` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=euckr;

-- ----------------------------
-- Records of sponsor
-- ----------------------------
INSERT INTO `sponsor` VALUES ('1', '오동익', '35', 'M', 'doh@sch.ac.kr', '2012-03-21', '9999-12-31', '010-1234-5678', '동익', '6780000', '3', '54', '17');
INSERT INTO `sponsor` VALUES ('2', '민세동', '31', 'M', 'msd@sch.ac.kr', '2015-04-02', '9999-12-31', '010-1234-5678', '세동', '3150000', '4', '57', '3');
INSERT INTO `sponsor` VALUES ('3', '안재억', '38', 'M', 'aju@sch.ac.kr', '2016-09-21', '2018-11-21', '010-1234-5678', '재억', '1570000', '2', '68', '8');
INSERT INTO `sponsor` VALUES ('4', '이언석', '29', 'M', 'lus@sch.ac.kr', '2017-12-11', '9999-12-31', '010-1234-5678', '언석', '5000000', '7', '69', '9');
INSERT INTO `sponsor` VALUES ('5', '호종갑', '23', 'M', 'hjg@sch.ac.kr', '2017-12-13', '2018-12-18', '010-1234-5678', '종갑', '1378000', '15', '73', '10');
INSERT INTO `sponsor` VALUES ('6', '공용해', '36', 'M', 'gyh@sch.ac.kr', '2016-05-03', '2019-11-13', '010-1234-5678', '용해', '4800000', '13', '72', '16');
INSERT INTO `sponsor` VALUES ('7', '김리원', '21', 'F', 'krw@sch.ac.kr', '2015-03-22', '9999-12-31', '010-1234-5678', '리원', '350000', '8', '75', '15');
INSERT INTO `sponsor` VALUES ('8', '김민지', '21', 'F', 'kmj@sch.ac.kr', '2015-04-28', '9999-12-31', '010-1234-5678', '민지', '189000', '9', '76', '4');
INSERT INTO `sponsor` VALUES ('9', '김범준', '21', 'M', 'mbj@sch.ac.kr', '2013-06-11', '2014-03-22', '010-1234-5678', '범준', '164000', '1', '77', '6');
INSERT INTO `sponsor` VALUES ('10', '김선우', '21', 'M', 'ksw@sch.ac.kr', '2017-05-29', '9999-12-31', '010-1234-5678', '선우', '132000', '5', '58', '6');
INSERT INTO `sponsor` VALUES ('11', '김소연', '21', 'F', 'ksy@sch.ac.kr', '2013-11-12', '9999-12-31', '010-1234-5678', '소연', '950000', '16', '61', '12');
INSERT INTO `sponsor` VALUES ('12', '김재영', '17', 'M', 'kjy@sch.ac.kr', '2012-04-07', '9999-12-31', '010-1234-5678', '재영', '450000', '6', '67', '9');
INSERT INTO `sponsor` VALUES ('13', '김한이', '17', 'F', 'khe@sch.ac.kr', '2013-06-07', '9999-12-31', '010-1234-5678', '한이', '650000', '10', '53', '20');
INSERT INTO `sponsor` VALUES ('14', '김혜인', '16', 'F', 'khi@sch.ac.kr', '2016-11-13', '2017-12-14', '010-1234-5678', '혜인', '142000', '19', '54', '16');
INSERT INTO `sponsor` VALUES ('15', '나승준', '13', 'M', 'nsj@sch.ac.kr', '2017-08-22', '2018-09-17', '010-1234-5678', '승준', '370000', '11', '55', '23');
INSERT INTO `sponsor` VALUES ('16', '강재영', '14', 'M', 'kjy@sch.ac.kr', '2017-07-09', '9999-12-31', '010-1234-5678', '재영', '850000', '18', '56', '27');
INSERT INTO `sponsor` VALUES ('17', '이승호', '17', 'M', 'lsh@sch.ac.kr', '2014-05-04', '2016-03-22', '010-1234-5678', '승호', '945000', '17', '57', '26');
INSERT INTO `sponsor` VALUES ('18', '류소현', '21', 'F', 'rsh@sch.ac.kr', '2014-09-19', '9999-12-31', '010-1234-5678', '소현', '314000', '12', '59', '28');
INSERT INTO `sponsor` VALUES ('19', '민선홍', '23', 'M', 'msh@sch.ac.kr', '2018-03-18', '9999-12-31', '010-1234-5678', '선홍', '658000', '14', '60', '29');
INSERT INTO `sponsor` VALUES ('20', '문보미', '27', 'F', 'mbm@sch.ac.kr', '2019-01-03', '2019-10-04', '010-1234-5678', '보미', '947000', '20', '58', '31');
