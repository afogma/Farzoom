# Farzoom backend test

This project implements CRUD operations to work with Tasks with fields: id, name , description and creation/update date.
All data saved in Hashmap and being removed upon stopping or restarting application.

Project built with jdk11. It includes lombok,  To inspect all crud methods
follow url: http://server-ip:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

Curl command for testing: curl -u admin:admin http://server-ip:8080/api/
