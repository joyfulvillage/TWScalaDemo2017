package controllers

import javax.inject.Inject

import cats.implicits._
import com.iheart.happy.path.{ ExceptionReason, ItemNotFound, RegularReason, ValidationReason }
import play.api.mvc._
import service.ExpensiveBackend
import format.FormatImplicit._

import scala.concurrent.ExecutionContext.Implicits.global

class ExpensiveController @Inject() (backend: ExpensiveBackend) extends Controller with JsonResponses {

  def demoFE(userId: String, field: Option[String]) = Action.async {

    backend
      .expensiveCall(userId, field)
      .map(Ok(_))
      .recoverLeft {
        case ValidationReason(r) => BadRequest(r.toErr)
        case ItemNotFound(r) => InternalServerError(r.toErr)
        case ExceptionReason(r) => NotAcceptable(r.getMessage.toErr)
        case RegularReason(r) => NotImplemented(r.toString.toErr)
      }
      .toFuture
  }
}
