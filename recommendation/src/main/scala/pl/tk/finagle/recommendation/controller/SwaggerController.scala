package pl.tk.finagle.recommendation.controller

import java.io.{File, InputStream}
import javax.inject.Inject

import com.google.common.io.Resources
import com.google.inject.Singleton
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.routing.FileResolver

@Singleton
class SwaggerController @Inject()(fileResolver: FileResolver) extends Controller {

  get("/api-docs") { request: Request =>
    response.temporaryRedirect.location("/api-docs/index.html?url=../api.yml")
  }

  get("/api-docs/api.yml") { request: Request =>
    response.ok.file("api.yml")
  }


  get("/api-docs/:*") { request: Request =>
    val path = request.params("*")
    val ret = response.ok.body(static(s"$swaggerUiPrefix/$path"))
    ret.contentType(fileResolver.getContentType(request.path))
    ret
  }

  private val swaggerUiPrefix = "META-INF/resources/webjars/swagger-ui/2.1.1"

  private def static(resourceName: String): InputStream = {
    Resources.getResource(resourceName).openStream()
  }
}