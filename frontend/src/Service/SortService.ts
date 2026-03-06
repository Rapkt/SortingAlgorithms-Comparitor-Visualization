import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SortStats } from '../Models/SortStats';
import{Steps} from "../Models/Steps";
export interface Vis {
  algoId:string;
  size:number;
  type:string;
}
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
    return this.http.get<Steps[]>(`${this.URL}/steps`, { params });
  }

  startVis(algoId:string,type:string,size:number,arr:number[]):Observable<number[]> | null {
    const payload={
      algoId:algoId,
      size:size,
      type:type,
      arr:arr
    }
    return this.http.post<number[]>(`${this.URL}/startVis`, payload);
  }

  triggerTheater(algoId:string):Observable<number[]> {
    return this.http.post<number[]>(`${this.URL}/startTheater?algoId=${algoId}`,{});
  }
}
