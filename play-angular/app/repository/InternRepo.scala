package repository

import javax.inject.{Inject, Singleton}

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile


import scala.concurrent.Future

/**
  * Created by knodus on 9/3/16.
  */

case class Intern(name:String,email:String ,mobile:Int ,address:String ,emergencyContact:Int ,id:Int=0)

trait InternTable { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val internTableQuery = TableQuery[InternTable]

  class InternTable(tag: Tag) extends Table[Intern](tag,"intern") {
    val name = column[String]("name", O.SqlType("VARCHAR(200)"))
    val email = column[String]("email", O.SqlType("VARCHAR(200)"))
    val mobile = column[Int]("mobile")
    val address= column[String]("address",O.SqlType("VARCHAR(200)"))
    val emergencyContact = column[Int]("emergency_contact")
    val id = column[Int]("id",O.PrimaryKey, O.AutoInc)

    def * = (name,email,mobile,address,emergencyContact,id) <>(Intern.tupled, Intern.unapply)

  }
}

@Singleton()
class InternRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends InternTable with
  HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def getIntern:Future[List[Intern]]={
    val statement=internTableQuery.to[List].result
    db.run(statement)
  }


  def addIntern(intern:Intern)={
    val statement=internTableQuery += intern
    db.run(statement)
  }

  def deleteById(id:Int)={
    val statement=internTableQuery.filter(_.id ===id).delete
    db.run { statement }
  }


}