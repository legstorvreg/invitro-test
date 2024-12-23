# language: ru

Функция: Проверка backend GET запроса

  Структура сценария: Проверка кода и тела ответа
    Допустим я строю запрос с параметром "CODE" равным "<code>"
    Когда я отправляю запрос
    Тогда я проверяю, что код ответа равен 200
    И я проверяю, что тело ответа содержит обязательные поля "city", "code", "guid"

    Примеры:
      | code   |
      | bajmak |
      | moscow |
      | london |
