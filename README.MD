<div align=center>
<h1>CASINO CUSTOMERS PROJECT</h1>
<p>This project is the final project of the kruger star's reality backend course</p>
</div>

## About the project
Este proyecto es un simulacion simple de un sistema de juego de suerte, se registra un cliente y una vez registrado el cliente puede apostar con cierta cantidad de dinero tirando un numero de 0 a 5, la sistema del Casino eligeria un numero aleatorio de 0 a 5, las reglas son las siguientes : 
    
    - Si el numero del usuario es el mismo exacto numero de la sistema del casino ganara el doble de su apuesta
    - Si el numero del cliente es uno mas o menos que el numero de la sistema del casino ganara la mitad de la apuesta
    - Otros casos el cliente perderia al monto de l apuesta y sera descontado de su cuenta

## How It Works
Este proyectp fue construido utilizando la microservices architecture, asi que hay 2 servicos :
<div align=start>
1-User-Service: es responsable de crear editar eliminar y leer clientes, aparte de buscar clientes por nmbre correo o numero de id, tambien sera responsable de agregar y listar las direcciones de los clientes, este servicio se comunica con el servico de Casino-service para poder ejecutar y validar las apuestas.

2-Casino-service: es el servicio responsable de validar las apuestas generar los numeros aleatorios  decidir el resultado final de la apuesta y enviar la respuesta al servicio de los clientes User-service.
</div>

## Built With
- Spring boot
- Postgresql
- Maven
- Spring cloud
- Open api/swagger
- Junit 5
- Eureka Server
- Postman

## ✍️ Author

👤 **Kenan Aljaber**

- GitHub: [Kenan Aljaber](https://github.com/KenanAljaber)
- LinkedIn: [Kenan Aljaber](https://www.linkedin.com/in/kenan-aljaber-a232aa187/)

# Show your support

Give a ⭐ if you like this project!

