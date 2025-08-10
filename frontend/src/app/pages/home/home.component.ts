import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-home',
  imports: [CommonModule, NgFor, NgIf],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  products: Product[] = [];
  isLoading: boolean = false;
  error: string = '';

  constructor(private productService: ProductService) {}

  ngOnInit():void {
    this.loadProducts();
  }

  loadProducts():void {
        this.isLoading = true;
        this.error = '';
    this.productService.getProducts().subscribe({
      next: (data) => {
        this.products = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading products', err);
        this.isLoading = false;
      }
    });
  }

}
