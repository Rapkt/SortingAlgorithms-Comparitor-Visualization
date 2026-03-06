# Algorithm Analysis & Visualization Suite 🚀

A **full-stack, high-fidelity system** designed to **visualize, benchmark, and statistically analyze six fundamental sorting algorithms**.

Developed on **Arch Linux**, this project bridges the gap between **theoretical Big-O complexity** and **real-world hardware performance** through interactive visualization and scientific benchmarking.

---

# 🛠️ Tech Stack

### Backend

* **Java 21**
* **Spring Boot**
* RESTful API architecture

### Frontend

* **Angular 17**
* **D3.js** for scalable SVG rendering and visualization

### Data Analysis

* **Python 3**
* **Pandas**
* **Matplotlib**
* **SciPy** (Cubic Spline Interpolation)

---

# ✨ Key Features

## 🎭 The Theater

A **dynamic visualization engine** powered by **D3.js** that maps array values to **SVG coordinates** and renders sorting operations through **asynchronous state playback**.

## 🔬 Scientific Benchmarking

### Identical Dataset Control

Every algorithm in a benchmark suite runs against a **bit-for-bit identical clone** of a master dataset to ensure **fair comparisons**.

### Statistical Aggregation

Supports **multi-iteration batch testing** and calculates:

* Mean runtime
* Minimum runtime
* Maximum runtime

<img width="1221" height="583" alt="Screenshot 2026-03-06 195018" src="https://github.com/user-attachments/assets/37b5cf29-0286-4725-a1fe-d175dd1b2fae" />


## 📄 State Pagination

Custom **pagination logic** handles the **O(n²) step explosion** produced by inefficient algorithms, preventing **browser memory exhaustion** during large visualizations.

---

# 📊 Supported Algorithms

| Algorithm      | Theoretical Complexity | Performance Class           |
| -------------- | ---------------------- | --------------------------- |
| Quick Sort     | O(N log N)             | Cache-Efficient / In-place  |
| Merge Sort     | O(N log N)             | Stable / Out-of-place       |
| Heap Sort      | O(N log N)             | Memory-Efficient / In-place |
| Insertion Sort | O(N²)                  | Hybrid-ready                |
| Selection Sort | O(N²)                  | Minimizes Interchanges      |
| Bubble Sort    | O(N²)                  | Educational baseline        |

---

# 🚀 Getting Started

## 1️⃣ Backend (Spring Boot)

Run the backend server:

```bash
./mvnw spring-boot:run
```

The API server will start at:

```
http://localhost:8080
```

---

## 2️⃣ Frontend (Angular)

Install dependencies and start the development server:

```bash
npm install
ng serve
```

Then open:

```
http://localhost:4200
```

---

## 3️⃣ Data Science Bridge (Python)

Run the benchmark analysis script:

```bash
python benchmark_analysis.py
```

This will:

* Execute benchmark sweeps across all algorithms
* Aggregate statistical metrics
* Generate a high-resolution graph:

```
sorting_benchmark.png<img width="845" height="529" alt="output" src="https://github.com/user-attachments/assets/34f5ccfa-4565-40e8-a4dd-d7a5710a1262" />

```

---

# 🧪 Experimental Results

The benchmarking system empirically demonstrates that:

* **O(N log N)** algorithms share the same asymptotic growth.
* **Quick Sort** often achieves the best real-world performance due to **superior cache locality**.
* **Merge Sort** provides **high stability**, but incurs additional **memory bus traffic** because it operates out-of-place.

These results highlight the difference between **theoretical complexity** and **practical hardware behavior**.
<img width="1221" height="583" alt="Screenshot 2026-03-06 195018" src="https://github.com/user-attachments/assets/6623fd84-7745-49c5-ae1c-fadd5ae1d668" />

---

# 📌 Project Goals

* Bridge the gap between **algorithm theory and empirical performance**
* Provide **interactive algorithm education**
* Enable **scientific benchmarking of sorting algorithms**
* Demonstrate **full-stack integration between visualization, backend computation, and data science**

---
<img width="2840" height="1531" alt="Screenshot 2026-03-06 192124" src="https://github.com/user-attachments/assets/b20bc1e3-5f32-4558-8140-311ff08e072c" />

