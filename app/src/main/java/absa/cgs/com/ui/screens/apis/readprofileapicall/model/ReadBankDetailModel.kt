package absa.cgs.com.ui.screens.apis.readprofileapicall.model

data class ReadBankDetailModel(val bank_id: String, val bank_account_number: String,
                               val bank_ifsc_code: String, val bank_act_holder_name: String,
                               val pan_image: String, val address_proof_image: String,
                               val pan_image_status: String, val address_proof_status: String,
                               val bank_created: String)