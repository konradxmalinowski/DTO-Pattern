-- Initialize schema and seed data for spring-dto
CREATE DATABASE IF NOT EXISTS `spring-dto` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `spring-dto`;

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `emial` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `users` (`id`, `emial`, `password`, `username`) VALUES
  (1, 'email@test.com', 'password', 'username')
ON DUPLICATE KEY UPDATE username=VALUES(username);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr53o2ojjw4fikudfnsuuga336` (`password`);

ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;


