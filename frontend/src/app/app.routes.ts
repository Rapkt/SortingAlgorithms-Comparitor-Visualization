import { RouterModule, Routes } from '@angular/router';
import { Comparisons } from './comparisons/comparisons';
import { Vis } from './vis/vis';
import { Home } from './home/home';
import { NgModule } from '@angular/core';
export const routes: Routes = [
  {path:'comparisons',component:Comparisons},
  {path:'visualizer',component:Vis},
  {path:'Home',component:Home},
  { path: '', redirectTo: '/Home', pathMatch: 'full' },
  { path: '**', redirectTo: '/Home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
