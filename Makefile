create-db:
	psql -U postgres -tc "SELECT 1 FROM pg_database WHERE datname = 'sample'" | grep -q 1 || psql -U postgres -c "CREATE DATABASE sample"

drop-db:
	psql -U postgres -tc "SELECT 1 FROM pg_database WHERE datname = 'sample'" | grep -q 1 && psql -U postgres -c "DROP DATABASE sample"

reset-db: drop-db create-db