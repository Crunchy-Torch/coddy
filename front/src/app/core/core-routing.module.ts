import { OverviewComponent } from './overview/overview.component';
import { HomeComponent } from './home/home.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const CORE_ROUTES: Routes = [
  { path: '', component: HomeComponent },
  { path: 'overview', component: OverviewComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(CORE_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class CoreRoutingModule { }
