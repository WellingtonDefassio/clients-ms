db_create:
	docker run --name postgres12 -p 5432:5432 -e POSTGRES_PASSWORD=secret -e POSTGRES_USER=root -e POSTGRES_DB=clients -d postgres:12-alpine
db_create_messages:
	docker run --name postgres-messages -p 5433:5432 -e POSTGRES_PASSWORD=secret -e POSTGRES_USER=root -e POSTGRES_DB=messages -d postgres:12-alpine
db_create_entries:
	docker run --name postgres-entries -p 5434:5432 -e POSTGRES_PASSWORD=secret -e POSTGRES_USER=root -e POSTGRES_DB=entries -d postgres:12-alpine
db_delete:
	docker rm -f postgres12
db:
	docker run postgres12
rabbit:
	docker run -d --hostname my-rabbit --name rabbirmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management