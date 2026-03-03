import {Injectable} from "@angular/core";

export interface Algorithm {
  id:string;
  name: string;
  imageUrl: string;
  description: string;
  complexity: string;
}

@Injectable({ providedIn: 'root' })

export class AlgorithmService {
  private readonly algos: Algorithm[] = [
    {
      id: 'bubble',
      name: 'Bubble Sort',
      imageUrl: '../assets/Bubble.png',
      description: 'Repeatedly steps through the list, compares adjacent elements and swaps them.',
      complexity: 'O(n²)'
    },
    {
      id: 'quick',
      name: 'Quick Sort',
      imageUrl: '../assets/Quick.png',
      description: 'A highly efficient sorting algorithm and is based on partitioning of array of data.',
      complexity: 'O(n log n)'
    },
    {
      id: 'merge',
      name: 'Merge Sort',
      imageUrl: '../assets/Merge.png',
      description: 'Divide and conquer algorithm that splits the array into halves until they are single elements.',
      complexity: 'O(n log n)'
    },
    {
      id:'heap',
      name:'Heap Sort',
      imageUrl:`../assets/heap.png`,
      description:'an efficient, comparison-based sorting algorithm that leverages a binary heap data structure to sort elements in an array',
      complexity:'O(n log n)'
    },
    {
      id:'selection',
      name:'Selection Sort',
      imageUrl:'../assets/Selection.png',
      description:'a simple, in-place, comparison-based sorting algorithm that works by repeatedly finding the smallest (or largest) element from an unsorted portion of the list and swapping it with the first element of that unsorted portion',
      complexity:'O(n²)'
    },
    {
      id:'insertion',
      name:"Insertion Sort",
      imageUrl:'../assets/Insertion.png',
      description:'a simple, in-place comparison-based sorting algorithm that builds a final sorted array (or list) one element at a time',
      complexity:'O(n²)'
    }
  ];

  getAlgorithm(){
    return this.algos;
  }
}
