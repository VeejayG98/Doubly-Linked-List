import java.util.ArrayList;

public class MyStack<AnyType> {

    private int top = -1;

    ArrayList<AnyType> stack = new ArrayList<AnyType>();

    public int getSize(){
        return top + 1;
    }
    
    public void push(AnyType x){
        stack.add(x);
        top ++;
    }

    private boolean isEmpty(){
        
        return (getSize() == 0);
    }

    public AnyType pop(){

        if(isEmpty()){
            System.out.println("Stack is already empty");
            return null;
        }

        AnyType x = stack.get(top);
        stack.remove(top);
        top --;
        return x;
    }

    public static void main(String[] args) {

        // Creating the expression using a String array
        String[] a = {"[","(","{","}","{","}",")","]"};
        MyStack<String> stack = new MyStack<>();

        for(int i = 0; i < a.length; i++){
            // Checking if the symbol is an opening symbol
            if (a[i] == "[" || a[i] =="(" || a[i] == "{")
                stack.push(a[i]);
            
        // Checking if the closing symbol matches the opening symbol or if we are pushing a closing symbol into an empty stack

            else if (a[i] == "}" && (stack.isEmpty() || stack.pop() != "{")){
                System.out.println("Symbols are not balanced");
                return;
            }

            else if (a[i] == ")" && (stack.isEmpty() || stack.pop() != "(")){
                System.out.println("Symbols are not balanced");
                return;
            
            }

            else if (a[i] == "]" && (stack.isEmpty() || stack.pop() != "[")){
                System.out.println("Symbols are not balanced");
                return;
            
            }
        }

        // To check if there are any open symbols still present in the stack
        if (stack.getSize() != 0){
            System.out.println("Symbols are not balanced");
            return;
        }

        else{
            System.out.println("Symbols are balanced");
        }
        
    }
}
