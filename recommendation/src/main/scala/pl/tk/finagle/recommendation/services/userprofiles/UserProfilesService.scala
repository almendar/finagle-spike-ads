package pl.tk.finagle.recommendation.services.userprofiles

import javax.inject.{Inject, Singleton}

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.service.FailFastFactory.FailFast
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.inject.Logging
import com.twitter.util.Future



@Singleton
class UserProfilesService @Inject()(mapper: FinatraObjectMapper) extends Service[Request, Response] with Logging {

  private val url = "127.0.0.1:4001,127.0.0.1:4002,127.0.0.1:4003"
  private val productServiceHttpClient =
    Http.client.configured(FailFast(false)).newClient(url, "userProfileService").toService

  override def apply(request: Request): Future[Response] = productServiceHttpClient(request)
}