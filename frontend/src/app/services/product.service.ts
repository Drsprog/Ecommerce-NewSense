import { Injectable } from '@angular/core';
import { map, Observable, of} from 'rxjs';
import { Product } from '../models/product.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiUrl = 'http://localhost:8080/products';
  constructor(private http: HttpClient) {}

  // Obtener todos los productos
  getProducts(): Observable<Product[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      map((products) =>
        products.map((p) => ({
          id: p.id ?? p._id, // si no tiene id, toma _id
          name: p.name,
          description: p.description,
          price: p.price,
          stock: p.stock,
          category: p.category, // MongoDB devuelve _id
        }))
      )
    );
  }

  // Obtener producto por ID
  getProductById(id: string): Observable<Product> {
    return this.http.get<any>(`${this.apiUrl}/${id}`).pipe(
      map((p) => ({
        id: p.id ?? p._id,
        name: p.name,
        description: p.description,
        price: p.price,
        stock: p.stock,
        category: p.category,
      }))
    );
  }

  // Filtrar por categoría
  getProductsByCategory(category: string): Observable<Product[]> {
    return this.http.get<any[]>(`${this.apiUrl}/category/${category}`).pipe(
      map((products) =>
        products.map((p) => ({
          id: p.id ?? p._id,
          name: p.name,
          description: p.description,
          price: p.price,
          stock: p.stock,
          category: p.category,
        }))
      )
    );
  }

  // Filtrar por precio menor a X
  getProductsByPriceLessThan(price: number): Observable<Product[]> {
    return this.http.get<any[]>(`${this.apiUrl}/price/under/${price}`).pipe(
      map((products) =>
        products.map((p) => ({
          id: p.id ?? p._id,
          name: p.name,
          description: p.description,
          price: p.price,
          stock: p.stock,
          category: p.category,
        }))
      )
    );
  }
}
