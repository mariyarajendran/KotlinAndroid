package absa.cgs.com.ui.screens.apis.getnomineerelation.model

data class NomineeRelationResponseModel(var status: Int, var message: String, var nominee_relations: List<NomineeRelations>)