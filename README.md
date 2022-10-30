# films-library
Получает с открытого API IMDB (https://www.omdbapi.com) и позволяет пользователю искать фильмы с тегами, добавлять в избранное и помечать просмотренный фильм 

Сервис работает через Open Feigns Clients, чтобы подключиться к открытому API.
Пользователь может зарегистрироваться, получив роль USER, что дает ему доступ к базовому функционалу: получить информацию о своем аккаунте, редактировать его, найти фильм с помощью IMDB-ID, найти фильмы по году с помощью 3 операций:>=,=,<=; найти фильм по названию, а также добавить фильм или удалить его из списка к просмотру.

Данные пользователя сохраняются в базу данных с зашифрованным паролем, чтобы обезопасить данные.

Также есть функционал администратора, доступный для роли ADMIN. Такому пользователю возможно получить список всех пользователей, получить данные конкретного пользователя через его UUID, удалить фильм из БД и наоборот добавить его в БД, а также изменить данные фильма в БД.

Функционал поиска фильма по названию реализован через два этапа - поиск в базе, и в случае отсутствия фильмов с таким названием сервис подключается к API и получает список фильмов оттуда, а после преобразует весь полученный список в Entity и заносит в базу данных, расширяя его. Аналогично работает поиск по IMDB-ID.

Список фильмов к просмотру привязан к пользователю через связь Many-To-Many.

Все настройки вынесены в файл Application.yml

Все таблицы создаются через LiqueBase. Также есть прогрев БД. В папке liquebase V1.1 есть файл с расширением .csv. Он содержит список IMDB ID, который сразу заносится в базу.

### Stack:
```
Java 11
Spring Boot 2
Spring Cloud Netflix: Feign Clients
Spring Data JPA (Hibernate)
Spring Security
PostgreSQL
Liquibase
Gradle 
```
