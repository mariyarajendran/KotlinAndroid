package absa.cgs.com.ui.screens.apis.updatebankapicall.model

data class UpdateBankRequestModel(var user_id: String, var bank_account_number: String,
                                  var bank_ifsc_code: String, var bank_act_holder_name: String)