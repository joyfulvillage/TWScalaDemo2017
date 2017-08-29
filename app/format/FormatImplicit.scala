package format

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.json.Writes._

import scala.concurrent.duration._

object FormatImplicit {

  final val key = "reason"

  implicit class StrToErr(val err: String) extends AnyVal {
    def toErr: JsValue = Json.obj(key -> err)
  }

  implicit class SeqStrToErr(val err: Seq[String]) extends AnyVal {
    def toErr: JsValue = Json.obj(key -> err.mkString(","))
  }

  implicit class OptStrToErr(val err: Option[String]) extends AnyVal {
    def toErr: JsValue = err.fold[JsValue](JsNull)(e => Json.obj(key -> e))
  }

  implicit val finiteDurationFormat = new Format[FiniteDuration] {
    def reads(json: JsValue): JsResult[FiniteDuration] = LongReads.reads(json).map(_.seconds)
    def writes(o: FiniteDuration): JsValue = LongWrites.writes(o.toSeconds)
  }

  implicit def mapFormatter[T: Format] = new Format[Map[String, T]] {
    def writes(o: Map[String, T]): JsValue =
      JsObject(o.toSeq.map { case (k, v) => (k, Json.toJson(v)) })

    def reads(json: JsValue): JsResult[Map[String, T]] =
      json.validate[Map[String, JsValue]].map(_.map {
        case (ks, vValue) ⇒ (ks, vValue.as[T])
      })
  }
}
