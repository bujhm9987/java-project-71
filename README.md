### Hexlet tests and linter status:
[![Actions Status](https://github.com/bujhm9987/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/bujhm9987/java-project-71/actions)
[![Buld project](https://github.com/bujhm9987/java-project-71/actions/workflows/build-project.yml/badge.svg)](https://github.com/bujhm9987/java-project-71/actions/workflows/build-project.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/f7fa7500da7795985b3a/maintainability)](https://codeclimate.com/github/bujhm9987/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f7fa7500da7795985b3a/test_coverage)](https://codeclimate.com/github/bujhm9987/java-project-71/test_coverage)

<h1 align="center">Проект: Вычислитель отличий</h1>

###### Автор Павлов Игорь Геннадьвич (bujhm9987) 

### Описание

"Вычислитель отличий" – программа, определяющая разницу между двумя структурами данных.

Возможности утилиты:
* Поддержка разных входных форматов: yaml и json;
* Генерация отчета в виде plain text, stylish и json.

### Установка

    git clone git@github.com:bujhm9987/java-project-71.git
    cd java-project-71/app
    make -C app build

### Запуск

    ./build/install/app/bin/app filepath1.json filepath2.json


### Вывод справочной информации

    ./build/install/app/bin/app -h

### Выбор формата вывода результата сравнения

    ./build/install/app/bin/app -f format filepath1.json filepath2.json

### Демонстрация проекта:

* [Справочная информация](https://asciinema.org/a/xOJ0VfuFogpRlHtzJfCEcaAN9)
* [Сравнение JSON файлов](https://asciinema.org/a/XwAyq0fqSfqelW0aSDdEsUWHG)
* [Сравнение YAML файлов](https://asciinema.org/a/5BJBV4bKo8pWY57IMSEn0gGdo)
* [Вывод результата сравнения в формате Stylish](https://asciinema.org/a/8Hwqi12QMEXv6L7cchxAT7LlJ)
* [Вывод результата сравнения в формате Plain](https://asciinema.org/a/uxXE2XLm4IeNcdO2JZxBR49MA)
* [Вывод результата сравнения в формате JSON](https://asciinema.org/a/bcGEgVDcHwLZMZ1CLHjPjxvo3)

