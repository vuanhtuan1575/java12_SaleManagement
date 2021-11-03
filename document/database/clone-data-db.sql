/*
 Navicat Premium Data Transfer

 Source Server         : Java12
 Source Server Type    : PostgreSQL
 Source Server Version : 140000
 Source Host           : 127.0.0.1:5432
 Source Catalog        : sale-management
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140000
 File Encoding         : 65001

 Date: 04/11/2021 00:35:58
*/


-- ----------------------------
-- Sequence structure for brand_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."brand_id_seq";
CREATE SEQUENCE "public"."brand_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."category_id_seq";
CREATE SEQUENCE "public"."category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_id_seq";
CREATE SEQUENCE "public"."product_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for table_product_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."table_product_user_id_seq";
CREATE SEQUENCE "public"."table_product_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for table_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."table_role_id_seq";
CREATE SEQUENCE "public"."table_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for table_sessionmanager_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."table_sessionmanager_id_seq";
CREATE SEQUENCE "public"."table_sessionmanager_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for table_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."table_user_id_seq";
CREATE SEQUENCE "public"."table_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for talbe_order_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."talbe_order_id_seq";
CREATE SEQUENCE "public"."talbe_order_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS "public"."brand";
CREATE TABLE "public"."brand" (
  "id" int8 NOT NULL DEFAULT nextval('brand_id_seq'::regclass),
  "create_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "brand_name" varchar(255) COLLATE "pg_catalog"."default",
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "url_image" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO "public"."brand" VALUES (1, '2021-11-03 19:06:30.03715', 'admin', NULL, NULL, 0, 'Apple', 'Apple Inc. là một tập đoàn công nghệ đa quốc gia của Mỹ có trụ sở chính tại Cupertino, California', 'https://upload.wikimedia.org/wikipedia/commons/thu…pple_logo_black.svg/80px-Apple_logo_black.svg.png');
INSERT INTO "public"."brand" VALUES (2, '2021-11-01 00:19:15', 'admin', NULL, NULL, 0, 'SamSung', 'Công ty chuyên sản xuất các sản phẩm thuộc lĩnh vực điện tử', 'https://cdn.icon-icons.com/icons2/2699/PNG/512/samsung_logo_icon_169775.png');
INSERT INTO "public"."brand" VALUES (3, '2021-11-01 00:19:17', 'admin', NULL, NULL, 0, 'Xiaomi', 'Xiaomi Inc. là một Tập đoàn sản xuất hàng điện tử Trung Quốc có trụ sở tại Thâm Quyến', 'https://cdn.icon-icons.com/icons2/2699/PNG/512/samsung_logo_icon_169775.png');
INSERT INTO "public"."brand" VALUES (4, '2021-11-01 00:24:36', 'admin', NULL, NULL, 0, 'LG', 'LG là một tập đoàn công nghiệp nặng đa quốc gia của Hàn Quốc', 'https://upload.wikimedia.org/wikipedia/commons/thu…o_%282015%29.svg/176px-LG_logo_%282015%29.svg.png');

-- ----------------------------
-- Table structure for brand_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."brand_category";
CREATE TABLE "public"."brand_category" (
  "brand_id" int8 NOT NULL,
  "category_id" int8 NOT NULL
)
;

-- ----------------------------
-- Records of brand_category
-- ----------------------------
INSERT INTO "public"."brand_category" VALUES (1, 1);
INSERT INTO "public"."brand_category" VALUES (1, 2);
INSERT INTO "public"."brand_category" VALUES (2, 2);
INSERT INTO "public"."brand_category" VALUES (3, 1);
INSERT INTO "public"."brand_category" VALUES (4, 1);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS "public"."category";
CREATE TABLE "public"."category" (
  "id" int8 NOT NULL DEFAULT nextval('category_id_seq'::regclass),
  "create_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "image_url" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO "public"."category" VALUES (2, '2021-11-03 23:49:37.21513', 'tuan', NULL, NULL, 0, 'Laptop description', 'https://cdn.tgdd.vn/Files/2021/03/05/1332738/dell_inspiron_15_7000_late_2020_1_1199x675-800-resize.jpg', 'May tinh xach tay');
INSERT INTO "public"."category" VALUES (1, '2021-11-03 19:06:23.768826', 'admin', NULL, NULL, 0, 'Điện thoại description', 'https://hc.com.vn/i/ecommerce/media/GS.006091_FEATURE_71013.jpg', 'Điện Thoại');

-- ----------------------------
-- Table structure for category_brand
-- ----------------------------
DROP TABLE IF EXISTS "public"."category_brand";
CREATE TABLE "public"."category_brand" (
  "category_id" int8 NOT NULL,
  "brand_id" int8 NOT NULL
)
;

-- ----------------------------
-- Records of category_brand
-- ----------------------------
INSERT INTO "public"."category_brand" VALUES (1, 1);
INSERT INTO "public"."category_brand" VALUES (1, 2);
INSERT INTO "public"."category_brand" VALUES (2, 2);
INSERT INTO "public"."category_brand" VALUES (2, 3);
INSERT INTO "public"."category_brand" VALUES (2, 4);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS "public"."product";
CREATE TABLE "public"."product" (
  "id" int8 NOT NULL DEFAULT nextval('product_id_seq'::regclass),
  "create_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "image_ulr" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "price" float8,
  "review" varchar(255) COLLATE "pg_catalog"."default",
  "trademark" varchar(255) COLLATE "pg_catalog"."default",
  "brand_id" int8 NOT NULL,
  "category_id" int8 NOT NULL
)
;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO "public"."product" VALUES (1, '2021-11-03 19:06:34.29723', 'tuan', '2021-11-03 23:40:27.882025', 'tuan', 2, 'Đánh giá iPhone 13 - Flagship được mong chờ năm 2021
Cuối năm 2020, bộ 4 iPhone 12 đã được ra mắt với nhiều cái tiến. Sau đó, mọi sự quan tâm lại đổ dồn vào sản phẩm tiếp theo – iPhone 13. Vậy iP 13 sẽ có những gì, hãy tìm hiểu ngay sau đây.', 'https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/1000x/040ec09b1e35df139433887a97daa66f/i/p/ip13-pro_2.jpg', 'iPhone 13 | Chính hãng VN/A', 100000, 'san pham tot', 'Apple', 1, 1);
INSERT INTO "public"."product" VALUES (2, '2021-11-02 00:31:22', 'admin', NULL, NULL, 1, 'Galaxy S21 Ultra với những cải tiến đáng kể về hiệu năng của máy cực kỳ mạnh mẽ', '	https://cdn.cellphones.com.vn/media/catalog/produc…f/6/3/637462630853935725_ss-s21-ultra-den-1_1.jpg', 'Samsung Galaxy S21 Ultra 5G', 500000, 'san pham tot', 'Samsung', 2, 1);

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_brand";
CREATE TABLE "public"."product_brand" (
  "product_id" int8 NOT NULL,
  "brand_id" int8 NOT NULL
)
;

-- ----------------------------
-- Records of product_brand
-- ----------------------------

-- ----------------------------
-- Table structure for product_order
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_order";
CREATE TABLE "public"."product_order" (
  "product_id" int8 NOT NULL,
  "order_id" int8 NOT NULL
)
;

-- ----------------------------
-- Records of product_order
-- ----------------------------

-- ----------------------------
-- Table structure for table_product_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."table_product_user";
CREATE TABLE "public"."table_product_user" (
  "id" int8 NOT NULL DEFAULT nextval('table_product_user_id_seq'::regclass),
  "quanty" int4 NOT NULL,
  "product_id" int8,
  "user_id" int8
)
;

-- ----------------------------
-- Records of table_product_user
-- ----------------------------
INSERT INTO "public"."table_product_user" VALUES (4, 1, 1, 1);

-- ----------------------------
-- Table structure for table_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."table_role";
CREATE TABLE "public"."table_role" (
  "id" int8 NOT NULL DEFAULT nextval('table_role_id_seq'::regclass),
  "create_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of table_role
-- ----------------------------
INSERT INTO "public"."table_role" VALUES (1, '2021-11-03 00:08:40', 'admin', NULL, NULL, 1, 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO "public"."table_role" VALUES (2, '2021-10-06 00:08:55', 'admin', NULL, NULL, 1, 'ROLE_USER', 'ROLE_USER');

-- ----------------------------
-- Table structure for table_sessionmanager
-- ----------------------------
DROP TABLE IF EXISTS "public"."table_sessionmanager";
CREATE TABLE "public"."table_sessionmanager" (
  "id" int8 NOT NULL DEFAULT nextval('table_sessionmanager_id_seq'::regclass),
  "create_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "expiration" int4,
  "refresh_token" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "token" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "tokentype" varchar(255) COLLATE "pg_catalog"."default",
  "username" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of table_sessionmanager
-- ----------------------------
INSERT INTO "public"."table_sessionmanager" VALUES (1, NULL, 'admin', NULL, 'admin', 0, 0, NULL, NULL, 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0dWFuIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE2MzU5NDExNzEsImV4cCI6MTYzNjAyNzU3MX0.30vnv5J3X3tEjSOuS0YPJEAijIQ_U8YzSdX-ibCqq6QQpJXk3TafFR9brOCOIq_EF6Ty3n4C8krJNivc0BjnOg', NULL, 'admin');

-- ----------------------------
-- Table structure for table_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."table_user";
CREATE TABLE "public"."table_user" (
  "id" int8 NOT NULL DEFAULT nextval('table_user_id_seq'::regclass),
  "create_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "username" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of table_user
-- ----------------------------
INSERT INTO "public"."table_user" VALUES (1, '2021-11-03 19:05:58.227438', 'admin', NULL, NULL, 0, 'tuan@1.ceo1mmmm23aa', NULL, '$2a$10$pcuN4FuGWKrpvdAHullGCekZJO3.hK/nKmDzdftLZsU2lC7bd3EDq', NULL, 'ACTIVE', 'tuan');
INSERT INTO "public"."table_user" VALUES (2, '2021-11-04 00:09:42.954311', 'admin', NULL, NULL, 0, 'khachhang@gmail.com', NULL, '$2a$10$O3R.gpLcrj9enxD2kAlIPuU5QarPdD/QfVhsZXWbBZatcvWQySN3q', NULL, 'ACTIVE', 'khachhang');

-- ----------------------------
-- Table structure for talbe_order
-- ----------------------------
DROP TABLE IF EXISTS "public"."talbe_order";
CREATE TABLE "public"."talbe_order" (
  "id" int8 NOT NULL DEFAULT nextval('talbe_order_id_seq'::regclass),
  "create_at" timestamp(6),
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "updated_at" timestamp(6),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL,
  "price" float8,
  "quantity" int4,
  "user_id" int8
)
;

-- ----------------------------
-- Records of talbe_order
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_role";
CREATE TABLE "public"."user_role" (
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL
)
;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO "public"."user_role" VALUES (1, 1);
INSERT INTO "public"."user_role" VALUES (2, 2);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."brand_id_seq"
OWNED BY "public"."brand"."id";
SELECT setval('"public"."brand_id_seq"', 8, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."category_id_seq"
OWNED BY "public"."category"."id";
SELECT setval('"public"."category_id_seq"', 3, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."product_id_seq"
OWNED BY "public"."product"."id";
SELECT setval('"public"."product_id_seq"', 5, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."table_product_user_id_seq"
OWNED BY "public"."table_product_user"."id";
SELECT setval('"public"."table_product_user_id_seq"', 5, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."table_role_id_seq"
OWNED BY "public"."table_role"."id";
SELECT setval('"public"."table_role_id_seq"', 3, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."table_sessionmanager_id_seq"
OWNED BY "public"."table_sessionmanager"."id";
SELECT setval('"public"."table_sessionmanager_id_seq"', 2, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."table_user_id_seq"
OWNED BY "public"."table_user"."id";
SELECT setval('"public"."table_user_id_seq"', 3, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."talbe_order_id_seq"
OWNED BY "public"."talbe_order"."id";
SELECT setval('"public"."talbe_order_id_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table brand
-- ----------------------------
ALTER TABLE "public"."brand" ADD CONSTRAINT "brand_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table brand_category
-- ----------------------------
ALTER TABLE "public"."brand_category" ADD CONSTRAINT "brand_category_pkey" PRIMARY KEY ("brand_id", "category_id");

-- ----------------------------
-- Primary Key structure for table category
-- ----------------------------
ALTER TABLE "public"."category" ADD CONSTRAINT "category_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table product
-- ----------------------------
ALTER TABLE "public"."product" ADD CONSTRAINT "uk_jmivyxk9rmgysrmsqw15lqr5b" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table product
-- ----------------------------
ALTER TABLE "public"."product" ADD CONSTRAINT "product_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_brand
-- ----------------------------
ALTER TABLE "public"."product_brand" ADD CONSTRAINT "product_brand_pkey" PRIMARY KEY ("product_id", "brand_id");

-- ----------------------------
-- Primary Key structure for table product_order
-- ----------------------------
ALTER TABLE "public"."product_order" ADD CONSTRAINT "product_order_pkey" PRIMARY KEY ("product_id", "order_id");

-- ----------------------------
-- Primary Key structure for table table_product_user
-- ----------------------------
ALTER TABLE "public"."table_product_user" ADD CONSTRAINT "table_product_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table table_role
-- ----------------------------
ALTER TABLE "public"."table_role" ADD CONSTRAINT "uk_qfxpkqp2mjxbtj02s1twr6gbf" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table table_role
-- ----------------------------
ALTER TABLE "public"."table_role" ADD CONSTRAINT "table_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table table_sessionmanager
-- ----------------------------
ALTER TABLE "public"."table_sessionmanager" ADD CONSTRAINT "table_sessionmanager_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table table_user
-- ----------------------------
ALTER TABLE "public"."table_user" ADD CONSTRAINT "uk_5r6j19hclnw5vcv8sqrnwuyx2" UNIQUE ("email");

-- ----------------------------
-- Primary Key structure for table table_user
-- ----------------------------
ALTER TABLE "public"."table_user" ADD CONSTRAINT "table_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table talbe_order
-- ----------------------------
ALTER TABLE "public"."talbe_order" ADD CONSTRAINT "talbe_order_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_role
-- ----------------------------
ALTER TABLE "public"."user_role" ADD CONSTRAINT "user_role_pkey" PRIMARY KEY ("user_id", "role_id");

-- ----------------------------
-- Foreign Keys structure for table brand_category
-- ----------------------------
ALTER TABLE "public"."brand_category" ADD CONSTRAINT "fki387ilww5ia7cy23wixj40q1q" FOREIGN KEY ("brand_id") REFERENCES "public"."brand" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."brand_category" ADD CONSTRAINT "fkn0e8s5bnd26hjohyh5xurwar2" FOREIGN KEY ("category_id") REFERENCES "public"."category" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table product
-- ----------------------------
ALTER TABLE "public"."product" ADD CONSTRAINT "fk1mtsbur82frn64de7balymq9s" FOREIGN KEY ("category_id") REFERENCES "public"."category" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."product" ADD CONSTRAINT "fks6cydsualtsrprvlf2bb3lcam" FOREIGN KEY ("brand_id") REFERENCES "public"."brand" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table product_order
-- ----------------------------
ALTER TABLE "public"."product_order" ADD CONSTRAINT "fk49fhw9f9o1ohd8bjghrnyi2bk" FOREIGN KEY ("order_id") REFERENCES "public"."talbe_order" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."product_order" ADD CONSTRAINT "fkh73acsd9s5wp6l0e55td6jr1m" FOREIGN KEY ("product_id") REFERENCES "public"."product" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table table_product_user
-- ----------------------------
ALTER TABLE "public"."table_product_user" ADD CONSTRAINT "fk6dwtn9eoh5w2dnevtgp4ho3bx" FOREIGN KEY ("product_id") REFERENCES "public"."product" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."table_product_user" ADD CONSTRAINT "fk8on3s2rl8dmm92am4f86ets4i" FOREIGN KEY ("user_id") REFERENCES "public"."table_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table talbe_order
-- ----------------------------
ALTER TABLE "public"."talbe_order" ADD CONSTRAINT "fk7o29rs9urrxudqvcjecfqqig7" FOREIGN KEY ("user_id") REFERENCES "public"."table_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table user_role
-- ----------------------------
ALTER TABLE "public"."user_role" ADD CONSTRAINT "fk7a88mhqu7mjxhl5tkvtq5c464" FOREIGN KEY ("role_id") REFERENCES "public"."table_role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."user_role" ADD CONSTRAINT "fkbameqy6jw78u5bu36ad28p87o" FOREIGN KEY ("user_id") REFERENCES "public"."table_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
