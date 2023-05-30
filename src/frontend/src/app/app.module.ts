import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import { CustomerComponent } from './components/customer/customer.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import {AvatarModule} from "primeng/avatar";
import { MenuItemComponent } from './components/menu-item/menu-item.component';
import { HeaderBarComponent } from './components/header-bar/header-bar.component';
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {MenuModule} from "primeng/menu";
import {SidebarModule} from "primeng/sidebar";
import { ManageCustomerComponent } from './components/manage-customer/manage-customer.component';
import { LoginComponent } from './components/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    MenuBarComponent,
    MenuItemComponent,
    HeaderBarComponent,
    ManageCustomerComponent,
    LoginComponent
  ],
  schemas: [NO_ERRORS_SCHEMA],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    InputTextModule,
    FormsModule,
    AvatarModule,
    ButtonModule,
    RippleModule,
    MenuModule,
    SidebarModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
