CREATE TABLE `categories` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `slug` varchar(45) DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `expenses`
ADD category_id int(10) unsigned;


insert into categories (id, name, slug, user_id)
select tags.id, tags.name, tags.slug, tags.user_id
from tags;

update expenses join expenses_tags on expenses.id = expenses_tags.expense_id
set expenses.category_id = expenses_tags.tag_id;

truncate expenses_tags;
truncate tags;