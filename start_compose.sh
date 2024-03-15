#!/bin/bash

# List of directories containing Docker Compose files
directories=("user" "roomManagement" "optionalServices" "accommodation")

# Check if the network already exists
network_exists=$(docker network ls | grep msnetwork)

# If the network does not exist, create it
if [ -z "$network_exists" ]; then
  echo "Creating network msnetwork"
  docker network create msnetwork
else
  echo "Network msnetwork already exists"
fi

# Navigate each directory and run Docker Compose
for dir in "${directories[@]}"
do
  echo "Navigating to the directory $dir"
  cd $dir
  echo "Running Docker Compose $dir"
  # starting containers
  docker-compose up -d
  cd ..
done