package es.upm.dit.adsw.cifrasyletras.juego;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ValidadorCifrasConParentesis {

    public boolean numerosUsadosValidos(String expresion, List<Integer> numerosDisponibles) {
        List<Integer> numerosSinUsar = new ArrayList<>(numerosDisponibles);
        for (String token : expresion.split("[^0-9]+")) {
            if (token.isEmpty()) continue;
            try {
                Integer n = Integer.parseInt(token);
                if (!numerosSinUsar.remove(n)) {
                    System.out.println("Número no disponible: " + n);
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Token no válido: " + token);
                return false;
            }
        }
        return true;
    }

    public boolean esValida(String expresion, List<Integer> numerosDisponibles) {
        if (expresion == null) return false;
        String[] partes = expresion.trim().split("\\s*=\\s*", 2);
        if (partes.length != 2) {
            System.out.println("Formato incorrecto, falta '='");
            return false;
        }
        int resultado;
        try {
            resultado = Integer.parseInt(partes[0].trim());
        } catch (NumberFormatException e) {
            System.out.println("Resultado no es un número: " + partes[0]);
            return false;
        }
        String exprParte = partes[1].trim();
        if (!numerosUsadosValidos(exprParte, numerosDisponibles)) return false;
        int resultadoCalculado;
        try {
            resultadoCalculado = evaluar(exprParte);
        } catch (Exception e) {
            System.out.println("Error al evaluar expresión: " + e.getMessage());
            return false;
        }
        if (resultadoCalculado != resultado) {
            System.out.println("El resultado calculado " + resultadoCalculado
                    + " no coincide con el resultado esperado " + resultado);
            return false;
        }
        return true;
    }

    private int evaluar(String expresion) {
        Deque<Integer> valores = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        String s = expresion.replaceAll("\\s+", "");
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    num = num * 10 + (s.charAt(i++) - '0');
                valores.push(num);
                continue;
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(')
                    valores.push(aplicar(ops.pop(), valores.pop(), valores.pop()));
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && ops.peek() != '('
                        && precedencia(ops.peek()) >= precedencia(c))
                    valores.push(aplicar(ops.pop(), valores.pop(), valores.pop()));
                ops.push(c);
            } else {
                throw new IllegalArgumentException("Carácter no válido: " + c);
            }
            i++;
        }
        while (!ops.isEmpty())
            valores.push(aplicar(ops.pop(), valores.pop(), valores.pop()));
        return valores.pop();
    }

    private int precedencia(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private int aplicar(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-':
                int r = a - b;
                if (r < 0) throw new ArithmeticException("Resultado intermedio negativo");
                return r;
            case '*': return a * b;
            case '/':
                if (b == 0 || a % b != 0) throw new ArithmeticException("División no válida");
                return a / b;
            default: throw new IllegalArgumentException("Operador no válido: " + op);
        }
    }
}