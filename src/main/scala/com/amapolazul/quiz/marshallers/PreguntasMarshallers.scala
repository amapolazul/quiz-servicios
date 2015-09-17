package com.amapolazul.quiz.marshallers

import org.json4s.{ DefaultFormats, Formats }
import spray.httpx.Json4sSupport

/**
 * Created by juanmartinez on 14/09/15.
 */
object PreguntasMarshallers extends Json4sSupport {
  override implicit def json4sFormats: Formats = DefaultFormats

  case class Preguntas( mensaje: String, preguntas: List[ Pregunta ] )

  case class Pregunta( enunciado: String, respuestaA: String, respuestaB: String, respuestaC: String, respuestaD: String, respuestaCorrecta: String, categoria: String, lectura: String, grado: String, urlImagen: String )
}
