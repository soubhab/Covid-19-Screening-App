create database if not exists covid19;

CREATE TABLE `covid19`.`patient_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `dob` DATETIME NULL,
  `gender` VARCHAR(6) NULL,
  `addressLine1` VARCHAR(45) NULL,
  `addressLine2` VARCHAR(45) NULL,
  `addressLine3` VARCHAR(45) NULL,
  `pinNumber` VARCHAR(10) NULL,
  `phoneNo` VARCHAR(14) NULL,
  `emailId` VARCHAR(45) NULL,
  `adharCardNo` VARCHAR(20) NULL,
  `height` VARCHAR(10) NULL,
  `weight` VARCHAR(10) NULL,
  `password` VARCHAR(100) NULL,
  `secretQuestion` VARCHAR(100) NULL,
  `secretAnswer` VARCHAR(45) NULL,
  `createDateTime` DATETIME NULL,
  `lastUpdatedDateTime` DATETIME NULL,
  `consentText` VARCHAR(100) NULL,
  `DB_CHAR_1` VARCHAR(100) NULL,
  `DB_CHAR_2` VARCHAR(45) NULL,
  `DB_CHAR_3` VARCHAR(45) NULL,
  `DB_CHAR_4` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
 CREATE TABLE `covid19`.`patient_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patientId` INT NOT NULL,
  `doctorId` VARCHAR(45) NULL,
  `symptomDate` DATETIME NULL,
  `doctorConsultation` VARCHAR(45) NULL,
  `doctorSuggestion` VARCHAR(200) NULL,
  `covidTestDate` DATETIME NULL,
  `covidTestFileName` VARCHAR(45) NULL,
  `medicalHistory` VARCHAR(20) NULL,
  `currentMedication` VARCHAR(50) NULL,
  `smokingHabit` VARCHAR(5) NULL,
  `createDateTime` DATETIME NULL,
  `lastUpdatedDateTime` DATETIME NULL,
  `consentText` VARCHAR(100) NULL,
  `DB_CHAR_1` VARCHAR(100) NULL,
  `DB_CHAR_2` VARCHAR(45) NULL,
  `DB_CHAR_3` VARCHAR(45) NULL,
  `DB_CHAR_4` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `covid19`.`symptom_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patientId` INT NOT NULL,
  `patientData` INT NOT NULL,
  `feverTemparature` VARCHAR(8) NULL,
  `soreThroatStatus` VARCHAR(8) NULL,
  `bodyAcheStatus` VARCHAR(6) NULL,
  `smellStatus` VARCHAR(6) NULL,
  `tasteStatus` VARCHAR(6) NULL,
  `diarrheaStatus` VARCHAR(6) NULL,
  `chestXrayFileName` VARCHAR(50) NULL,
  `lastUpdatedDateTime` DATETIME NULL,
  `createDateTime` DATETIME NULL,
  `consentText` VARCHAR(100) NULL,
  `DB_CHAR_1` VARCHAR(100) NULL,
  `DB_CHAR_2` VARCHAR(45) NULL,
  `DB_CHAR_3` VARCHAR(45) NULL,
  `DB_CHAR_4` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `covid19`.`phone_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patientId` INT NOT NULL,
  `phone_name` VARCHAR(45) NULL,
  `device_model` VARCHAR(45) NULL,
  `os_name` VARCHAR(45) NULL,
  `os_version` VARCHAR(45) NULL,
  `createDateTime` DATETIME NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `covid19`.`spo2_device` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patientId` INT NOT NULL,
  `device_name` VARCHAR(45) NULL,
  `model_number` VARCHAR(45) NULL,
  `createDateTime` DATETIME NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `covid19`.`sample_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patientId` INT NOT NULL,
  `phoneId` INT NOT NULL,
  `coughSoundFile` VARCHAR(45) NULL,
  `spo2deviceId` INT NOT NULL,
  `spo2Value` VARCHAR(45) NULL,
  `puslseRate` VARCHAR(45) NULL,
  `createDateTime` DATETIME NULL,
  `lastUpdatedDateTime` DATETIME NULL,
  `consentText` VARCHAR(100) NULL,
  `DB_CHAR1` VARCHAR(45) NULL,
  `DB_CHAR2` VARCHAR(45) NULL,
  `DB_CHAR3` VARCHAR(45) NULL,
  `DB_CHAR4` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
  Commit; 