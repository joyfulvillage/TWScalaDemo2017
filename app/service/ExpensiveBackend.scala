package service

import com.iheart.happy.path.{ FutureEither, ItemNotFound, ValidationReason }
import com.iheart.happy.path.FutureEither._
import cats.implicits._
import models.{ ExpensiveInfo, Permission }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

class ExpensiveBackend {

  private def runBlock(): String = {
    Thread.sleep(300)
    "Hello Taiwan!"
  }

  def expensiveCall(idStr: String, requiredField: Option[String]): FutureEither[ExpensiveInfo] =
    for {
      id <- ofTry(Try(idStr.toInt))
      _ <- ofOptional(requiredField, reason = ValidationReason(Seq("Just to want it to be required")))
      permission <- ofFutureEither(FakePermissionRepo.getPermission(id), leftMap = (s: String) => ItemNotFound(Option(s)))
      friendsCount <- ofFuture(FakeFriendsRepo.getFBFriendsCount(id))
      res <- ofBoolean(permission.priority > 5, runBlock(), "priority too low")
    } yield ExpensiveInfo(id, friendsCount, permission, res)

}

object FakeFriendsRepo {

  def getFBFriendsCount(id: Int): Future[Int] = Future {
    Thread.sleep(500)
    123
  }
}

object FakePermissionRepo {

  def getPermission(id: Int): Future[Either[String, Permission]] =
    if (id % 2 == 0)
      Future.successful(Left("ID has expired"))
    else if (id % 3 == 0)
      Future.successful(Right(Permission(id, 4)))
    else
      Future.successful(Right(Permission(id, 9)))
}

