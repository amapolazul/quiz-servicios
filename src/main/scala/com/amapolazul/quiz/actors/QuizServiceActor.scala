package com.amapolazul.quiz.actors

import akka.actor.{ ActorRefFactory, Actor, Props }
import com.amapolazul.quiz.services.PreguntasServiceTrait
import spray.routing.HttpService

/**
 * Created by juanmartinez on 14/09/15.
 */
class QuizServiceActor extends Actor with QuizAmapolaServiceTrait {
  def actorRefFactory: ActorRefFactory = context
  override def receive = runRoute( quizAmapolaRoutes )
}

trait QuizAmapolaServiceTrait extends HttpService with PreguntasServiceTrait {
  val quizAmapolaRoutes = {
    pathPrefix( "amapola" ) {
      pathPrefix( "api" ) {
        pathPrefix( "v1" ) {
          darRutasPreguntas
        }
      }
    }
  }
}

/**
 * Companion object del orquestador.
 */
object QuizServiceActor {
  //Factory method para crear el orquestador.
  def props = Props( new QuizServiceActor )
}