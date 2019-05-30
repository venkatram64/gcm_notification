CREATE SCHEMA `my_school_db` ;

CREATE TABLE IF NOT EXISTS `reg_gcm_users` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `gcm_regid` text,
 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;