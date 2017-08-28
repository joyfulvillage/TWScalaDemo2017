package models

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.json.Writes._

import scala.concurrent.duration._

case class Inner(
  genres: Seq[String],
  name: String,
  basicCost: Double
)

object Inner {
  implicit val innerFormat = Json.format[Inner]
}

case class CountInner(
  name: String,
  num: Int
)

object CountInner {
  implicit val countInnerFormat = Json.format[CountInner]
}

case class ConfigOut(
  interval: FiniteDuration,
  inner: Inner,
  counting: Map[String, CountInner]
)

object ConfigOut {
  import format.FormatImplicit._
  import Inner.innerFormat
  import CountInner.countInnerFormat
  implicit val configOutFormat = Json.format[ConfigOut]
}
