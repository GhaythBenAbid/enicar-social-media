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

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    LandingPageComponent,
    ErrorPageComponent,
    LoginComponent,
    ListClubComponent,
    NavbarComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    NgToastModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
