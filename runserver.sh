#! /bin/bash
echo "running applications"

cd /usr/app

java -XX:+HeapDumpOnOutOfMemoryError -Xms1024m -jar assesment-0.0.1-SNAPSHOT.jar