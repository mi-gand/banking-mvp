#!/bin/bash
set -e

docker build -t ms-calculator:latest ../calculator
docker build -t ms-deal:latest ../deal
