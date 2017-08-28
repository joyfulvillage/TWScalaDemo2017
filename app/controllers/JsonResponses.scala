package controllers

import play.api.http.Writeable
import play.api.libs.json.{ Json, Writes }

trait JsonResponses {

  implicit def writeableJson[A: Writes]: Writeable[A] =
    Writeable.writeableOf_JsValue.map[A](Json.toJson(_))

}
