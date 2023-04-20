import { NgModule } from '@angular/core';
import { ParticipantsListComponent } from './participant-list/participant-list.component';
import { SharedModule } from "../../shared/shared.module";
import { ParticipantFormComponent } from './participant-form/participant-form.component';
import { ParticipantsRoutingModule } from './participants-routing.module';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ParticipantsListComponent,
    ParticipantFormComponent
  ],
  imports: [
    SharedModule,
    ParticipantsRoutingModule,
    FormsModule
  ]
})
export class ParticipantsModule {
}
