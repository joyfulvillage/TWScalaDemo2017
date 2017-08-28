package controllers

import javax.inject.Inject

import models.{ Converted, Identical, Optionaled, Original }
import play.api.mvc.{ Action, Controller }
import service.HenKanBackend

import scala.concurrent.Future

class HenkanController @Inject() (backend: HenKanBackend) extends Controller with JsonResponses {

  def convertIt(id: Int, name: String) = Action.async {

    val original = Original(1, "name 1")

    val res = backend.convertIt(original)

    Future.successful(Ok(res))
  }

  def optional(id: Int, name: String) = Action.async {
    import henkan.optional.syntax.all._
    val original = Original(id, name)

    val optionaled = from(original).toOptional[Optionaled]

    Future.successful(Ok(optionaled))
  }
}
