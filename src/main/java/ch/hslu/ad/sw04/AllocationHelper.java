package ch.hslu.ad.sw04;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import ch.hslu.ad.sw01.Allocation;
import ch.hslu.ad.sw02.AllocationStack;

public class AllocationHelper {
    static Allocation[] create(int capacity) {
        Allocation[] list = new Allocation[capacity];
        for (int i = 0; i < capacity; i++) {
            list[i] = new Allocation(3, 1000 + i);
        }
        return list;
    }

    static Stack<Allocation> toJavaStack(Allocation[] arr) {
        var stack = new Stack<Allocation>();
        for (Allocation a : arr) {
            stack.push(a);
        }
        return stack;
    }

    static AllocationStack toAllocationStack(Allocation[] arr) {
        var stack = new AllocationStack(arr.length);
        for (Allocation a : arr) {
            stack.push(a);
        }
        return stack;
    }

    static Deque<Allocation> toDeque(Allocation [] arr) {
        var deque = new ArrayDeque<Allocation>();
        for (Allocation a : arr) {
            deque.push(a);
        }
        return deque;
    }
}
