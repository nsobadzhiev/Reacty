import { Component, OnInit, Input } from '@angular/core';
import { ReactionService } from '../reaction.service';
import { Reaction } from '../reaction';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  @Input() set searchTerm(value:string) {
    console.log("Starting search for " + value);
    this.reactionService.getReactionsForSearch(value).subscribe(
      data => {
        this.reactions = data
        console.log("Returned reactions " + data[0])
        console.log("First one " + this.reactions[0].name)
      },
      error => { console.log(error) },
      () => { console.log('Done loading reactions') }
   );
  }

  reactions: Array<Reaction> = [];

  constructor(private reactionService:ReactionService) { }

  ngOnInit() {
  }

  public pathForReaction(reaction:Reaction): String {
    if (reaction.image != null) {
      return "images/" + reaction.image
    }
    else {
      return reaction.link
    }
  }

}
