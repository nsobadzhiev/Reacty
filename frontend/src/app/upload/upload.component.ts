import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  onFileUpload(event: Event) {
    const files = (<HTMLInputElement> event.target).files;
    if (files.length > 0) {
      let file = files[0];
      this.http.post('/upload', file).subscribe(_ => {
        console.log("Upload complete");
      },
      error => {
        console.log("Failed to upload image: " + error)
      })
    }
  }

}
