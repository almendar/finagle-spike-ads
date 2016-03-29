package pl.tk.finagle.userprofile

import javax.inject.Inject

import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.{Header, RouteParam}
import com.twitter.inject.Logging


case class UserProfileRequest(@RouteParam cookieId: Int,
                              @Header `x-auth`: String)

class UserProfileController @Inject()(userProfileService: UserProfileService) extends Controller with Logging {
  get("/userprofile/:cookie_id") { request: UserProfileRequest =>
    userProfileService(CookieId(request.cookieId))
  }
}
