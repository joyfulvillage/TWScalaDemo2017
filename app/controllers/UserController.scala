package controllers

import models.User
import play.api.mvc.{ Action, Controller }

import scala.concurrent.Future

class UserController extends Controller with JsonResponses {

  def getUser(userId: Int) = Action.async {
    val user = User(id = 1, name = "Luke", email = "starwar@gmail.com", address = "123-234 56St")
    Future.successful(Ok(user))
  }
}
