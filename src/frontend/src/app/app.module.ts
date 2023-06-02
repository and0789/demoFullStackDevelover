import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CustomerComponent} from './components/customer/customer.component';
import {CustomerCardComponent} from './components/customer-card/customer-card.component';
import {LoginComponent} from './components/login/login.component';
import {HeaderBarComponent} from './components/header-bar/header-bar.component';
import {ManageCustomerComponent} from './components/manage-customer/manage-customer.component';
import {MenuBarComponent} from './components/menu-bar/menu-bar.component';
import {MenuItemComponent} from './components/menu-item/menu-item.component';
import {HttpInterceptorService} from "./services/interceptor/http-interceptor.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {SidebarModule} from "primeng/sidebar";
import {ToastModule} from "primeng/toast";
import {CardModule} from "primeng/card";
import {BadgeModule} from "primeng/badge";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {AvatarModule} from "primeng/avatar";
import {MenuModule} from "primeng/menu";
import {MessageModule} from "primeng/message";
import {FormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {ConfirmationService, MessageService} from "primeng/api";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    CustomerCardComponent,
    LoginComponent,
    HeaderBarComponent,
    ManageCustomerComponent,
    MenuBarComponent,
    MenuItemComponent,
    RegisterComponent
  ],
  schemas: [NO_ERRORS_SCHEMA],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SidebarModule,
    ToastModule,
    CardModule,
    BadgeModule,
    ButtonModule,
    RippleModule,
    AvatarModule,
    MenuModule,
    MessageModule,
    FormsModule,
    InputTextModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ConfirmDialogModule,

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    },
    MessageService,
    ConfirmationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
