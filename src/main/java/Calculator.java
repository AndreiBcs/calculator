public class Calculator {
    private StringBuilder input;
    public Calculator() {
        this.input = new StringBuilder();
    }
    public Calculator(String input) {
        this.input = new StringBuilder(input);
    }
    public void clear(){
        this.input.setLength(0);
    }
    public void backspace(){
        if(!this.input.isEmpty()) {
            this.input.deleteCharAt(this.input.length() - 1);
        }
    }
    public String getInput(){
        return this.input.toString();
    }
    public void append(char ch){
        char lastChar = this.input.charAt(this.input.length() - 1);
        if(isOperator(lastChar)&&isOperator(ch)){
            this.input.deleteCharAt(this.input.length() - 1);
        }
        else {
            this.input.append(ch);
        }
    }
    public boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c=='%' || c=='^' || c=='.';
    }
}
