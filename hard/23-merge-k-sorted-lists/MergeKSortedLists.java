/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode>((a1, a2) -> a1.val - a2.val);
        for (ListNode list : lists) {
            if (list != null) {
                q.add(list);
            }
        }

        ListNode head = new ListNode(-10001);
        ListNode curr = head;

        while (!q.isEmpty()) {
            ListNode node = q.poll();
            if (node.next != null) {
                q.add(node.next);
            }
            curr.next = node;
            curr = curr.next;
        }
        return head.next;
    }
}