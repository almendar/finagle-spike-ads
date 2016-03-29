package pl.tk.finagle.recommendation.services.userprofiles

import javax.inject.Inject

import com.google.inject.ImplementedBy
import com.twitter.util.Future
import pl.tk.finagle.recommendation.{GetEshopProducts, Product}


case class CookieId(value: Int) extends AnyVal


case class UserProfile(device: String,
                       locations: List[String],
                       language: List[String])

@ImplementedBy(classOf[UserProfileServiceClientImpl])
trait UsersProfileClient {
  def getProfile(req: Int): Future[UserProfile]
}


class UserProfileServiceClientImpl @Inject()(userProfilesService: UserProfilesService,
                                             getUserProfileFilter: GetUserProfileFilter) extends UsersProfileClient {
  override def getProfile(req: Int): Future[UserProfile] =
    (getUserProfileFilter andThen userProfilesService) (req)
}



