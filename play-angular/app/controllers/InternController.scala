package controllers


import javax.inject.Inject

import play.api.data.Form
import play.api.data.Forms._

import play.api.mvc.{Controller, Action}
import play.api.i18n.Messages.Implicits._
import play.api.Play.current
import repository.InternRepo
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import play.api.libs.json._

/**
  * Created by knodus on 2/3/16.
  */

//case class Intern(name:String,email:String ,mobile:Int ,address:String ,emergencyContact:Int ,id:Int=0)

class InternController @Inject()(intern:InternRepo) extends Controller {

  val deleteForm = Form(
    "id"-> number
  )

  val internForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> email.verifying("Email can't be empty",email=> if (email== "") false else true),
      "mobile" ->number.verifying("Mobile can't be empty",mobile=> if (mobile== "") false else true),
      "address" ->nonEmptyText,
      "emergencyContact" ->number.verifying("Emergency Contact can't be empty",contact=> if (contact== "") false else true),
        "id" -> number.verifying("Id can't be empty",id=> if (id== "") false else true)
    )(repository.Intern.apply)(repository.Intern.unapply)

  )



  def showSubmittedInfo = Action.async { implicit request =>
      internForm.bindFromRequest.fold(
        formWithErrors => {
          Future(Redirect(routes.InternController.index))
        },
        UserData => {
          Future {
         /*     val json: JsValue = Json.obj(
              "emp-name" -> UserData.name,
              "emp-email" -> UserData.email,
              "emp-mobile" -> UserData.mobile,
              "emp-address" -> UserData.address,
              "emp-emergencyContact" -> UserData.emergencyContact,
              "emp-id" -> UserData.id
            )
*/
            intern.addIntern(UserData)
          Ok(views.html.submittedForm())
          }
        }
      )



    }

  implicit val userFormat = Json.format[repository.Intern]

  def getInterns =Action.async {
    intern.getIntern.map{ interns => Ok(Json.obj("interns" -> interns))
    }
  }

  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  def loadForm = Action { implicit request =>
    Ok(views.html.form())

  }

  def deleteIntern = Action { implicit request =>
    val id:Int=deleteForm.bindFromRequest.get
    println("id :" + id)
    intern.deleteById(id)
    Ok(views.html.index())

  }

}
