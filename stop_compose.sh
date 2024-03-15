#!/bin/bash

# List of directories containing Docker Compose files
directories=("user" "roomManagement" "optionalServices" "accommodation")

# Navigate each directory and run Docker Compose
for dir in "${directories[@]}"
do
  echo "Navigating to the directory $dir"
  cd $dir
  echo "Running Docker Compose $dir"

  # stopping containers
  docker-compose down

  cd ..
done

# Check if the network already exists
network_exists=$(docker network ls | grep msnetwork)

# If the network exists, remove it
if [ -n "$network_exists" ]; then
  echo "Removing network msnetwork"
  docker network rm msnetwork
else
  echo "Network msnetwork does not exist"
fi