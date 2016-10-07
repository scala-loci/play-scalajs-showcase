package models

import retier._
import rescala._


case class User(name: String, avatar: String)

case class ChatMessage(user: Option[User], message: String)

object User { implicit val pickler = upickle.default.macroRW[User] }

object ChatMessage { implicit val pickler = upickle.default.macroRW[ChatMessage] }


trait ChatUserStore {
  def get(name: String): Option[User]

  def get(name: String, remote: Remote[Peer]): Option[User]

  def list: List[User]

  def save(user: User, remote: Remote[Peer]): Boolean

  def remove(name: String): Boolean
}


trait ChatRoom {
  def randomAvatar: String

  val robotMessage: Event[ChatMessage]

  def setupRobot(): Unit
}
