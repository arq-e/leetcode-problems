class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> connections = new HashMap<>();
        for (List<String> ticket : tickets) {
            if (!connections.containsKey(ticket.get(0))) {
                connections.put(ticket.get(0), new PriorityQueue<>());
            }
            connections.get(ticket.get(0)).add(ticket.get(1));
        }

        LinkedList<String> order = new LinkedList<>();
        buildItinerary(connections, "JFK", order);

        return order;
    }

    private void buildItinerary(Map<String, PriorityQueue<String>> connections, String start, LinkedList<String> order) {
        PriorityQueue<String> nextPos = connections.get(start);
        while (nextPos != null && nextPos.size() != 0) {
            buildItinerary(connections, nextPos.poll(), order);
        }
        order.addFirst(start);
    }
}