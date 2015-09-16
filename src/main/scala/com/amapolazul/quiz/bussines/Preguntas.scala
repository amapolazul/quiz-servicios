package com.amapolazul.quiz.bussines

import com.amapolazul.quiz.marshallers.PreguntasMarshallers.Pregunta
import com.amapolazul.quiz.persistence.dao.{ PreguntasEntity, PreguntasDAO }
import reactivemongo.bson.BSONObjectID
import reactivemongo.core.commands.LastError

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Codec
import scala.util.{ Failure, Success }

/**
 * Created by juanmartinez on 14/09/15.
 */
class Preguntas() {

  private val dao = PreguntasDAO

  def crearPreguntas() = {
    val bufferedSource = io.Source.fromFile( "/temp/preguntas.csv" )( Codec.UTF8 )
    val future: Future[ Unit ] = dao.removeAll() map {
      x =>
        {
          for ( line <- bufferedSource.getLines ) {
            val cols = line.split( "&" ).map( _.trim )
            val preguntaEntity = PreguntasEntity(
              _id = BSONObjectID.generate,
              enunciado = cols( 0 ),
              respuestaA = cols( 1 ),
              respuestaB = cols( 2 ),
              respuestaC = cols( 3 ),
              respuestaD = cols( 4 ),
              respuestaCorrecta = cols( 5 ),
              categoria = cols( 6 ),
              grado = cols( 7 ),
              lectura = cols( 8 ),
              urlImagen = cols( 9 )
            )
            println( s"${cols( 0 )}|${cols( 1 )}|${cols( 2 )}|${cols( 3 )}|${cols( 4 )}|${cols( 5 )}|${cols( 6 )}|${cols( 7 )}|${cols( 8 )}|" )
            dao.insert( preguntaEntity )
            //          bufferedSource.close
          }
        }
    }
    future
  }

  def consultarPreguntas(): Future[ List[ Pregunta ] ] = {
    val preguntas: Future[ List[ PreguntasEntity ] ] = dao.findAll()
    preguntas.map {
      preguntasList =>
        {
          preguntasList.map {
            x =>
              {
                Pregunta(
                  enunciado = x.enunciado,
                  respuestaA = x.respuestaA,
                  respuestaB = x.respuestaB,
                  respuestaC = x.respuestaC,
                  respuestaD = x.respuestaD,
                  respuestaCorrecta = x.respuestaCorrecta,
                  categoria = x.categoria,
                  grado = x.grado,
                  lectura = x.lectura,
                  urlImagen = x.urlImagen
                )
              }
          }
        }
    }
  }
}
