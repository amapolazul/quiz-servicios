package com.amapolazul.quiz.persistence.dao

import reactivemongo.bson.{ Macros, BSONObjectID }

/**
 * Created by juanmartinez on 14/09/15.
 */
case class PreguntasEntity( _id: BSONObjectID, enunciado: String, respuestaA: String, respuestaB: String, respuestaC: String, respuestaD: String, respuestaCorrecta: String, categoria: String, lectura: String, grado: String, urlImagen: String) {
}

object PreguntasEntity {
  implicit val preguntasHandler = Macros.handler[ PreguntasEntity ]
}
