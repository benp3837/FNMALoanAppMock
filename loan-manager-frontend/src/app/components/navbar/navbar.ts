import { CommonModule, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AvatarModule } from 'primeng/avatar';
import { Badge, BadgeModule } from 'primeng/badge';
import { MenubarModule } from 'primeng/menubar';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {

  items: MenuItem[] = [
    { label: 'Home', routerLink: ['/userHome'] },
    { label: 'New Loan', routerLink: ['/newLoan'] },
    { label: 'Profile', routerLink: ['/profile'] }
    // Add more items as needed
  ];

}
