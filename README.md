# SSO Auth Service

- SSO based authentication service using Spring boot and Spring security
- Proxied by Nginx and supports redirection URL for the system of services trying to authenticate with this service

## Pre-Requisites

1. Java 8+
2. PostgreSQL
3. Vagrant

## Development Setup

* Install `Vagrant` which is used to setup the isolated environment
* Run `vagrant up` command to create the environment. This also provisions the machine
* Run `vagrant provision` command to manually provision the virtual machine
* Run `vagrant ssh` command to login to the virtual machine
* Go to project directory using `cd /vagrant` command inside the virtual machine
* Run `bash start_service.sh` shell script file. This will setup a database with seed value in it and starts the Spring Boot application
* Access the application in `http://localhost:8080/` which will be proxied by Nginx to access the back-end service
