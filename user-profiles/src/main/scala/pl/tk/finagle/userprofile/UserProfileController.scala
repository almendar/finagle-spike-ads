package pl.tk.finagle.userprofile

import javax.inject.Inject

import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.{Header, RouteParam}
import com.twitter.inject.Logging


case class UserProfileRequest(@RouteParam cookieId: Int)

class UserProfileController @Inject()(userProfileService: UserProfileService) extends Controller with Logging {
  get("/userprofile/:cookie_id") { request: UserProfileRequest =>
    debugFutureResult("User profile")(userProfileService(CookieId(request.cookieId)))
  }
}
