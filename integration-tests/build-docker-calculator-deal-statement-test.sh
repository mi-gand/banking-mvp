#!/bin/bash
set -e

docker build -t ms-calculator:test ../calculator
docker build -t ms-deal:test ../deal
docker build -t ms-statement:test ../statement
