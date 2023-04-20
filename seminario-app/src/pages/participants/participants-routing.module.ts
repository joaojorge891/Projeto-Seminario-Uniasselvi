import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParticipantsListComponent } from "./participant-list/participant-list.component";
import { ParticipantFormComponent } from "./participant-form/participant-form.component";

const routes: Routes = [
  { path: '', component: ParticipantsListComponent },
  { path: 'new', component: ParticipantFormComponent },
  { path: ':id/edit', component: ParticipantFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParticipantsRoutingModule {
}
