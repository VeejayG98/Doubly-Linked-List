import java.util.ArrayList;

public class MyStack<AnyType> {

    private int top = -1;
    private int size;

    ArrayList<AnyType> stack = new ArrayList<AnyType>();

    public int getSize(){
        return size;
    }
    
    public void push(AnyType x){
        stack.add(x);
        top ++;
        size ++;
    }

    private boolean isEmpty(){
        
        return (size == 0);
    }

    public AnyType pop(){

        if(isEmpty()){
            System.out.println("Stack is already empty");
            return null;
        }

        AnyType x = stack.get(top);
        stack.remove(top);
        top --;
        size --;
        return x;
    }

    public static void main(String[] args) {
        String[] a = {"{","}"};
        MyStack<String> stack = new MyStack<>();
        for(int i = 0; i < a.length; i++){
            if (a[i] == "[" || a[i] =="(" || a[i] == "{")
                stack.push(a[i]);
            
            else if (a[i] == "}" && (stack.isEmpty() || stack.pop() != "{")){
                System.out.println("Invalid expression");
                return;
            }

            else if (a[i] == ")" && (stack.isEmpty() || stack.pop() != "(")){
                System.out.println("Invalid expression");
                return;
            
            }

            else if (a[i] == "]" && (stack.isEmpty() || stack.pop() != "[")){
                System.out.println("Invalid expression");
                return;
            
            }
        }

        if (stack.getSize() != 0){
            System.out.println("Invalid expression");
            return;
        }

        else{
            System.out.println("The expression is valid!");
        }
        
    }
}
