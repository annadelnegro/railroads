# **Building Railroads**

This project involves designing a cost-efficient railroad system for the Knights Train Company using graph algorithms. The objective is to construct a minimum spanning tree (MST) using Kruskal's Algorithm to minimize the cost of the railroad system while avoiding cycles.

---

## **Objective**
The program applies graph algorithm concepts, particularly Kruskal’s Algorithm, to:
- Create a cost-efficient railroad system.
- Avoid cycles in the railroad network.
- Ensure that students can travel across Florida by connecting vertices (cities) through the least expensive tracks.

---

## **Project Structure**

### **Java Files**
- **`Railroad.java`**
  - Core class implementing Kruskal’s Algorithm.
  - Constructs a minimum spanning tree from the input graph (text file) of vertices and tracks.
  - Returns the tracks used and the total cost.

- **`RailroadDriver.java`**
  - Driver program that tests the `Railroad` class using provided test cases and files.

---

## **Input**
1. **Test Case Files**
   - Each line in the test case files contains:
     ```
     VERTEX VERTEX COST
     ```
     Example:
     ```
     ORLANDO TAMPA 140
     ```
     - **VERTEX**: A label representing a city or point on the map (e.g., "ORLANDO", "A").
     - **COST**: The cost of building a track between the two vertices.

2. **Constraints**
   - The program must handle both city names and letter labels for vertices.
   - All test case files are in the same directory as the driver program.

---

## **Output**
- A string displaying the tracks used in the MST and the total cost.
- Tracks are displayed in the format:
