package com.amapolazul.quiz.persistence.config

import reactivemongo.api.MongoDriver
import reactivemongo.core.nodeset.Authenticate

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by juanmartinez on 12/11/14.
 */
object MongoDBContext {


//  val replicaSetHosts = List( "159.203.78.181:27017" )
  val replicaSetHosts = List( "ds027719.mongolab.com:27719" )
  val databaseName = "heroku_hqm0lq18"
  val user = "amapola"
  val pass = "Samantha26"
  val credentials = Seq( Authenticate( databaseName, user, pass ) )
  val driver = new MongoDriver()
  val connection = driver.connection( replicaSetHosts, nbChannelsPerNode = 5, authentications = credentials )
  def database = connection( databaseName )
}
