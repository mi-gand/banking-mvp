#!/bin/bash

set -e

set -a
source .env
set +a


if [[ "$(uname -s)" == MINGW* || "$(uname -s)" == CYGWIN* || "$(uname -s)" == MSYS* ]]; then
  echo "Windows detected"

  DOCKER_PROCESS=$(tasklist | grep "Docker Desktop.exe" || true)

  if [[ -z "$DOCKER_PROCESS" ]]; then
    powershell.exe -Command "Start-Process 'C:\Program Files\Docker\Docker\Docker Desktop.exe'"
    echo "Docker Desktop started"
    sleep 10
  else
    echo "Docker Desktop already running"
  fi
fi

IS_INITIALIZED=$(docker run --rm -v ${VOLUME_NAME}:${KAFKA_LOG_DIR} alpine \
  sh -c "[ -f ${KAFKA_LOG_DIR}/${META_FILE} ] && echo yes || echo no")

if [ "$IS_INITIALIZED" == "yes" ]; then
  echo "Kafka уже инициализирована"
else
  echo "Инициализация Kafka кластера"

  docker run --rm \
    -v "${VOLUME_NAME}:${KAFKA_LOG_DIR}" \
    alpine \
    sh -c "chown -R 1000:1000 ${KAFKA_LOG_DIR}"

  docker run --rm \
    -v "${VOLUME_NAME}:${KAFKA_LOG_DIR}" \
    "${KAFKA_IMAGE}" \
    kafka-storage format \
      --ignore-formatted \
      --cluster-id="${CLUSTER_ID}" \
      --config=/etc/kafka/kraft/server.properties

  echo "Kafka инициализирована"
fi

mvn clean package -pl '!integration-tests'
docker compose up --build -d
read -p "Press Enter to exit"