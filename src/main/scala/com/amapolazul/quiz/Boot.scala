package com.amapolazul.quiz

import akka.actor.ActorSystem
import akka.io.IO
import akka.util.Timeout
import com.amapolazul.quiz.actors.QuizServiceActor
import spray.can.Http
import akka.pattern.ask
import scala.concurrent.duration._

/**
 * Created by juanmartinez on 14/09/15.
 */
object Boot extends App {

  implicit val timeout = Timeout( 50.seconds )

  implicit val system = ActorSystem( "QuizAmapola-backend" )
  val orchestrator = system.actorOf( QuizServiceActor.props, "quiz-amapola-service" )
  val host = "0.0.0.0"
  val port = Option( System.getenv( "PORT" ) ).getOrElse("8080")
  IO( Http ) ? Http.Bind( orchestrator, interface = host, port = port.toInt )
}
