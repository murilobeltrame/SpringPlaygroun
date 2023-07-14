$name = "playground-sql"
$saPassword = "MyPass@word"

$containerExists = docker container ls --all --quiet --filter "name=$name"

if ($containerExists) {
    # Container exists, start it
    docker container start $name
}
else {
    # Container does not exist, run it
    docker run -e "ACCEPT_EULA=1" -e "MSSQL_SA_PASSWORD=$saPassword" -e "MSSQL_PID=Developer" -e "MSSQL_USER=SA" -p 1433:1433 --name $name mcr.microsoft.com/azure-sql-edge
}
docker container logs --follow $name