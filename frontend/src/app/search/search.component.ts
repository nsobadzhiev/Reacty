import { Component, EventEmitter, Output, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchTerm: String = "";

  @Output() onSearchSubmitted: EventEmitter<String> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  onSearch() {
    console.log("Searching for " + this.searchTerm);
    this.onSearchSubmitted.emit(this.searchTerm);
  }

}
