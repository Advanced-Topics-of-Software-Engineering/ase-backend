#!/usr/bin/env bash
set -eo pipefail
echo "Runner"
exec java -jar /app/ase23/ase23-0.0.1-SNAPSHOT.jar &
