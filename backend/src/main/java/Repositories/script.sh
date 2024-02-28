#!/bin/bash

# Define the package name
PACKAGE_NAME="Repositories"

# Define the list of classes
CLASSES="Club Content Event EventPost Field MediaPost Post Reaction TextPost User UserClub"

# Loop through the classes and create repository files
for CLASS_NAME in $CLASSES
do
    REPOSITORY_FILE="${CLASS_NAME}Repository.java"
    touch "${REPOSITORY_FILE}"

    echo "package ${PACKAGE_NAME};

import org.springframework.data.jpa.repository.JpaRepository;

public interface ${CLASS_NAME}Repository extends JpaRepository<${CLASS_NAME}, Long> {
    // You can add custom query methods here if needed
}" > "${REPOSITORY_FILE}"

    echo "Created ${REPOSITORY_FILE}"
done

