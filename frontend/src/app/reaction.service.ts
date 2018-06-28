import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {} from '@angular/common/location';
import { Observable } from 'rxjs/Rx';

import { Reaction } from './reaction';

let reactionsUrl = "/reactions";

@Injectable()
export class ReactionService {

  constructor(private http: HttpClient) {

  }

  getReactionsForSearch(searchTerm:string): Observable<any>  {
    var requestOptions = new HttpParams();
    console.log("Searching for " + searchTerm);
    requestOptions = requestOptions.append("name", searchTerm);
    let requestParameters = {params: requestOptions};
    return this.http.get(reactionsUrl, requestParameters);
  }

  private parseReactions(rawReactions: any): Array<Reaction> {
    var reactions = new Array<Reaction>();
    for (let key in rawReactions) {
      let rawReaction = rawReactions[key];
      let reaction = new Reaction(rawReaction.id, rawReaction["name"], rawReaction["image"], rawReaction["tags"]);
      reactions.push(reaction);
    }
    return reactions;
  }
}
