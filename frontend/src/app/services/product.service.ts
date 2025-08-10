import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor() { }

   getProducts(): Observable<Product[]> {
    // Datos de prueba
    const products: Product[] = [
      { id: 1, name: 'Laptop Gamer', description: 'Laptop potente para juegos.', price: 4500, stock: 5 },
      { id: 2, name: 'Mouse Inalámbrico', description: 'Ergonómico y preciso.', price: 150, stock: 20 },
      { id: 3, name: 'Teclado Mecánico', description: 'Switches rojos silenciosos.', price: 300, stock: 10 }
    ];
    return of(products);
}
}
