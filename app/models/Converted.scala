package models

import play.api.libs.json.Json

case class Original(
  id: Int,
  name: String
)

case class Identical(
  id: Int,
  name: String
)

case class Converted(
  id: Int,
  name: String,
  magicWord: String,
  toBeDropped: String,
  age: Int,
  priority: Int = 5
)

case class ConvertedContinue(
  id: Int,
  name: String,
  magicWord: String,
  age: Int,
  priority: Int
)

object ConvertedContinue {
  implicit val convertedContinueFormat = Json.format[ConvertedContinue]
}

case class Optionaled(
  id: Option[Int],
  name: Option[String]
)

object Optionaled {
  implicit val optionaledFormat = Json.format[Optionaled]
}
