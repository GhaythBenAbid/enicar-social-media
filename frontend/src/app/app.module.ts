import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomepageComponent } from './Pages/homepage/homepage.component';
import { LandingPageComponent } from './Pages/landing-page/landing-page.component';
import { ErrorPageComponent } from './Pages/error-page/error-page.component';
import { LoginComponent } from './Pages/login/login.component';
import { ListClubComponent } from './Pages/admin/club/list-club/list-club.component';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { FormsModule } from '@angular/forms';
import { NgToastModule } from 'ng-angular-popup';
import { RegisterComponent } from './Pages/register/register.component';
import { VerifcationComponent } from './Pages/verifcation/verifcation.component';
import { ListUserComponent } from './Pages/admin/user/list-user/list-user.component';
import { EditUserComponent } from './Pages/admin/user/edit-user/edit-user.component';
import { AddUserComponent } from './Pages/admin/user/add-user/add-user.component';
import { DeleteUserComponent } from './Pages/admin/user/delete-user/delete-user.component';
import { ResetPasswordComponent } from './Pages/reset-password/reset-password.component';
import { SidebarComponent } from './Components/sidebar/sidebar.component';
import { CalendarComponent } from './Pages/calendar/calendar.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import { ClubsComponent } from './Pages/clubs/clubs.component';
import { SettingsComponent } from './Pages/settings/settings.component';
import { ContactUsComponent } from './Pages/contact-us/contact-us.component';
import { DialogModule } from 'primeng/dialog';
import { ClubDetailsComponent } from './Pages/club-details/club-details.component';
import { MyAccountComponent } from './Pages/my-account/my-account.component';
import { EventsComponent } from './Pages/events/events.component';
import { StoriesComponent } from './Components/stories/stories.component';
import { EditClubComponent } from './Pages/admin/club/edit-club/edit-club.component';
import { AddEventComponent } from './Pages/add-event/add-event.component';


@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    LandingPageComponent,
    ErrorPageComponent,
    LoginComponent,
    ListClubComponent,
    NavbarComponent,
    RegisterComponent,
    VerifcationComponent,
    ListUserComponent,
    EditUserComponent,
    AddUserComponent,
    DeleteUserComponent,
    ResetPasswordComponent,
    SidebarComponent,
    CalendarComponent,
    ClubsComponent,
    SettingsComponent,
    ContactUsComponent,
    ClubDetailsComponent,
    MyAccountComponent,
    EventsComponent,
    StoriesComponent,
    EditClubComponent,
    AddEventComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    NgToastModule,
    FullCalendarModule,
    DialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
