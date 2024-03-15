import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './Pages/landing-page/landing-page.component';
import { LoginComponent } from './Pages/login/login.component';
import { HomepageComponent } from './Pages/homepage/homepage.component';
import { RegisterComponent } from './Pages/register/register.component';
import { ListClubComponent } from './Pages/admin/club/list-club/list-club.component';
import { ErrorPageComponent } from './Pages/error-page/error-page.component';
import { VerifcationComponent } from './Pages/verifcation/verifcation.component';
import { AuthGuard } from './Utils/auth.guard';
import { ListUserComponent } from './Pages/admin/user/list-user/list-user.component';
import { EditUserComponent } from './Pages/admin/user/edit-user/edit-user.component';


const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomepageComponent , canActivate: [AuthGuard]},
  { path: 'register', component: RegisterComponent},
  { path: 'verify/:token', component: VerifcationComponent },


  //admin group routes
  {
    path: 'admin',
    canActivate: [AuthGuard],
    children: [
      { path: 'club', component: ListClubComponent },
      
      
      { path: 'user' , component : ListUserComponent },
      { path: 'user/edit/:id' , component : EditUserComponent }
    ],
  },




  { path: '**', component: ErrorPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
