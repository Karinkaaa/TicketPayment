# TicketPayment

API

API работает в JSON формате и состоит из двух сервисов:

1. Сервис приема заявок на оплату. Получает, валидирует и сохраняет заявку в БД.
На вход принимает:
- Номер маршрута
- Дата и время отправления
На выход отдает (в случае успеха):
- Идентификатор заявки

2. Сервис проверки статуса заявки. Получает данные о заявке из БД.
 На вход принимает:
Идентификатор заявки
	На выход отдает:
Статус заявки
Заявки необходимо хранить в БД (на выбор).

Логика проведения заявок

Процесс проведения оплаты (запускается раз в минуту):
- Выбирает заявку, подходящую для проведения, из БД
- Отправляет в http-сервис (сервис можно реализовать в этом же приложении отдельным endpoint-ом) платежного шлюза запрос, который случайным образом возвращает статусы (обрабатывается, ошибка, проведен)
- Обновляет статус заявки
Статусы “Ошибка” или “Проведен” являются конечными, т.е. повторение запроса не требуется
