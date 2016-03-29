package pl.tk.finagle.userprofile

import com.twitter.finagle.Service
import com.twitter.util.Future

import scala.util.Random


case class CookieId(value: Int) extends AnyVal


case class UserProfile(device: Device,
                       locations: List[String],
                       language: List[String])


sealed trait Device
case object Mobile extends Device
case object Desktop extends Device


object UserProfileService {

  private def getRandomOfList[A](n: Int, lst: List[A]): List[A] = {
    List.fill(Random.nextInt(n) + 1)(lst(Random.nextInt(lst.size))).distinct
  }

  private def randomDevice = if (Random.nextInt(27) % 2 == 0) Mobile else Desktop

  private def randomCityList = {
    getRandomOfList(4, List("Warsaw", "Paris", "Berlin", "TriCity", "Moscow", "New York", "Barcelona", "Prague"))
  }

  private def getRandomLanauge = {
    getRandomOfList(2, List("Polish", "German", "French", "Czech", "Spanish", "Russian", "English"))
  }

  lazy val mock: Map[Int, UserProfile] = (1 to 100).map { id: Int =>
    id -> UserProfile(randomDevice, randomCityList, getRandomLanauge)
  }.toMap
}

class UserProfileService extends Service[CookieId, Option[UserProfile]] {
  override def apply(request: CookieId): Future[Option[UserProfile]] =
    Future.value(UserProfileService.mock.get(request.value))
}
