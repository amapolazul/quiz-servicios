[1mdiff --git a/src/main/scala/com/arlsura/afiliacion/bussiness/affiliation/BasicDataServiceHandler.scala b/src/main/scala/com/arlsura/afiliacion/bussiness/affiliation/BasicDataServiceHandler.scala[m
[1mindex e595a42..9d62b0f 100755[m
[1m--- a/src/main/scala/com/arlsura/afiliacion/bussiness/affiliation/BasicDataServiceHandler.scala[m
[1m+++ b/src/main/scala/com/arlsura/afiliacion/bussiness/affiliation/BasicDataServiceHandler.scala[m
[36m@@ -5,7 +5,7 @@[m [mimport java.util.Date[m
 import com.arlsura.afiliacion.bussiness.HandlerSupport.ServiceHandlerResponse[m
 import com.arlsura.afiliacion.bussiness.affiliation.BasicDataServiceHandler.AffiliationBasicDataResponse[m
 import com.arlsura.afiliacion.bussiness.affiliation.contacts.ContactDataRepository[m
[31m-import com.arlsura.afiliacion.json.marshallers.ARLJsonMarshaller.{SaveContactInformation, ContactInformationData, SaveAffiliationBasicData, GeneralJsonResponseData}[m
[32m+[m[32mimport com.arlsura.afiliacion.json.marshallers.ARLJsonMarshaller.{ SaveContactInformation, ContactInformationData, SaveAffiliationBasicData, GeneralJsonResponseData }[m
 import com.arlsura.afiliacion.persistence.entities.affiliation.basic_data.{ BankInformation, AffiliationBasicData, Address }[m
 import com.arlsura.afiliacion.persistence.entities.affiliation.contacts_data.{ ContactInformation, AffiliationContactsData }[m
 import com.arlsura.afiliacion.persistence.entities.preaffiliation.ProvinceSelected[m
[36m@@ -218,13 +218,13 @@[m [mclass BasicDataServiceHandler @Inject() ( private val repository: BasicDataRepos[m
     if ( !DNIHelper.isNIT( data.identificationType ) ) {[m
       val workCenter: WorkCenterPreSaving = new WorkCenterPreSaving()[m
       contactsRepository.findByDni( dni ) onComplete {[m
[31m-        case Success( contacts: Option[AffiliationContactsData]) =>[m
[31m-          val contactRequestInformation: SaveContactInformation = getSaveContactInformation(dni, data)[m
[32m+[m[32m        case Success( contacts: Option[ AffiliationContactsData ] ) =>[m
[32m+[m[32m          val contactRequestInformation: SaveContactInformation = getSaveContactInformation( dni, data )[m
           contacts match {[m
             case _ =>[m
               workCenter.saveOrUpdate( contactRequestInformation )[m
           }[m
[31m-        case Failure(ex) =>[m
[32m+[m[32m        case Failure( ex ) =>[m
           ex.printStackTrace()[m
       }[m
     }[m
[36m@@ -236,16 +236,16 @@[m [mclass BasicDataServiceHandler @Inject() ( private val repository: BasicDataRepos[m
    * @param data[m
    * @return[m
    */[m
[31m-  private def getSaveContactInformation(dni: String, data: SaveAffiliationBasicData): SaveContactInformation = {[m
[32m+[m[32m  private def getSaveContactInformation( dni: String, data: SaveAffiliationBasicData ): SaveContactInformation = {[m
 [m
     def getContactInformationData(): ContactInformationData = {[m
       ContactInformationData([m
         email = "",[m
         identification = data.identification,[m
         identificationType = data.identificationType,[m
[31m-        name1 = data.name1.getOrElse(""),[m
[32m+[m[32m        name1 = data.name1.getOrElse( "" ),[m
         name2 = data.name2,[m
[31m-        lastname1 = data.lastname1.getOrElse(""),[m
[32m+[m[32m        lastname1 = data.lastname1.getOrElse( "" ),[m
         lastname2 = data.lastname2,[m
         phone = None,[m
         position = None,[m
[36m@@ -260,7 +260,7 @@[m [mclass BasicDataServiceHandler @Inject() ( private val repository: BasicDataRepos[m
       insideTrainingRepresentative = getContactInformationData,[m
       legalRepresentative = getContactInformationData,[m
       manager = getContactInformationData,[m
[31m-      rosterRepresentative =  getContactInformationData,[m
[32m+[m[32m      rosterRepresentative = getContactInformationData,[m
       workSafetyRepresentative = getContactInformationData[m
     )[m
   }[m
[1mdiff --git a/src/main/scala/com/arlsura/afiliacion/proceso/pasos/centros_trabajo/WorkCenterPreSaving.scala b/src/main/scala/com/arlsura/afiliacion/proceso/pasos/centros_trabajo/WorkCenterPreSaving.scala[m
[1mindex 6cf8949..8b2f675 100755[m
[1m--- a/src/main/scala/com/arlsura/afiliacion/proceso/pasos/centros_trabajo/WorkCenterPreSaving.scala[m
[1m+++ b/src/main/scala/com/arlsura/afiliacion/proceso/pasos/centros_trabajo/WorkCenterPreSaving.scala[m
[36m@@ -8,7 +8,7 @@[m [mimport com.arlsura.afiliacion.persistence.entities.affiliation.contacts_data.Con[m
 import com.arlsura.afiliacion.persistence.entities.affiliation.work_center_data.{ AffiliationWorkCentersData, WorkCenterInformation }[m
 import com.arlsura.afiliacion.persistence.entities.preaffiliation.ProvinceSelected[m
 import com.typesafe.scalalogging.LazyLogging[m
[31m-import reactivemongo.bson.{BSONString, BSONDocument}[m
[32m+[m[32mimport reactivemongo.bson.{ BSONString, BSONDocument }[m
 import reactivemongo.core.commands.LastError[m
 [m
 import scala.collection.immutable.IndexedSeq[m
[36m@@ -52,19 +52,21 @@[m [mclass WorkCenterPreSaving extends LazyLogging {[m
             workCenters = workCenterInformation.toList[m
           )[m
 [m
[31m-          val workCenters: Future[Option[AffiliationWorkCentersData]] = affiliationWorkCenterDataWrapper.findOne(BSONDocument("dni" -> BSONString(contactsInfoRequest.dni)))[m
[32m+[m[32m          val workCenters: Future[ Option[ AffiliationWorkCentersData ] ] = affiliationWorkCenterDataWrapper.findOne( BSONDocument( "dni" -> BSONString( contactsInfoRequest.dni ) ) )[m
 [m
[31m-          val workCentersResponse: Future[LastError] = workCenters flatMap  {[m
[32m+[m[32m          val workCentersResponse: Future[ LastError ] = workCenters flatMap {[m
             workCentersInfo =>[m
               workCentersInfo match {[m
[31m-                case Some(wci) =>[m
[31m-                  affiliationWorkCenterDataWrapper.update(BSONDocument("dni" -> BSONString(contactsInfoRequest.dni)),[m
[32m+[m[32m                case Some( wci ) =>[m
[32m+[m[32m                  affiliationWorkCenterDataWrapper.update([m
[32m+[m[32m                    BSONDocument( "dni" -> BSONString( contactsInfoRequest.dni ) ),[m
                     workCenterEntity.copy([m
                       _id = wci._id,[m
                       dni = contactsInfoRequest.dni,[m
                       securityCode = contactsInfoRequest.securityCode,[m
                       workCenters = workCenterInformation.toList[m
[31m-                    ))[m
[32m+[m[32m                    )[m
[32m+[m[32m                  )[m
                 case None =>[m
                   affiliationWorkCenterDataWrapper.insert( workCenterEntity )[m
               }[m
[36m@@ -106,7 +108,7 @@[m [mclass WorkCenterPreSaving extends LazyLogging {[m
     } yield WorkCenterInformation([m
       branch = defaultBranchName,[m
       workcenterAddressData = bd.mainAddress.get,[m
[31m-      workcenterName = s"${defaultWorkCenter} ${provincesSelected(i).provinceName}",[m
[32m+[m[32m      workcenterName = s"${defaultWorkCenter} ${provincesSelected( i ).provinceName}",[m
       workcenterCode = i.toString,[m
       commercialActivity = bd.commercialActivity,[m
       fax = bd.contactInformation.phone,[m
