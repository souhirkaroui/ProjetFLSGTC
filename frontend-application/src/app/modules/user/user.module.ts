import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { LayoutUserComponent } from './layout-user/layout-user.component';
import { ProfileUserComponent } from './views/profile-user/profile-user.component';


@NgModule({
  declarations: [
    LayoutUserComponent,
    ProfileUserComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
