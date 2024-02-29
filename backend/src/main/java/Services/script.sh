#!/bin/bash

# Define the package name
PACKAGE_NAME="Services"

# Define the list of classes
CLASSES="Club Content Event EventPost Field MediaPost Post Reaction TextPost User UserClub"

# Loop through the classes and create service files
for CLASS_NAME in $CLASSES
do
    SERVICE_FILE="${CLASS_NAME}Service.java"
    touch "${SERVICE_FILE}"

    echo "package ${PACKAGE_NAME};

import org.springframework.stereotype.Service;

@Service
public class ${CLASS_NAME}Service {
    // Your service logic goes here
}" > "${SERVICE_FILE}"

    echo "Created ${SERVICE_FILE}"
done

