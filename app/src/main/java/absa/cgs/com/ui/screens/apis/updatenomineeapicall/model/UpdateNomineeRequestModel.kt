package absa.cgs.com.ui.screens.apis.updatenomineeapicall.model

data class UpdateNomineeRequestModel(var user_id: String, var nominee_name: String,
                                     var nominee_phone_number: String, var nominee_address: String,
                                     var nominee_relation_nominee: String)