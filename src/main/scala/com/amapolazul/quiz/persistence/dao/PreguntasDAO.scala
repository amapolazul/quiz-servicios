package com.amapolazul.quiz.persistence.dao

import reactivemongo.bson.BSONObjectID
import reactivemongo.extensions.dao.BsonDao
import com.amapolazul.quiz.persistence.config.MongoDBContext._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by juanmartinez on 14/09/15.
 */
object PreguntasDAO extends BsonDao[ PreguntasEntity, BSONObjectID ]( database, "preguntas" )
