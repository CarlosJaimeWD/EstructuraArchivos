/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ProductoDTO;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author 7053
 */
public class ProductoFDAO {
    
    private final File f; 
    private final RandomAccessFile raf;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private int numRegistro;
    
    public ProductoFDAO() throws IOException {
        f=new File("productos.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        raf=new RandomAccessFile(f, "rw");
    }
    
    public synchronized boolean agregar(ProductoDTO dto) throws IOException{
        raf.seek(raf.length());
        raf.writeUTF(dto.toStringFile());
        raf.seek(0);
        return true;
    }
    public boolean actualizar(ProductoDTO dto) throws IOException{
        raf.seek(0);
        int row=0;
        while(row<numRegistro){
            raf.readLine();
            row++;
        }
        raf.writeUTF(dto.toStringFile());
        raf.seek(0);
        System.out.println(dto+" actualizado en la posicion "+numRegistro);
        return true;     
    }
    
    public boolean eliminar(ProductoDTO dto) throws IOException{
        raf.seek(0);
        int row=0;
        while(row<numRegistro){
            raf.readLine();
            row++;
        }
        raf.writeUTF(new ProductoDTO().toStringFile());
        raf.seek(0);
        System.out.println(dto+" borrado en la posicion "+numRegistro);
        return true;      
    }
   
    public ProductoDTO obtenerPorId(ProductoDTO dto) throws IOException, ClassNotFoundException{
        ProductoDTO producto=null;
        String registro=null;
        numRegistro=0;
        do{
            registro=raf.readLine();
            producto=new ProductoDTO(registro);
            if(producto.equals(dto)){
                System.out.println(producto+" encontrado en la posicion "+numRegistro);
                break;
            }
            numRegistro++;            
        }while(registro!=null);
        raf.seek(0);
        return producto;
    }
    
    public List<ProductoDTO> obtenerTodos() throws IOException, ClassNotFoundException{
        List<ProductoDTO> productos=new ArrayList<>();
        ProductoDTO producto=null;
        String registro=null;
        raf.seek(0);
        do{
            registro=raf.readLine();
            producto=new ProductoDTO(registro);
            productos.add(producto);
        }while(registro!=null);
        raf.seek(0);
        return productos;
    }
    
    public void cerrarArchivo() throws IOException{
        raf.close();
    }
        
}
