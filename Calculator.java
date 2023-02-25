import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Stack;
import javax.swing.*;



public class Calculator extends JFrame {

    static int Prec(char chr)
    {
        switch(chr)
        {
            case '+', '-':
                return 1;
            case '*', '/':
                return 2;
        }
        return -1;
    }
    JButton digits[] = {
            new JButton(" 0 "),
            new JButton(" 1 "),
            new JButton(" 2 "),
            new JButton(" 3 "),
            new JButton(" 4 "),
            new JButton(" 5 "),
            new JButton(" 6 "),
            new JButton(" 7 "),
            new JButton(" 8 "),
            new JButton(" 9 ")
    };

    JButton operators[] = {
            new JButton(" + "),
            new JButton(" - "),
            new JButton(" * "),
            new JButton(" / "),
            new JButton(" = "),
            new JButton(" C "),
            new JButton(" ( "),
            new JButton(" ) ")
    };

    String oper_values[] = {"+", "-", "*", "/", "=", "","(",")"};
    double x=0;
    char operator;
    Stack<Double> s=new Stack<Double>();
    JTextArea area = new JTextArea(3, 5);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setSize(380, 250);
        calculator.setTitle(" CALCULATOR,LABORATOR 1 ");
        calculator.setResizable(true);
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Calculator() {
        add(new JScrollPane(area), BorderLayout.NORTH);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());

        for (int i=0;i<10;i++)
            buttonpanel.add(digits[i]);

        for (int i=0;i<8;i++)
            buttonpanel.add(operators[i]);

        add(buttonpanel, BorderLayout.CENTER);
        area.setForeground(Color.BLACK);
        area.setBackground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        for (int i=0;i<10;i++) {
            int finalI = i;
            digits[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    area.append(Integer.toString(finalI));
                }
            });
        }

        for (int i=0;i<8;i++){
            int finalI = i;
            operators[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (finalI == 5)
                        area.setText("");
                    else
                    if (finalI == 4) {
                       ////////////////////////////////////////////////////////////////////
                        Stack<Character> stack=new Stack<>();
                        String result=new String("");

                        for(int i=0;i<area.getText().length();++i)
                        {
                            char c=area.getText().charAt(i);
                            if(Character.isLetterOrDigit(c))
                                result +=c;
                            else if(c=='(')
                                stack.push(c);
                            else if(c==')')
                            {
                                while(!stack.isEmpty() && stack.peek()!='(')
                                    result += stack.pop();
                                if(!stack.isEmpty() && stack.peek()!='(')
                                {System.out.println("expresie gresita");
                                    area.append("expresie gresita");}
                                else
                                    stack.pop();
                            }
                            else
                            {
                                while(!stack.isEmpty() && Prec(c)<=Prec(stack.peek()))
                                {
                                    if(stack.peek()=='(')
                                    {System.out.println("expresie gresita");
                                        area.append("expresie gresita");}
                                    result += stack.pop();
                                }
                                stack.push(c);
                            }
                        }
                        while(!stack.isEmpty())
                        {
                            if(stack.peek()=='(')
                            {System.out.println("expresie gresita");
                                area.append("expresie gresita");}
                            result+=stack.pop();
                        }

                        /////////////////////////////////////////////
//                        for(int i=0;i<result.length();i++)
//                            {
//
//                                if(     area.getText().substring(i, i + 1).equals("1")||
//                                        area.getText().substring(i, i + 1).equals("2")||
//                                        area.getText().substring(i, i + 1).equals("3")||
//                                        area.getText().substring(i, i + 1).equals("4")||
//                                        area.getText().substring(i, i + 1).equals("5")||
//                                        area.getText().substring(i, i + 1).equals("6")||
//                                        area.getText().substring(i, i + 1).equals("7")||
//                                        area.getText().substring(i, i + 1).equals("8")||
//                                        area.getText().substring(i, i + 1).equals("9")||
//                                        area.getText().substring(i, i + 1).equals("0"))
//                                {
//                                    s.add(Double.parseDouble(area.getText().substring(i, i + 1)));
//                                }
//
//                                else
//
//                                {
//                                    switch (area.getText().substring(i, i + 1)) {
//
//                                    case "+":
//
//                                        x=s.pop()+s.pop();
//                                                       s.push(x);
//                                                       break;
//                                    case "-": x=s.pop()-s.pop();
//                                                       s.push(x);
//                                                       break;
//                                    case "/": x=s.pop()/s.pop();
//                                                        s.push(x);
//                                                        break;
//                                    case "*": x=s.pop()*s.pop();
//                                                        s.push(x);
//                                                        break;
//                                    default: area.setText(" "); break;
//                                }}
//
//
//                            }
//                            System.out.println(s.peek());
//                            area.append(String.valueOf(s.peek()));0

                        Stack<Double> stack2=new Stack<>();
                        for(int i=0;i<result.length();++i)
                        {
                            char c=result.charAt(i);
                            if(Character.isDigit(c))
                                stack2.push((double)(c-'0'));
                            else
                            {
                                double x=stack2.pop();
                                double y=stack2.pop();

                                switch(c)
                                {
                                    case '+':
                                        stack2.push(y+x);
                                        break;
                                    case '-':
                                        stack2.push(y-x);
                                        break;
                                    case '/':
                                        stack2.push(y/x);
                                        break;
                                    case '*':
                                        stack2.push(y*x);
                                        break;
                                }
                            }
                        }

                        System.out.println(stack2.peek());
                        area.append("="+stack2.peek());
       }

                    else
                    {
                        area.append(oper_values[finalI]);
                        operator = oper_values[finalI].charAt(0);
                    }
                }
            });
        }
    }
}