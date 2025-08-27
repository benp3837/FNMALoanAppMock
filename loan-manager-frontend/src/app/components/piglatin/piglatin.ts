import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-piglatin',
  imports: [CommonModule, FormsModule],
  templateUrl: './piglatin.html',
  styleUrl: './piglatin.css'
})
export class Piglatin {

 title = 'pig-latin-translator';

  englishText: string = '';
  pigLatinText: string = '';


  translate(){
    
    //Ripped directly from Github Copilot
    let words:string[] = this.englishText.split(" ");

    let pigLatin:string = ""

    for(let word of words) {
        if (word.length > 0) {
            let firstLetter:string = word.charAt(0);
            let pigLatinWord:string = word.substring(1) + firstLetter + "ay";
            this.pigLatinText += (pigLatinWord + " ");
        }
    }

  }

}
