package com.amapolazul.quiz.services

import com.amapolazul.quiz.bussines.Preguntas
import com.amapolazul.quiz.marshallers.PreguntasMarshallers
import spray.http.StatusCodes
import spray.routing.{ Route, RequestContext, HttpService }

import scala.util.{ Failure, Success }

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by juanmartinez on 14/09/15.
 */
trait PreguntasServiceTrait extends HttpService {

  private[ PreguntasServiceTrait ] val preguntas = new Preguntas

  private[ PreguntasServiceTrait ] val darPreguntas = {
    path( "preguntas" ) {
      get {
        ( ctx: RequestContext ) =>
          {
            val preguntasResponse = preguntas.consultarPreguntas()
            preguntasResponse.onComplete {
              case Success( preguntas ) =>
                ctx.complete( ( StatusCodes.OK, PreguntasMarshallers.Preguntas( "Preguntas consultadas correctamente", preguntas ) ) )
              case Failure( ex ) =>
                println( "pailas" )
            }
          }
      }
    }
  }

  private[ PreguntasServiceTrait ] val crearPreguntas = {
    path( "preguntas" ) {
      post {
        ( ctx: RequestContext ) =>
          {
            preguntas.crearPreguntas() onComplete {
              case Success( e ) =>
                ctx.complete( ( StatusCodes.OK, "respuesta" ) )
              case Failure( ex ) =>
                ctx.complete( ( StatusCodes.InternalServerError, ex.getMessage ) )
            }
          }
      }
    }
  }

  def darRutasPreguntas: Route = darPreguntas ~ crearPreguntas

}
