import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable()
export class EventService {
  apiUrl = '/events'
  constructor(private http: HttpClient) { }

  getEvents(username, repo, event){
    let url = this.apiUrl + "?username=" + username + "&repo=" + repo + "&event=" + event;
    return this.http.get(url)
  }
}
