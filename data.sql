create table PRODUCT
(
    ID       INT,
    BRAND    VARCHAR2(20),
    NAME     VARCHAR2(50),
    PRICE    FLOAT,
    QUANTITY INT
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/product.csv');

create table COOLER
(
    ID             INT auto_increment,
    TIER           INT,
    NOISE_LEVEL_DB FLOAT,
    IS_AIR         BOOLEAN,
    IS_WORKSTATION BOOLEAN,
    PRODUCT_FK     INT,
    constraint COOLER_PK
        primary key (ID),
    constraint COOLER_PRODUCT_ID_FK
        foreign key (PRODUCT_FK) references PRODUCT (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/cooler.csv');

create table CPU
(
    ID             INT auto_increment,
    SOCKET         VARCHAR2(10),
    CORES          INT,
    SMT            BOOLEAN,
    INTEGRATED_GPU BOOLEAN,
    TDP_W          INT,
    ST_PREF        INT,
    MT_PREF        INT,
    CORE_CLOCK     FLOAT,
    BOOST_CLOCK    FLOAT,
    PRODUCT_FK     INT,
    constraint CPU_PK
        primary key (ID),
    constraint CPU_PRODUCT_ID_FK
        foreign key (PRODUCT_FK) references PRODUCT (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/cpu.csv');

create table MOTHERBOARD
(
    ID            INT auto_increment,
    TIER          FLOAT,
    CHIPSET       VARCHAR2(10),
    SOCKET        VARCHAR2(10),
    FORM_FACTOR   VARCHAR2(10),
    MEMORY_SLOTS  INT,
    MEMORY_MAX_GB INT,
    PRODUCT_FK    INT,
    constraint MOTHERBOARD_PK
        primary key (ID),
    constraint MOTHERBOARD_PRODUCT_ID_FK
        foreign key (PRODUCT_FK) references PRODUCT (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/motherboard.csv');

create table RAM
(
    ID                 INT auto_increment,
    SPEED              INT,
    MODULES_COUNT      INT,
    MODULE_CAPACITY_GB INT,
    FW_LATENCY_NS      FLOAT,
    CL                 INT,
    PRODUCT_FK         INT,
    constraint RAM_PK
        primary key (ID),
    constraint RAM_PRODUCT_ID_FK
        foreign key (PRODUCT_FK) references PRODUCT (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/ram.csv');

create table STORAGE
(
    ID          INT auto_increment,
    CAPACITY_GB INT,
    TIER        INT,
    TYPE        VARCHAR2(10),
    FORM_FACTOR VARCHAR2(10),
    INTERFACE   VARCHAR2(20),
    PRODUCT_FK  INT,
    constraint STORAGE_PK
        primary key (ID),
    constraint STORAGE_PRODUCT_ID_FK
        foreign key (PRODUCT_FK) references PRODUCT (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/storage.csv');

create table GPU
(
    ID                    INT auto_increment,
    CHIPSET               VARCHAR2(30),
    MEMORY_GB             INT,
    CORE_CLOCK_MHZ        INT,
    BOOST_CLOCK_MHZ       INT,
    LENGHT_MM             FLOAT,
    TDP                   INT,
    RECOMMENDED_PSU_WATTS INT,
    PERFORMANCE           INT,
    PRODUCT_FK            INT,
    constraint GPU_PK
        primary key (ID),
    constraint GPU_PRODUCT_ID_FK
        foreign key (PRODUCT_FK) references PRODUCT (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/gpu.csv');

create table CASE_
(
    ID                    INT auto_increment,
    POWER_SUPPLY_STANDARD VARCHAR2(5),
    MAX_MOTHERBOARD_SIZE  VARCHAR2(10),
    TYPE                  VARCHAR2(20),
    SIDE_PANEL_WINDOW     VARCHAR2(30),
    PRODUCT_FK            INT,
    constraint CASE__PK
        primary key (ID),
    constraint CASE__PRODUCT_ID_FK
        foreign key (PRODUCT_FK) references PRODUCT (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/case.csv');

create table PSU
(
    ID                INT auto_increment,
    TIER              FLOAT,
    FORM_FACTOR       VARCHAR2(5),
    EFFICIENCY_RATING VARCHAR2(20),
    WATTAGE           INT,
    MODULAR           VARCHAR2(10),
    PRODUCT_FK        INT,
    constraint PSU_PK
        primary key (ID),
    constraint PSU_PRODUCT_ID_FK
        foreign key (PRODUCT_FK) references PRODUCT (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/psu.csv');

create table USER
(
    ID       INT auto_increment,
    EMAIL    VARCHAR2(100),
    USERNAME VARCHAR2(50),
    PASSWORD VARCHAR2(50),
    constraint USER_PK
        primary key (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/user.csv');

create table "order"
(
    ID      INT auto_increment,
    DATE    DATE,
    USER_ID INT,
    constraint ORDER_PK
        primary key (ID),
    constraint ORDER_USER_ID_FK
        foreign key (USER_ID) references USER (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/order.csv');

create table ORDERLIST
(
    ID         INT auto_increment,
    DATE       DATE,
    PRODUCT_ID INT,
    ORDER_ID   INT,
    QUANTITY   INT,
    constraint ORDERLIST_PK
        primary key (ID),
    constraint ORDERLIST_ORDER_ID_FK
        foreign key (ORDER_ID) references "order" (ID),
    constraint ORDERLIST_PRODUCT_ID_FK
        foreign key (PRODUCT_ID) references PRODUCT (ID)
)AS SELECT * FROM CSVREAD('C:/Users/szust/Desktop/csv/orderlist.csv');