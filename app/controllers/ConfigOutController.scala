package controllers

import javax.inject.Inject

import models.{ ConfigOut, Inner }
import play.api.Configuration
import play.api.mvc.{ Action, Controller }
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

class ConfigOutController @Inject() (config: Configuration) extends Controller with JsonResponses {

  def getConfig = Action.async {

    val c = config.underlying

    val interval = c.as[FiniteDuration]("demo.interval")

    val inner = c.as[Inner]("demo.inner")

    val configOut = c.as[ConfigOut]("demo")

    Future.successful(Ok(configOut))
  }
}
