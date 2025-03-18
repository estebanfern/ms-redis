# Microservicios + Redis

## Requisitos
- Java 11 ([SDKman](https://sdkman.io/))
- Docker y docker compose

## Despliegue
```bash
#En la raiz del proyecto
docker compose up -d --build
```
## Uso
### Clientes `/api/v1/clientes`
- Creación de cliente `POST /api/v1/clientes/`
```json
{
    "nombre" : "Esteban",
    "genero" : "MALE",
    "edad" : 23,
    "identificacion" : "5425495",
    "direccion" : "Dr Molinas 677",
    "telefono" : "0982891328",
    "contrasena" : "hola"
}
```
- Obtener cliente `GET /api/v1/clientes?id=id&type=type`
```json
type = [
    ID,
    IDENTIFICACION
]
```
- Edición de cliente `PUT /api/v1/clientes/:id`
```json
{
    "nombre" : "Esteban",
    "genero" : "MALE",
    "edad" : 23,
    "identificacion" : "5425495",
    "direccion" : "Dr Molinas 677",
    "telefono" : "0982891328",
    "contrasena" : "hola",
    "active": "true"
}
```
- Eliminar cliente `DELETE /api/v1/clientes/:id`

### Cuentas `/api/v1/cuentas`
- Creación de cuenta `POST /api/v1/cuentas`
```json
{
    "numeroCuenta" : "123124",
    "tipoCuenta" : "AHORROS",
    "clienteId" : "1"
}
```
- Obtener cuenta `GET /api/v1/cuentas/:id`
- Edición de cuenta `PUT /api/v1/cuentas`
```json
{
    "numeroCuenta" : "123123",
    "tipoCuenta" : "AHORROS",
    "active" : "true"
}
```
- Eliminar cuenta `DELETE /api/v1/cuentas/:id`

### Movimientos `/api/v1/movimientos`
- Creación de movimiento `POST /api/v1/movimientos`
```json
{
    "monto" : 1000,
    "descripcion" : "Transferencia recibida",
    "numeroCuenta" : "123124"
}
```

### Reportes `/api/v1/reportes`
- Obtener reporte de cuentas y movimientos de un cliente `GET /api/v1/reportes?cliente=id&inicio=isodatetime&fin=isodatetime`
