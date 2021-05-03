# App_Developers
Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:
Developer
Skill
Account
AccountStatus (enum ACTIVE, BANNED, DELETED)
 
Developer -> Set<Skill> skills + Account account
Account -> AccountStatus
Требования:
 
Придерживаться шаблона MVC (пакеты model, repository, service, controller, view)
Для миграции БД использовать https://www.liquibase.org/
Для импорта библиотек использовать Maven
Результатом выполнения проекта должен быть отдельный репозиторий на github, с описанием задачи, проекта и инструкцией по локальному запуску проекта.
 
Технологии: Java, MySQL, JDBC, Maven, Liquibase
