CREATE DATABASE IF NOT EXISTS `test`;

USE `test`;

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(250) NOT NULL COLLATE 'utf8_unicode_ci',
	`author` VARCHAR(250) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`description` TEXT NULL COLLATE 'utf8_unicode_ci',
	`isbn` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`printYear` YEAR NULL DEFAULT NULL,
	`readAlready` VARCHAR(50) NOT NULL DEFAULT '0' COLLATE 'utf8_unicode_ci',
	PRIMARY KEY (`id`)
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB;