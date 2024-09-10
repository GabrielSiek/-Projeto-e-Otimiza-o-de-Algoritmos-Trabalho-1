public class Main {

    public static void main(String[] args) {

        if (args.length != 2 || !args[0].matches("[01]+") || !args[1].matches("[01]+")) {
            System.out.println("Insira 2 números binários para multiplicar.");
        } else {

            String resultado = Karatsuba.karatsuba(args[0], args[1]);
            System.out.println("Resultado: " + resultado);
        }
    }
}
