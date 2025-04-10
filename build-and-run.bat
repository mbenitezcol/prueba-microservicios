@echo off
echo ============================
echo 🏗️  Compilando los microservicios...
echo ============================

cd cliente-persona-service
call mvn clean package -DskipTests
IF EXIST target\cliente-persona-service.jar (
    del target\cliente-persona-service.jar
)
for %%f in (target\cliente-persona-service-*.jar) do (
    ren "%%f" cliente-persona-service.jar
)
cd ..

cd cuenta-movimiento-service
call mvn clean package -DskipTests
IF EXIST target\cuenta-movimiento-service.jar (
    del target\cuenta-movimiento-service.jar
)
for %%f in (target\cuenta-movimiento-service-*.jar) do (
    ren "%%f" cuenta-movimiento-service.jar
)
cd ..

echo ============================
echo 🐳 Construyendo contenedores Docker...
echo ============================
docker-compose build

echo ============================
echo 🚀 Levantando microservicios...
echo ============================
docker-compose up
