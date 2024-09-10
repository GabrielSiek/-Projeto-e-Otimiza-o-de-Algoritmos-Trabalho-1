public class Karatsuba {

    public static String karatsuba(String num1, String num2) {

        int tamanho1 = num1.length();
        int tamanho2 = num2.length();
        int maxLength = tamanho1;

        if(tamanho1 > tamanho2)
            num2 = adicionaZeros(num2, tamanho1);
        else if(tamanho2 > tamanho1) {
            num1 = adicionaZeros(num1, tamanho2);
            maxLength = tamanho2;
        }

        if (maxLength == 1)
            return Integer.toString((num1.charAt(0) - '0') * (num2.charAt(0) - '0'));



        int metade = maxLength / 2;

        String num1Left = num1.substring(0, metade);
        String num1Right = num1.substring(metade);
        String num2Left = num2.substring(0, metade);
        String num2Right = num2.substring(metade);

        String numLeft = karatsuba(num1Left, num2Left);
        String numRight = karatsuba(num1Right, num2Right);
        String soma = karatsuba(soma(num1Left, num1Right), soma(num2Left, num2Right));

        String subtracao = subtrai(subtrai(soma, numLeft), numRight);

        String resultado = soma(soma(moverParaEsquerda(numLeft, 2 * (maxLength - metade)), moverParaEsquerda(subtracao, maxLength - metade)), numRight);


        return removeZeros(resultado);
    }

    public static String soma(String num1, String num2) {

        StringBuilder resultado = new StringBuilder();
        int leva = 0;

        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int soma = leva;

            if (i >= 0)
                soma += num1.charAt(i) - '0';

            if (j >= 0)
                soma += num2.charAt(j) - '0';

            resultado.append(soma % 2);

            leva = soma / 2;
        }

        if (leva > 0)
            resultado.append(leva);


        return resultado.reverse().toString();
    }


    public static String subtrai(String num1, String num2) {
        StringBuilder resultado = new StringBuilder();

        int emprestado = 0;


        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int subtracao = emprestado;

            if (i >= 0)
                subtracao += num1.charAt(i) - '0';

            if (j >= 0)
                subtracao -= num2.charAt(j) - '0';


            if (subtracao < 0) {
                subtracao += 2;
                emprestado = -1;
            }
            else
                emprestado = 0;

            resultado.append(subtracao);
        }

        while ( resultado.length() > 1 &&
                resultado.charAt(resultado.length() - 1) == '0') {

            resultado.deleteCharAt(resultado.length() - 1);
        }


        return resultado.reverse().toString();
    }

    public static String adicionaZeros(String num, int tamanho) {

        StringBuilder novoNumero = new StringBuilder(num);

        while (novoNumero.length() < tamanho) {
            novoNumero.insert(0, '0');
        }

        return novoNumero.toString();
    }

    public static String removeZeros(String num) {
        int i = 0;
        while (i < num.length() - 1 && num.charAt(i) == '0') {
            i++;
        }
        return num.substring(i);
    }


    public static String moverParaEsquerda(String num, int vezes) {

        StringBuilder novoNumero = new StringBuilder(num);

        for (int i = 0; i < vezes; i++)
            novoNumero.append('0');


        return novoNumero.toString();
    }


}
