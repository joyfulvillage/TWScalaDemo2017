package models

import play.api.libs.json.Json

case class Permission(id: Int, priority: Int)

object Permission {
  implicit val permissionFormat = Json.format[Permission]
}

case class ExpensiveInfo(
  id: Int,
  friendsCount: Int,
  permission: Permission,
  res: String
)

object ExpensiveInfo {
  import Permission.permissionFormat
  implicit val expensiveInfoFormat = Json.format[ExpensiveInfo]
}

