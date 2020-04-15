package absa.cgs.com.ui.screens.apis.readexpenseapicall.model

data class ReadExpenseResponseModel(var status_code: Int, var message: String, var product_details: List<ReadExpenseProductDetailModel>)