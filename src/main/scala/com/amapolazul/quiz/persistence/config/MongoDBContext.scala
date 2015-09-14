package com.amapolazul.quiz.persistence.config

import reactivemongo.api.MongoDriver
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by juanmartinez on 12/11/14.
 */
object MongoDBContext {

  val replicaSetHosts = List( "localhost:27017" )
  val databaseName = "padbookquiz"
  //  val user = Utils.getProperty( "persistence.", "user" ).asInstanceOf[ String ]
  //  val pass = Utils.getProperty( "persistence.", "password" ).asInstanceOf[ String ]

  //  val credentials = Seq( Authenticate( databaseName, user, pass ) )
  val driver = new MongoDriver()
  val connection = driver.connection( replicaSetHosts, nbChannelsPerNode = 5 )

  def database = connection( databaseName )
}
