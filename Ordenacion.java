import java.util.Random;

public class Ordenacion {
    public static void main(String[] args) {
        int cantidad = 10000;
        int[] datos = new int[cantidad];
        Random rand = new Random();

        // Generación de números aleatorios
        for (int i = 0; i < cantidad; i++) {
            datos[i] = rand.nextInt(100000);
        }

        // Copias del arreglo para cada método
        int[] datosSeleccion = datos.clone();
        int[] datosShell = datos.clone();
        int[] datosQuick = datos.clone();

        // Ordenación por Selección Directa
        long inicio = System.currentTimeMillis();
        seleccionDirecta(datosSeleccion);
        long fin = System.currentTimeMillis();
        System.out.println("Tiempo de ordenación por Selección Directa: " + (fin - inicio) + " ms");

        // Ordenación por Shell
        inicio = System.currentTimeMillis();
        shell(datosShell);
        fin = System.currentTimeMillis();
        System.out.println("Tiempo de ordenación por Shell: " + (fin - inicio) + " ms");

        // Ordenación por QuickSort
        inicio = System.currentTimeMillis();
        quickSort(datosQuick, 0, datosQuick.length - 1);
        fin = System.currentTimeMillis();
        System.out.println("Tiempo de ordenación por QuickSort: " + (fin - inicio) + " ms");
    }

    // Método Selección Directa
    public static void seleccionDirecta(int[] arreglo) {
        int n = arreglo.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arreglo[j] < arreglo[min]) {
                    min = j;
                }
            }
            int temp = arreglo[min];
            arreglo[min] = arreglo[i];
            arreglo[i] = temp;
        }
    }

    // Método Shell
    public static void shell(int[] arreglo) {
        int n = arreglo.length;
        for (int salto = n / 2; salto > 0; salto /= 2) {
            for (int i = salto; i < n; i++) {
                int temp = arreglo[i];
                int j;
                for (j = i; j >= salto && arreglo[j - salto] > temp; j -= salto) {
                    arreglo[j] = arreglo[j - salto];
                }
                arreglo[j] = temp;
            }
        }
    }

    // Método QuickSort
    public static void quickSort(int[] arreglo, int inicio, int fin) {
        if (inicio < fin) {
            int indiceParticion = particion(arreglo, inicio, fin);
            quickSort(arreglo, inicio, indiceParticion - 1);
            quickSort(arreglo, indiceParticion + 1, fin);
        }
    }

    private static int particion(int[] arreglo, int inicio, int fin) {
        int pivote = arreglo[fin];
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (arreglo[j] <= pivote) {
                i++;
                int temp = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = temp;
            }
        }
        int temp = arreglo[i + 1];
        arreglo[i + 1] = arreglo[fin];
        arreglo[fin] = temp;
        return i + 1;
    }
}
