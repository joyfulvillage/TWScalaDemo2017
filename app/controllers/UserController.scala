package controllers

import models.User
import play.api.mvc.{ Action, Controller }

import scala.concurrent.Future

class UserController extends Controller with JsonResponses {

  def getUser(userId: Int, name: String, age: Int) = Action.async { request =>
    val user =
      User(
        id = 1,
        name = name,
        email = request.headers.toMap("email").headOption.getOrElse("dummyEmail"),
        age = age
      )
    Future.successful(Ok(user))
  }
}
