public class MyDynamicStack {

    private int stackSize;
    private int[] stackArr;
    private int top;
    public int resizeCount;

    public MyDynamicStack(int size) {
        this.stackSize = size;
        this.stackArr = new int[stackSize];
        this.top = -1;
        this.resizeCount = 0;
    }

    public void push(int entry) {
        if (this.isStackFull()) {
            resizeCount++;
            System.out.println(("Stack is full. Increasing the capacity." + resizeCount));
            this.increaseStackCapacity();
        }

        this.stackArr[++top] = entry;
    }

    public long peek() {
        return stackArr[top];
    }


    private void increaseStackCapacity() {

        int[] newStack = new int[this.stackSize * 2];
        for (int i = 0; i < stackSize; i++) {
            newStack[i] = this.stackArr[i];
        }
        this.stackArr = newStack;
        this.stackSize = this.stackSize * 2;
    }

    public boolean isStackFull() {
        return (top == stackSize - 1);
    }

    public static void main(String[] args) {
        MyDynamicStack stack = new MyDynamicStack(2);
        for (int i = 0; i < 20000000; i++) {
            stack.push(i);
        }

    }
}

