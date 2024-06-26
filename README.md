# Parcial 2 AREP Camilo Fajardo

## Iniciando

### Prerrequisitos

- git
- Java
- Maven

Si aún no cuenta con las tecnologías necesarias aquí hay unos videotutoriales que pueden ser de utilidad.

* Git: https://www.youtube.com/watch?v=4xqVv2lTo40
* Java: https://www.youtube.com/watch?v=BG2OSaxWX4E
* Maven: https://www.youtube.com/watch?v=1QfiyR_PWxU

### Instalando el proyecto

Para correr el proyecto tienes que iniciar primero docker en tu máquina

Luego, clona el proyecto usando el siguiente comando

```
git clone https://github.com/briancfajardo/Parcial2Arep.git
```

Una vez se corre el programa deberás entrar a la dirección http://localhost:4567/ y aparecerá lo siguiente:

![PagInicial](https://github.com/briancfajardo/Parcial2Arep/assets/80064378/0ffd9291-61b8-492b-896e-a5b8cff9702f)

Para usarlo solo debes decidir cuál de los dos tipos de búsqueda deseas usar, luego, coloca una lista de elementos
separados por comas y sin espacios, por último en el segundo campo coloca el valor que desea buscar. Un ejemplo de uso se
muestra a continuación:

![Usos](https://github.com/briancfajardo/Parcial2Arep/assets/80064378/69a40bcd-92d4-4a1b-9e91-7b0bec9c298d)

## Arquitectura

La arquitectura que se siguió fue la siguiente: 

<img width="644" alt="Arquitectura" src="https://github.com/briancfajardo/Parcial2Arep/assets/80064378/129e4990-92d7-4d21-a4fe-b31df9336cdf">

Para construir esta arquitectura en el mismo proyecto se separaron los 2 componentes en paquetes diferentes

-  mathService: Contiene todas las clases que ayudan a prestar el servicio de buscar en una lista en las dos maneras 
requeridas (lineal y binario)
-  proxyService: Contiene todas las clases que ayudan a actuar como intermediario a esta clase

## Como correrlo en AWS

Para correr el programa en AWS con la arquitectura propuesta lo primero que hacemos es crear las máquinas virtuales, una 
máquina "Proxy" y 2 máquinas "MathServer" que actuaran como los componentes de la arquitectura

![Maquinas](https://github.com/briancfajardo/Parcial2Arep/assets/80064378/ff433653-f79a-4be5-9676-4b9941c6df4a)

Cuando tengamos las 3 máquinas creadas, y con ayuda del instalador de Linux "yum" instalamos git, java y maven en cada una de las máquinas. Con estas 
tecnologías instaladas, lo primero que hacemos es clonar este repositorio en todas las maquinas con el siguiente comando:

```
git clone https://github.com/briancfajardo/Parcial2Arep.git
````

Ya con el código fuente en las máquinas virtuales, corremos los siguientes comandos:

En la destinada al Proxy:

```
java -cp "./target/classes:./target/dependency/*"  co.edu.eci.parcial.proxyService.SparkServerWeb
```

En las destinadas al MathService:

```
java -cp "./target/classes:./target/dependency/*"  co.edu.eci.parcial.mathService.MathServices
```

Ya con los servicios corriendo entramos la siguiente URL: http://ec2-54-81-136-162.compute-1.amazonaws.com:4567/ y ahi 
encontrarás el mismo formulario de antes desplegado en AWS.

![Desplegado](https://github.com/briancfajardo/Parcial2Arep/assets/80064378/5e878f6a-7769-431b-b38e-713bc7144727)

## Video y test de integración

En el video se muestra el correcto funcionamiento del proyecto en AWS y además se realizan las siguientes pruebas:

1. Se prueba que el proyecto corra con normalidad
2. Se prueba que las dos funcionalidades requeridas se ejecuten de manera correcta.
3. Se procede a apagar una máquina de los MathService para evidenciar que en efecto está usando las dos máquinas virtuales
   (debe fallar la mitad de las veces)
4. Se realiza la misma prueba que la prueba 3, pero con la otra máquina de MathService

https://youtu.be/iHQ-EXvt5KI?si=RncwdSJPokrMfcoa

## Agradecimientos 

- Al profesor Daniel Benavides
- Al compañero que califique este parcial
