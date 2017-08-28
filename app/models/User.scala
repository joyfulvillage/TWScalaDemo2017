package models

import play.api.libs.json.Json

final case class User(
  id: Int,
  name: String,
  email: String,
  age: Int
)

object User {
  implicit val userFormat = Json.format[User]
}