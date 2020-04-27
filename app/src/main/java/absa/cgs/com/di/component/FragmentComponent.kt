package absa.cgs.com.di.component


import absa.cgs.com.di.annotation.PerFragment
import absa.cgs.com.di.module.FragmentModule
import absa.cgs.com.ui.screens.authentication.AuthChildfragments.AuthLoginFragment
import absa.cgs.com.ui.screens.expense.expensechildfragment.addexpense.AddExpenseFragment
import absa.cgs.com.ui.screens.expense.expensechildfragment.editviewexpense.EditExpenseFragment
import absa.cgs.com.ui.screens.expense.expensechildfragment.expensedetails.ExpenseDetailsFragment
import absa.cgs.com.ui.screens.profile.profilechildfragment.updatebank.UpdateBankFragment
import absa.cgs.com.ui.screens.profile.profilechildfragment.updatebank.ViewBankProofFragment
import absa.cgs.com.ui.screens.profile.profilechildfragment.updatenominee.UpdateNomineeFragment
import absa.cgs.com.ui.screens.profile.profilechildfragment.updateprofile.UpdateProfileFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(authLoginFragment: AuthLoginFragment)
    fun inject(addExpenseFragment: AddExpenseFragment)
    fun inject(expenseDetailsFragment: ExpenseDetailsFragment)
    fun inject(editExpenseFragment: EditExpenseFragment)
    fun inject(updateProfileFragment: UpdateProfileFragment)
    fun inject(updateNomineeFragment: UpdateNomineeFragment)
    fun inject(updateBankFragment: UpdateBankFragment)
    fun inject(viewBankProofFragment: ViewBankProofFragment)


}

