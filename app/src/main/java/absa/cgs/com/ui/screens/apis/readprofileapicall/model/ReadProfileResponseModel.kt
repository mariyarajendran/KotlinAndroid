package absa.cgs.com.ui.screens.apis.readprofileapicall.model

data class ReadProfileResponseModel(val status: Int, val message: String,
                                    val user_details: ReadProfileDetailModel,
                                    val nominee_details: ReadNomineeDetailsModel,
                                    val bank_details: ReadBankDetailModel)