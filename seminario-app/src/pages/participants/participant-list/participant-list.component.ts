import { Subscription } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { ParticipantsService } from "../shared/participants.service";
import { Participants } from "../shared/participants.model";
import toastr from "toastr";
@Component({
  selector: 'app-participant-list',
  templateUrl: './participant-list.component.html',
  styleUrls: ['./participant-list.component.css']
})
export class ParticipantsListComponent implements OnInit {
  participants: Participants[];
  subscription$: Subscription;

  constructor (private ParticipantsService: ParticipantsService) {
  }

  ngOnInit(): void {
    this.ParticipantsService.getAll().subscribe(
      participants => this.participants = participants,
      error => alert('Erro ao carregar a lista!')
    );
  }

  ngOnDestroy(): void {
    if (this.subscription$) this.subscription$.unsubscribe();
  }

  deleteParticipant(participant) {
    const remove = confirm(`Deseja realmente excluir o participante "${participant.nome}" ?`);

    if (remove) {
      toastr.success("Participante removido com sucesso!");
      this.ParticipantsService.delete(participant.id).subscribe(
        () => this.participants = this.participants.filter(element => element != participant),
        () => toastr.error('Error ao tentar excluir')
      );
    }
  }
}
