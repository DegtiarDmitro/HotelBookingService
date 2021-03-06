--
-- table `USER`
--
CREATE TABLE IF NOT EXISTS `USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `USERNAME` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `ROLE` varchar(45) DEFAULT NULL
);


--
-- table `ADDITIONAL_SERVICE`
--
CREATE TABLE IF NOT EXISTS `ADDITIONAL_SERVICE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(45) DEFAULT NULL,
  `PRICE` varchar(45) DEFAULT NULL
);

--
-- table `HOTEL`
--
CREATE TABLE IF NOT EXISTS `HOTEL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(45) DEFAULT NULL,
  `ADDRESS` varchar(45) DEFAULT NULL,
  `PHONE` varchar(50) NOT NULL
);


--
-- table `ROOM_CATEGORY`
--
CREATE TABLE IF NOT EXISTS `ROOM_CATEGORY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(45) DEFAULT NULL,
  `DESCRIPTION` text
);


--
-- table `ROOM`
--
CREATE TABLE IF NOT EXISTS `ROOM` (
  `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NUMBER` int(11) NOT NULL,
  `ROOM_CATEGORY_ID` int(11) NOT NULL,
  `PRICE` float NOT NULL,
  `HOTEL_ID` int(11) NOT NULL,
  CONSTRAINT "ROOM_HOTEL_ID_FKEY" FOREIGN KEY ("HOTEL_ID") REFERENCES "HOTEL" ("ID"),
  CONSTRAINT "ROOM_ROOM_CATEGORY_ID_FKEY" FOREIGN KEY ("ROOM_CATEGORY_ID") REFERENCES "ROOM_CATEGORY" ("ID")
);

--
-- table `ROOM_ADDITIONAL_SERVICE`
--
CREATE TABLE IF NOT EXISTS `ROOM_ADDITIONAL_SERVICE` (
  `ROOM_ID` int(11) NOT NULL,
  `ADDITIONAL_SERVICE_ID` int(11) NOT NULL,
  CONSTRAINT "ROOM_ADDITIONAL_SERVICE_ROOM_ID_FKEY" FOREIGN KEY ("ROOM_ID") REFERENCES "ROOM" ("ID"),
  CONSTRAINT "ROOM_ADDITIONAL_SERVICE_ID_FKEY" FOREIGN KEY ("ADDITIONAL_SERVICE_ID") REFERENCES "ADDITIONAL_SERVICE" ("ID")
);

--
-- table `BOOKING`
--
CREATE TABLE IF NOT EXISTS `BOOKING` (
  `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `START_DATE` varchar(45) DEFAULT NULL,
  `END_DATE` varchar(45) DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  `ROOM_ID` int(11) NOT NULL,
  CONSTRAINT "BOOKING_USER_ID_FKEY" FOREIGN KEY ("USER_ID") REFERENCES "USER" ("ID"),
  CONSTRAINT "BOOKING_ROOM_ID_FKEY" FOREIGN KEY ("ROOM_ID") REFERENCES "ROOM" ("ID")
);


--
-- insert into `ADDITIONAL_SERVICE`
--
INSERT INTO `ADDITIONAL_SERVICE` (`ID`, `NAME`, `PRICE`) VALUES
  (1, 'breakfast', '40.5'),
  (2, 'cleaning', '20');

--
-- insert into `HOTEL`
--
INSERT INTO `HOTEL` (`ID`, `NAME`, `ADDRESS`, `PHONE`) VALUES
  (1, 'Premier Hotel', 'City, street 5', '222-222-22');

--
-- insert into `ROOM_CATEGORY`
--
INSERT INTO `ROOM_CATEGORY` (`ID`, `NAME`, `DESCRIPTION`) VALUES
  (1, 'STD', NULL),
  (2, 'BDR', NULL);

--
-- insert into `USER`
--
INSERT INTO `USER` (`ID`, `USERNAME`, `EMAIL`, `PASSWORD`, `ROLE`) VALUES
  (1, 'user1', 'user1@gmail.com', '111111', 'CLIENT');

--
-- insert into `ROOM`
--
INSERT INTO `ROOM` (`ID`, `NUMBER`, `ROOM_CATEGORY_ID`, `PRICE`, `HOTEL_ID`) VALUES
  (1, 48, 1, 120, 1),
  (2, 50, 2, 150, 1);

--
-- insert into `ROOM_ADDITIONAL_SERVICE`
--
INSERT INTO `ROOM_ADDITIONAL_SERVICE` (`ROOM_ID`, `ADDITIONAL_SERVICE_ID`) VALUES
  (2, 1),
  (2, 2);
