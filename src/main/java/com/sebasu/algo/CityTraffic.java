package main.java.com.sebasu.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// For this challenge you will be finding the maximum traffic that will enter a node.
/*
have the function CityTraffic(strArr) read strArr which will be a representation of an undirected graph in a form
similar to an adjacency list. Each element in the input will contain an integer which will represent the
population for that city, and then that will be followed by a comma separated list of its neighboring cities
and their populations (these will be separated by a colon). For example: strArr may be
["1:[5]", "4:[5]", "3:[5]", "5:[1,4,3,2]", "2:[5,15,7]", "7:[2,8]", "8:[7,38]", "15:[2]", "38:[8]"].

Your goal is to determine the maximum traffic that would occur via a single road if everyone decided to go
to that city. For example: if every single person in all the cities decided to go to city 7, then via the
upper road the number of people coming in would be (8 + 38) = 46. If all the cities beneath city 7 decided
to go to it via the lower road, the number of people coming in would be (2 + 15 + 1 + 3 + 4 + 5) = 30.
So the maximum traffic coming into the city 7 would be 46 because the maximum value of (30, 46) = 46.
Your program should determine the maximum traffic for every single city and return the answers in a comma
separated string in the format: city:max_traffic,city:max_traffic,...
The cities should be outputted in sorted order by the city number.
For the above example, the output would therefore be: 1:82,2:53,3:80,4:79,5:70,7:46,8:38,15:68,38:45.
The cities will all be unique positive integers and there will not be any cycles in the graph.
There will always be at least 2 cities in the graph.
*/

public class CityTraffic {
    Map<Integer, String[]> nodesMap;

    public CityTraffic(String[] nodes) {
        this.nodesMap = new HashMap<Integer, String[]>();
        for (int i = 0; i < nodes.length; i++) {
            nodesMap.put(Integer.parseInt(nodes[i].split(":")[0]), (nodes[i].split(":")[1]).substring(1, nodes[i].split(":")[1].length() - 1).split(","));
        }
    }

    public void setNodesMap(Map<Integer, String[]> nodesMap) {
        this.nodesMap = nodesMap;
    }

    public String solve() {

        StringBuilder sb = new StringBuilder();
        List<Integer> cities = new ArrayList<>();

        for (Integer key : nodesMap.keySet()) {
            addIntegerToSortedInList(cities, key);
        }

        for (Integer city : cities) {
            Integer sum = getMaxNodeTraffic(city);
            if (!sb.isEmpty()) sb.append(",");
            sb.append(city).append(":").append(sum);
        }
        return sb.toString();
    }

    private void addIntegerToSortedInList(List<Integer> list, Integer integer) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > integer) {
                list.add(i, integer);
                return;
            }
        }
        list.add(integer);
    }


    private Integer getMaxNodeTraffic(Integer city) {
        String[] otherCities = nodesMap.get(city);
        int maxTraffic = 0;
        for (String oCity : otherCities) {
            Integer neighbor = Integer.parseInt(oCity);
            if (getPathTraffic(neighbor, city) > maxTraffic) {
                maxTraffic = getPathTraffic(neighbor, city);
            }
        }
        return maxTraffic;
    }

    /**
     * find the max traffic between two cities using recursion
     * example: given 2[5], 5[1,4,3,2], if we want the traffic of the 5->2 path,
     *
     * the graph looks like:
     *        <-3
     *  2 <-5 <-4
     *       <-1
     * we need the traffic for "5", which is 5 itself + every other city neighbor of 5 different than 2
     * since we dont know how big the branch is the function will explore all the nodes in the branch
     *
     *--1st function call: getPathTraffic(5, 2)
     *  traffic + 5, does 5 have neighbors other than 2 ? yes: 1,4,3
     *  --triggered function calls: getPathTraffic(1, 5), getPathTraffic(4, 5), getPathTraffic(3, 5)
     *  traffic + 1, does 1 have neighbors other than 5? NO
     *  traffic + 4, does 4 have neighbors other than 5? NO
     *  traffic + 3, does 3 have neighbors other than 5? NO
     *  total traffic = 5 + 1 + 4 + 3 = 12
     *
     * @param city the inmediate neighbor of the current city
     * @param origin the current city we are visiting
     * @return
     */
    public int getPathTraffic(Integer city, Integer origin) {
        String[] nodes = nodesMap.get(city);
        int neighborCities = 0; //populations of all the cities that are neighbors of the current city
        for (String node : nodes) {
            Integer neighbor = Integer.parseInt(node);
            if (neighbor != origin) {
                neighborCities += getPathTraffic(neighbor, city);
            }
        }
        return neighborCities + city;
    }


}
