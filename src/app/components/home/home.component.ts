import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ MatButtonModule, MatSnackBarModule ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private snackBar: MatSnackBar) { }

  openSnackbar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000, // Snackbar kitne der tak dikhai de
    });
  }

}
