import { Component } from '@angular/core';
import { EventService} from './event.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private data = []
  constructor(private eventService:EventService){

  }

  ngOnInit(){
    this.getEvents("eisenachalex", "github-events", "")
  }

  getEvents(username, repo, event){
      this.eventService.getEvents(username, repo, event).subscribe(
        (data: any) => {
        this.data = data;
        },
        err => {
          console.log(err)
        }
      )
  }
}
