# Instrucciones
Antes de ejecutar el proyecto se debe tener en cuenta lo siguiente:

## 1) Cambio en persistencia
Se utilizo un driver para sql server, sin embargo, esto se puede reemplazar por los datos de motor de base de datos que se requiera o que se tenga. Por ahora, los datos de persistencia estan con mi motor local, por lo que los datos deben ser cambiados, de igual forma, la base de datos que especifique en la url fue CallCenter, esto tambien esta libre para ser cambiado.

## 2) Estructura inicial
Se deben crear las cinco tablas iniciales Employee, Operator, Supervisor, Director y Call dentro de la base de datos que se especifico en el punto anterior.

## 3) Pruebas
Se creo un Wep app con API respectivamente para generar las pruebas debido a un problema de configuracion con JUnit, sin embargo, se dejo una prueba para demostrar la estructura que usare en las pruebas. Para ejecutar correctamente las pruebas se debe primero crear operadores, supervisores o directores segun el escenario de la prueba y posteriormente ejecutar el api de llamadas para simular la entrada de 10 llamadas simultaneas.
