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
import { ResetPasswordComponent } from './Pages/reset-password/reset-password.component';
import { CalendarComponent } from './Pages/calendar/calendar.component';
import { ClubsComponent } from './Pages/clubs/clubs.component';
import { SettingsComponent } from './Pages/settings/settings.component';
import { ContactUsComponent } from './Pages/contact-us/contact-us.component';
import { ClubDetailsComponent } from './Pages/club-details/club-details.component';
import { MyAccountComponent } from './Pages/my-account/my-account.component';
import { EventsComponent } from './Pages/events/events.component';
import { EditClubComponent } from './Pages/admin/club/edit-club/edit-club.component';
import { AddEventComponent } from './Pages/add-event/add-event.component';


const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'login', component: LoginComponent },
  // { path: 'feed', component: HomepageComponent , canActivate: [AuthGuard]},
  { path: 'feed', component: HomepageComponent},
  { path: 'clubs', component: ClubsComponent },
  { path: 'club/:id', component: ClubDetailsComponent },
  { path: 'club/:id/event/add', component: AddEventComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'verify/:token', component: VerifcationComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'calendar', component: CalendarComponent },
  { path: 'myaccount', component: MyAccountComponent },
  { path: 'events', component: EventsComponent },
  { path: 'contact-us', component: ContactUsComponent },
  { path: 'settings', component: SettingsComponent },


  //admin group routes
  {
    path: 'admin',
    canActivate: [AuthGuard],
    children: [
      { path: 'club', component: ListClubComponent },
      { path: 'club/edit/:id', component: EditClubComponent },

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
