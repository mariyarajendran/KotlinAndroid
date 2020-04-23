package absa.cgs.com.di.component


import absa.cgs.com.di.annotation.PerFragment
import absa.cgs.com.di.module.FragmentModule
import absa.cgs.com.ui.screens.authentication.AuthChildfragments.AuthLoginFragment
import absa.cgs.com.ui.screens.expense.expensechildfragment.addexpense.AddExpenseFragment
import absa.cgs.com.ui.screens.expense.expensechildfragment.expensedetails.ExpenseDetailsFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(authLoginFragment: AuthLoginFragment)
    fun inject(addExpenseFragment: AddExpenseFragment)
    fun inject(expenseDetailsFragment: ExpenseDetailsFragment)


}
