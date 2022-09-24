# SeverotekTestTask

1. Copy repo (see code on master branch)
2. Run test using: ./gradlew clean test --info
3. Pay attention to the captcha
4. JDK 17 must be installed
5. For change test data use testData.csv file in resourses folder 
6. [Отсутствует кнопка "Показать" на странице фильтрации](https://github.com/DoroshenkoDenis/SeverotekTestTask/issues/1/ "Issue")

Использован стек JAVA + Junit
Реализовано логгирование
Входные данные размещены в testData.csv в папке src/test/resources/testData.csv

Флоу:
Запуск развёрнутого на весь экран браузера.
Переход на сайт https://market.yandex.ru/
Перейти к разделу "Компьютеры" и выбрать "Ноутбуки".
Задать параметры поиска: производитель - Lenovo; цена - 25000 - 30000
На первой странице с результатами убедиться, что найденные товары соответствуют заданным параметрам поиска.
Закрыть браузер.
