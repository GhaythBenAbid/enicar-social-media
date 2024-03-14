import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './Pages/landing-page/landing-page.component';
import { LoginComponent } from './Pages/login/login.component';
import { HomepageComponent } from './Pages/homepage/homepage.component';
import { RegisterComponent } from './Pages/register/register.component';
import { ListClubComponent } from './Pages/admin/club/list-club/list-club.component';
import { ErrorPageComponent } from './Pages/error-page/error-page.component';
import { VerifcationComponent } from './Pages/verifcation/verifcation.component';


const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomepageComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'verify/:token', component: VerifcationComponent },

  //admin group routes
  {
    path: 'admin',
    children: [
      { path: 'club', component: ListClubComponent }, 
    ],
  },




  { path: '**', component: ErrorPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
