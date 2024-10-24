import { JsonPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { EmailService } from '../../service/email.service';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { HttpClientModule } from '@angular/common/http';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-email',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    JsonPipe,
    HttpClientModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    CommonModule
  ],
  templateUrl: './email.component.html',
  styleUrl: './email.component.css',
})
export class EmailComponent {
  data = {
    to: '',
    subject: '',
    msg: '',
  };

  flag:boolean=false;

  constructor(private email: EmailService, private snackBar: MatSnackBar) {}

  doSubmitForm() {
    console.log('trying to submit data');
    console.log('Data', this.data);

    if (this.data.to ==='' || this.data.subject === '' || this.data.msg === '') {
      this.snackBar.open('Fields cannot be empty !!', 'OK');
      return;
    }
    this.flag=true;
    this.email.sendEmail(this.data).subscribe(
      response => {
        console.log(response);
        this.snackBar.open('Email sent successfully!', 'OK');
        this.flag=false;
      },
      error => {
        console.log(error);
        this.snackBar.open('Failed to send email!', 'OK');
        this.flag=false;
      }
    );
  }
}
