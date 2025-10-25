@echo off
set CONTAINER_NAME=my_postgres_hr
set POSTGRES_USER=postgres
set POSTGRES_PASSWORD=1234567
set POSTGRES_DB=hrdb
set DATA_PATH=D:\Docker\PostgresData_hr_01

echo Creating new PostgreSQL container...
docker run -d ^
 --name %CONTAINER_NAME% ^
 -e POSTGRES_USER=%POSTGRES_USER% ^
 -e POSTGRES_PASSWORD=%POSTGRES_PASSWORD% ^
 -e POSTGRES_DB=%POSTGRES_DB% ^
 -p 5462:5432 ^
 -v %DATA_PATH%:/var/lib/postgresql/data ^
 postgres:15

:end
echo Done.
pauses