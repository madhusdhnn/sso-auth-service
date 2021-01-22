#!/usr/bin/env bash
set -e

echo "killing running db connections"
psql -U postgres -h localhost -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE pid <> pg_backend_pid() AND datname = 'auth_service_dev'"

echo "Dropping existing database.."
dropdb -U postgres -h localhost --if-exists auth_service_dev

echo "Creating database.."
createdb -U postgres -h localhost auth_service_dev

echo "Seeding Data"
psql -U postgres -h localhost -d auth_service_dev --single-transaction -f ./seed_dev.sql
