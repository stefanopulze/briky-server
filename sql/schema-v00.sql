CREATE TABLE `users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(160) NOT NULL,
  `password` VARCHAR(160) NOT NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `updated_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tokens` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `value` VARCHAR(160) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_token_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_token_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `briky`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `tags` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `users_tags` (
  `user_id` INT UNSIGNED NOT NULL,
  `tag_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `tag_id`)
);

CREATE TABLE `expenses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `value` DECIMAL(10,2) NULL DEFAULT 0,
  `latitude` DECIMAL(10,8) NULL,
  `longitude` DECIMAL(11,8) NULL,
  `accuracy` SMALLINT NULL,
  `description` VARCHAR(160) NULL,
  `created_at` TIMESTAMP NULL,
  `updated_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `expenses_tags` (
  `expense_id` INT UNSIGNED NOT NULL,
  `tag_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`expense_id`, `tag_id`)
);
