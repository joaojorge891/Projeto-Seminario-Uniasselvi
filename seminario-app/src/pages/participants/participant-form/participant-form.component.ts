import { AfterContentChecked, Component, OnInit } from '@angular/core';
import { ParticipantsService } from '../shared/participants.service';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Participants, Tecnologia } from "../shared/participants.model";
import { switchMap } from "rxjs/operators";

import toastr from "toastr";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-participant-form',
  templateUrl: './participant-form.component.html',
  styleUrls: ['./participant-form.component.css']
})
export class ParticipantFormComponent implements OnInit, AfterContentChecked {
  public currentAction: string;
  public participantForm: FormGroup;
  public pageTitle: string;
  public serverErrorMessage: string[];
  public submittingForm: boolean = false;
  public participant: Participants = new Participants();
  public listaEstados: string[] = ["AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"];
  public listaSenioridades: string[] = ["Iniciante", "Junior", "Pleno", "Senior"];
  public listaEspecialidades: string[] = ["Front-End", "Back_end", "Full Stack"];
  private tecnologias: Array<Tecnologia> = new Array();
  private subscription$: Subscription;
  public isChecked: Object = new Object({
    HTML: false,
    CSS: false,
    JavaScript: false,
    PHP: false,
    cSharp: false,
    Python: false,
    Java: false
  });

  constructor (
    private participantsService: ParticipantsService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.setCurrentAction();
    this.buildParticipantsForm();
    this.loadParticipants();

  }

  ngOnDestroy(): void {
    if (this.subscription$) this.subscription$.unsubscribe();
  }

  ngAfterContentChecked(): void {
    this.setPageTitle();
  }

  submitForm(): void {
    this.submittingForm = true;

    if (this.currentAction == 'new')
      this.createparticipants();
    else
      this.updateparticipants();
  }

  public onChangeTecnologia(e: any) {
    if (e.target.checked && !this.tecnologias.some(el => el.nome == e.target.id))
      this.tecnologias.push(new Tecnologia(e.target.id));

    else this.tecnologias.splice(this.tecnologias.findIndex(i => i.nome == e.target.id), 1);
  }

  //Metodos Privados

  private setPageTitle() {
    if (this.currentAction == 'new')
      this.pageTitle = 'Cadastro de Novo Participante';
    else {
      const participantName = this.participant.nome || '';
      this.pageTitle = 'Editando o participante: ' + participantName;
    }
  }

  private setCurrentAction() {
    if (this.route.snapshot.url[0].path == 'new')
      this.currentAction = 'new';
    else
      this.currentAction = 'edit';
  }

  private buildParticipantsForm() {
    this.participantForm = this.formBuilder.group({
      id: [null],
      nome: [null, [Validators.required, Validators.minLength(3)]],
      email: [null, [Validators.required, Validators.email]],
      cpf: [null, [Validators.required]],
      cep: [null, [Validators.required]],
      logradouro: [null, [Validators.required]],
      bairro: [null, [Validators.required]],
      localidade: [null, [Validators.required]],
      uf: [null, [Validators.required]],
      senioridade: [null, [Validators.required]],
      especialidade: [null, [Validators.required]],
      comentarios: [null, []],
      html: [null, []],
      css: [null, []],
      js: [null, []],
      php: [null, []],
      cSharp: [null, []],
      python: [null, []],
      java: [null, []],
    });
  }

  private loadParticipants() {
    if (this.currentAction == 'edit') {
      this.route.paramMap.pipe(
        switchMap(params => this.participantsService.getById(Number(params.get("id"))))
      )
        .subscribe(
          (participant) => {
            this.participant = participant;
            this.participantForm.patchValue(participant);
            this.convertTecnologias(participant);

          },
          (error) => alert('Ocorreu um error no servidor, tente mais tarde!')
        );
    }
  }

  private createparticipants() {
    const participant: Participants = Object.assign(new Participants(), this.participantForm.value);
    participant.tecnologias = this.tecnologias;
    this.participantsService.create(participant)
      .subscribe(
        participant => this.actionsForSuccess(participant),
        error => this.actionsForError(error)
      );
  }

  private updateparticipants() {
    const participant: Participants = Object.assign(new Participants(), this.participantForm.value);
    participant.tecnologias = this.tecnologias;
    const update = confirm(`Deseja realmente atualizar este participante "${participant.nome}" ?`);

    if (update) {
      this.participantsService.update(participant).subscribe(
        participant => this.actionsForSuccess(participant),
        error => this.actionsForError(error)
      );
    }
  }

  private actionsForSuccess(participant: Participants): void {
    toastr.success("Solicitação processada com sucesso!");
    this.router.navigateByUrl('participantes', { skipLocationChange: true }).then(
      () => this.router.navigate(['participantes', participant.id, 'edit'])
    );
  }

  private actionsForError(error: any): void {
    toastr.error("Ocorreu um erro ao processar a sua solicitação!");
    this.submittingForm = false;
    if (error.status === 422)
      this.serverErrorMessage = error;
    else {
      if (error.error.Causa !== "")
        this.serverErrorMessage = [error.error.Causa];
      else
        this.serverErrorMessage = ["Falha na comunicação com o servidor. Por favor, tente mais tarde!"];
    }
  }

  private convertTecnologias(participant: Participants) {
    let tecnologia: Tecnologia[] = Object.values(participant.tecnologias);
    tecnologia.forEach(item => {
      document.querySelectorAll('#' + item.nome).forEach(el => {
        for (const chave in this.isChecked) {
          if (chave == item.nome) {
            this.isChecked[chave] = true;
            this.tecnologias = Object.assign(this.tecnologias, participant.tecnologias);
          }
        }
      });
    });
  }
}

