import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { Participants } from './participants.model';

const host: string = 'http://localhost:8080/api/participants';

@Injectable({
  providedIn: 'root'
})
export class ParticipantsService {

  constructor (private http: HttpClient) {
  }

  getAll(): Observable<Participants[]> {
    return this.http.get(host).pipe(
      catchError(this.handleError),
      map(this.jsonDataToParticipants)
    );
  }

  getById(id: number): Observable<Participants> {
    const url = `${host}/${id}`;

    return this.http.get(url).pipe(
      catchError(this.handleError),
      map(this.jsonDataToparticipant)
    );
  }

  create(participant: Participants): Observable<Participants> {
    return this.http.post(host, participant).pipe(
      catchError(this.handleError),
      map(this.jsonDataToparticipant)
    );
  }

  update(participant: Participants): Observable<Participants> {
    const url = `${host}/${participant.id}`;
    return this.http.put(url, participant).pipe(
      catchError(this.handleError),
      map(() => participant)
    );
  }

  delete(id: number): Observable<Participants> {
    return this.http.delete(`${host}/${id}`).pipe(
      catchError(this.handleError),
      map(() => null)
    );

  }

  //Private Methods
  private jsonDataToParticipants(jsonData: any[]): Participants[] {
    const participants: Participants[] = [];
    jsonData.forEach(element => participants.push(element as Participants));
    return participants;
  }

  private jsonDataToparticipant(jsonData: any): Participants {
    return jsonData as Participants;
  }

  private handleError(error: any): Observable<any> {
    return throwError(error);
  }

}
