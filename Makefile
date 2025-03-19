APP_NAME=kafka-sample
JAR_NAME=kafka-msk-1.0-SNAPSHOT.jar
MAIN_PACKAGE=com.example.kafka

build:
	docker build -t $(APP_NAME) .

# Run the container with a default class
run-default:
	docker run --rm --env-file .env $(APP_NAME) $(MAIN_PACKAGE).App

# List Kafka topics
run-list-topics:
	docker run --rm --env-file .env $(APP_NAME) $(MAIN_PACKAGE).ListTopic

# Produce message to a topic (Requires --topic, --key, and --value)
run-producer:
	docker run --rm --env-file .env $(APP_NAME) $(MAIN_PACKAGE).ProduceToTopic --topic $(topic) --key $(key) --value $(value)

# Consume messages from a topic (Requires --topic)
run-consumer:
	docker run --rm --env-file .env $(APP_NAME) $(MAIN_PACKAGE).ConsumeFromTopic --topic $(topic)

# Clean up (removes old Docker images)
clean:
	docker rmi $(APP_NAME) || true
