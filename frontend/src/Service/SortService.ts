import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SortStats } from '../Models/SortStats';
import{Steps} from "../Models/Steps";

export interface BenchmarkRequest {
  algoIdA: string;
  algoIdB: string;
  size:number;
  iterations:number;
  type:string;
}
export interface BenchMarkResults {
  totalComparisons: number;
  totalInterchanges: number;
  TotalSteps: number;
  AverageRuntime: number;
  minRuntime: number;
  maxRuntime: number;
}
export interface BenchmarkResponse {
  resultsA:BenchMarkResults;
  resultsB:BenchMarkResults;
}
@Injectable({providedIn: 'root'})
export class SortService {
  private readonly URL= 'http://localhost:8080/api';
  constructor(private http: HttpClient) { }

  runBenchmarks(request:BenchmarkRequest):Observable<BenchmarkResponse> {
    return this.http.post<BenchmarkResponse>(`${this.URL}/benchmark`,request);
  }

  getSteps(page:number,pageSize:number):Observable<Steps[]> {
    const params = new HttpParams()
      .set('page', page.toString())
    .set('end', pageSize.toString());
    return this.http.post<Steps[]>(`${this.URL}/steps`, null, { params });
  }
}
