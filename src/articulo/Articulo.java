/*
9.  Se conoce de un artículo su costo base y se sabe que se ofrecen 2 precios de venta: 
al detal (30% de incremento del costo base) y al mayor (15% de incremento del costo base). 
Se desea conocer los distintos PVP del artículo, al detal y al mayor.   
Desarrolle una aplicación en java en la que se creen cinco artículos y para cada uno se muestre los PVP.
 */
package articulo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Articulo {
    //atributos
    String articulo;
    double precioBase;
    
    //metodo constructor
    public Articulo() {
    }
        
    //metodos setter y getter
    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
   
    //calcular valor del articulo al detal
    public double calcularPrecioDetal(double precio){
        double precioDetal, incremento;
        incremento = (precio*30)/100;
        precioDetal= (precio+incremento);
        return precioDetal;
    }
    
    //calcular valor del articulo al por mayor
    public double calcularPrecioMayor(double precio){
        double precioMay, incremento;
        incremento = (precio*15)/100;
        precioMay= (precio+incremento);
        return precioMay;
    }
    
    //creando archivo txt para almacenar los datos ingresados
    public void crearArchivo(JTable jtab){
        try{
           String fileRectangulo = "D:\\Documents\\NetBeansProjects\\Articulo\\src\\Articulo\\datos.txt";
           BufferedWriter bfw = new BufferedWriter(new FileWriter(fileRectangulo));
            for (int i = 0; i < jtab.getColumnCount(); i++) {
                bfw.write(jtab.getColumnName(i) + "      ");
            }
            bfw.write("\n");
            for (int i = 0; i < jtab.getRowCount(); i++) {
                for (int j = 0; j < jtab.getColumnCount(); j++) {
                    bfw.write(jtab.getValueAt(i, j).toString() + "         ");
                }
                bfw.newLine();
            }
            bfw.close();
            JOptionPane.showMessageDialog(null, "El archivo fue creado correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo." + e.getMessage());
        }
    }
    
    //llenar tabla con los registros
    public void llenarTabla(String articulo, double prec, JTable tabla){
        //variables
        double prec_D, prec_M;
        //asignar los valores obtenidos
        setArticulo(articulo);
        setPrecioBase(prec);
        prec_D = calcularPrecioDetal(prec);
        prec_M = calcularPrecioMayor(prec);
        //añadimos el registro a la tabla
        try {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object nuevaTab[] = {articulo,prec,prec_D,prec_M};
            tb.addRow(nuevaTab);
            JOptionPane.showMessageDialog(null, "Registro Añadido exitosamente"); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo añadir "+e.getMessage());
        }
    }
    
    // Eliminar datos de una tabla
    public void eliminaRegistro(JTable tabla){
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        if (tabla.getSelectedRow()>=0){
            tb.removeRow(tabla.getSelectedRow());
        } 
    }
    
    //guardar datos modificados
    public void guardarDatosModificados(JTable tabla, int filaSelec, String articulo, double prec){
        try {            
            tabla.setValueAt(articulo, filaSelec, 0);
            tabla.setValueAt(prec, filaSelec, 1);
            tabla.setValueAt(calcularPrecioDetal(prec), filaSelec, 2);
            tabla.setValueAt(calcularPrecioMayor(prec), filaSelec, 3);
            
            JOptionPane.showMessageDialog(null, "Registro Modificado exitosamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
