package pl.tk.finagle.userprofile

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.{JsonSerializer, SerializerProvider}
import com.fasterxml.jackson.databind.module.SimpleModule
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.json.modules.FinatraJacksonModule
import com.twitter.finatra.json.utils.CamelCasePropertyNamingStrategy


trait UserProfileJacksonModule extends FinatraJacksonModule {

  override val serializationInclusion = Include.NON_NULL
//  override val propertyNamingStrategy = CamelCasePropertyNamingStrategy

  override val additionalJacksonModules = Seq(new SimpleModule {
    addSerializer(classOf[Device], new JsonSerializer[Device] {
      override def serialize(value: Device, jgen: JsonGenerator, provider: SerializerProvider): Unit =
      {
        value match {
          case Mobile =>
            jgen.writeString("mobile")
          case Desktop =>
            jgen.writeString("desktop")
        }
      }
    })
  })

}

object UserProfileJacksonModule extends UserProfileJacksonModule


class UserProfilesServer extends HttpServer {

  override def jacksonModule = UserProfileJacksonModule

  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[UserProfileController]
  }
}

object UserProfilesApp extends UserProfilesServer