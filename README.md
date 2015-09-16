Api_AfiliacionEnLinea
===============

API  de Afiliacion en linea, es el api que contiene todos los servicios web necesarios para llevar acabo el proceso de afiliacion a la ARL.
Prueba del hook de Jenkins

##Librerias


En el desarrollo de API ARL SURA se realiza bajo las siguientes librerias como las mas importantes :

 - sbt (0.13.7)
 - akka (2.3.6)
 - spray (1.3.1)

##Entorno de Desarrollo


El entorno de desarrollo seleccionado es IntelliJ , el proyecto ya tiene las librerias necesarias para generear librerias para IJ , en sbt ( gen-idea )

##Como se corre


Para correr el servidor es necesario utlizar sbt con los comandos :

    > clean update compile run


##Configuraciones Heroku


Para pruebas locales:

		foreman start


Agregar git remotes:

		git remote add staging git@heroku.com:api-arlaflinealabo.git
        git remote add pdn git@heroku.com:api-arlaflinea.git


Para despliegues en Laboratorio:

		git fecth rama labo
		git checkout labo
		git merge dev
		git push staging labo:master


Para despliegues en Producción:

		git checkout master
		git merge labo
		git push pdn master

##Limpiar Cache Heroku

    Cuando: Compiled slug size: MB is too large (max is 300MB).
    Instalar Plugin heroku toolbelt para purgar el cache de heroku.

        https://github.com/heroku/heroku-repo#purge_cache

    Ejecutar el comando para purgar el cache (purge_cache)
    Al hacer push de la aplicaci贸n va a volver a descargar todo de nuevo


## Para entrar en la consola heroku

        heroku run bash


Deploy heroku con http


## Identificando el ambiente de despliegue

Para identificar el ambiente se utiliza el archivo /opt/app/shared/afilinea_api/configuration.conf,
el cual debe tener la variable AMBIENTE que debe tener uno de los siguientes valores:

 - local
 - desarrollo
 - laboratorio
 - heroku_lab
 - heroku_prod

Estos valores coinciden con los nombres de las carpetas en /src/main/resources/conf/

El valor por defecto (en caso de no encontrar el archivo o la variable) es heroku_lab