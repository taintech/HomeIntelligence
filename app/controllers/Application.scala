package controllers

import akka.stream.scaladsl.Source
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def stream = Action {
    val source = Source.apply(List("kiki", "foo", "bar"))
    Ok.chunked(source)
  }

  def ws = TODO

}