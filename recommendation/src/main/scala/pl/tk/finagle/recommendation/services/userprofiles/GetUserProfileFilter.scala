package pl.tk.finagle.recommendation.services.userprofiles

import javax.inject.Inject

import com.twitter.finagle.http.Method.Get
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Filter, Service}
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.util.Future
import pl.tk.finagle.recommendation.Product

private[userprofiles]
class GetUserProfileFilter @Inject()(mapper: FinatraObjectMapper) extends Filter[Int, UserProfile, Request, Response] {

  override def apply(cookieId: Int, service: Service[Request, Response]): Future[UserProfile] = {
    val req = Request(Get, s"/userprofile/$cookieId")
    req.headerMap += "x-auth" -> "secretObtainedFromSomewhereElse"
    service(req).map { response =>
      mapper.parse[UserProfile](response.content)
    }
  }
}
