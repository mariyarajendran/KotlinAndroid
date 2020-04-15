package absa.cgs.com.ui.screens.apis.updateprofileapicall.model

data class UpdateProfileRequestModel(var user_id: String, var user_name: String,
                                     var user_mailid: String, var user_mobile_number: String,
                                     var user_address: String, var user_profile_img: String,
                                     var user_office_number: String, var user_ower_name: String,
                                     var user_agency_name: String, var user_gst_number: String)