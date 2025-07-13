#!/bin/bash
set -e

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
